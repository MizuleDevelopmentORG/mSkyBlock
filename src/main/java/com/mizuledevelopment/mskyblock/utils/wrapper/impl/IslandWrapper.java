package com.mizuledevelopment.mskyblock.utils.wrapper.impl;

import com.mizuledevelopment.mskyblock.island.Island;
import com.mizuledevelopment.mskyblock.utils.cuboid.Cuboid;
import com.mizuledevelopment.mskyblock.utils.world.location.LocationSerializer;
import com.mizuledevelopment.mskyblock.utils.wrapper.Wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record IslandWrapper(String name, String leader, List<String> members, List<String> cuboid, int size, int points) implements Wrapper<Island> {

    @Override
    public Island wrap(){
        List<UUID> members = new ArrayList<>();
        this.members.forEach(member -> members.add(UUID.fromString(member)));

        return new Island(this.name, UUID.fromString(leader), members,
                new Cuboid(LocationSerializer.deserialize(cuboid.get(0)), LocationSerializer.deserialize(cuboid.get(1))),
                this.size, this.points);
    }
}