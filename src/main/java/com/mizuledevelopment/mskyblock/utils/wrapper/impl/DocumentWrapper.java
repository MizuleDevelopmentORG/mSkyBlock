package com.mizuledevelopment.mskyblock.utils.wrapper.impl;

import com.mizuledevelopment.mskyblock.island.Island;
import com.mizuledevelopment.mskyblock.profile.Profile;
import com.mizuledevelopment.mskyblock.utils.cuboid.Cuboid;
import com.mizuledevelopment.mskyblock.utils.world.location.LocationSerializer;
import com.mizuledevelopment.mskyblock.utils.wrapper.WrapperType;
import com.mizuledevelopment.mskyblock.zSkyBlock;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentWrapper {

    private Island island;
    private Profile profile;

    public DocumentWrapper(Island island){
        this.island = island;
    }

    public DocumentWrapper(Profile profile) {
        this.profile = profile;
    }

    public Document wrap(WrapperType type){
        Document document = new Document();

        if (type.equals(WrapperType.ISLAND)) {
            document.put("name", this.island.name());
            document.put("leader", this.island.leader().toString());
            document.put("members", toStringList(this.island.members()));
            document.put("cuboid", toStringList(this.island.cuboid()));
            document.put("size", this.island.size());
            document.put("points", this.island.points());
        } else if (type.equals(WrapperType.PROFILE)) {
            document.put("player", this.profile.uuid().toString());
            document.put("island", this.profile.islandName());
        }

        return document;
    }

    public List<String> toStringList(List<UUID> uuids){
        List<String> stringList = new ArrayList<>();
        uuids.forEach(uuid -> stringList.add(uuid.toString()));
        return stringList;
    }

    public List<String> toStringList(Cuboid cuboid){
        List<String> stringList = new ArrayList<>();
        stringList.add(LocationSerializer.serialize(cuboid.getLocation1(), zSkyBlock.getInstance().getWorldManager().getWorld()));
        stringList.add(LocationSerializer.serialize(cuboid.getLocation2(), zSkyBlock.getInstance().getWorldManager().getWorld()));
        return stringList;
    }

    public Island getIsland() {
        return island;
    }

    public Profile getProfile() {
        return profile;
    }

}