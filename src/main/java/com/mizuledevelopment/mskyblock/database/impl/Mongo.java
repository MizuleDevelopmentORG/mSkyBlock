package com.mizuledevelopment.mskyblock.database.impl;

import com.mizuledevelopment.mskyblock.database.Storage;
import com.mizuledevelopment.mskyblock.mSkyBlock;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Objects;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Mongo extends Storage {

    private MongoCollection<Document> islands;
    private MongoCollection<Document> profiles;

    @Override
    public void init(){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(Objects.requireNonNull(mSkyBlock.getInstance().getConfiguration().getString("mongo.uri"))))
                .codecRegistry(pojoCodecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mSkyBlock");

        this.profiles = mongoDatabase.getCollection("profiles");
        this.islands = mongoDatabase.getCollection("islands");
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }

    @Override
    public String name() {
        return "MongoDB";
    }
}
