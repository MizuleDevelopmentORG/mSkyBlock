package com.mizuledevelopment.mskyblock.database.impl;

import com.mizuledevelopment.mskyblock.database.Storage;
import com.mizuledevelopment.mskyblock.utils.wrapper.WrapperType;
import com.mizuledevelopment.mskyblock.utils.wrapper.impl.DocumentWrapper;
import com.mizuledevelopment.mskyblock.utils.wrapper.impl.IslandWrapper;
import com.mizuledevelopment.mskyblock.utils.wrapper.impl.ProfileWrapper;
import com.mizuledevelopment.mskyblock.zSkyBlock;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Objects;
import java.util.UUID;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Mongo extends Storage {

    private MongoCollection<Document> islands;
    private MongoCollection<Document> profiles;

    @Override
    public void init(){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(Objects.requireNonNull(zSkyBlock.getInstance().getConfiguration().getString("mongo.uri"))))
                .codecRegistry(pojoCodecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("zSkyBlock");

        this.profiles = mongoDatabase.getCollection("profiles");
        this.islands = mongoDatabase.getCollection("islands");
    }

    @Override
    public void load() {
        FindIterable<Document> islandIterable = islands.find();
        FindIterable<Document> profileIterable = profiles.find();

        try (MongoCursor<Document> cursor = islandIterable.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                zSkyBlock.getInstance().getIslandManager().getIslands()
                        .add(new IslandWrapper(document.getString("name"),
                                document.getString("leader"),
                                document.getList("members", String.class),
                                document.getList("cuboid", String.class),
                                document.getInteger("size"),
                                document.getInteger("points")).wrap());
            }
        }

        try (MongoCursor<Document> cursor = profileIterable.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                zSkyBlock.getInstance().getProfileManager().getProfiles()
                        .add(new ProfileWrapper(document.getString("player"), document.getString("island")).wrap());
            }
        }
    }

    @Override
    public void save() {
        zSkyBlock.getInstance().getIslandManager().getIslands().forEach(island -> {
            islands.replaceOne(Filters.eq("name", island.name()),
                    new DocumentWrapper(island).wrap(WrapperType.ISLAND), new UpdateOptions().upsert(true));
        });

        zSkyBlock.getInstance().getProfileManager().getProfiles().forEach(profile -> {
            profiles.replaceOne(Filters.eq("player", profile.uuid().toString())
                    , new DocumentWrapper(profile).wrap(WrapperType.PROFILE), new UpdateOptions().upsert(true));
        });
    }

    public MongoCollection<Document> getIslands() {
        return islands;
    }

    public MongoCollection<Document> getProfiles() {
        return profiles;
    }

    @Override
    public String name() {
        return "MongoDB";
    }
}
