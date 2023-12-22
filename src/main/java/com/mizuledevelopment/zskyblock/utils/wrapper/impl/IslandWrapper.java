package com.mizuledevelopment.zskyblock.utils.wrapper.impl;

import com.mizuledevelopment.zskyblock.island.Island;
import com.mizuledevelopment.zskyblock.utils.cuboid.Cuboid;
import com.mizuledevelopment.zskyblock.utils.world.location.LocationSerializer;
import com.mizuledevelopment.zskyblock.utils.wrapper.Wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IslandWrapper implements Wrapper<Island> {

    private String name;
    private String leader;
    private List<String> members;
    private List<String> cuboid;
    private int size;
    private int points;

    public IslandWrapper(String name, String leader, List<String> members, List<String> cuboid, int size, int points){
        this.name = name;
        this.leader = leader;
        this.members = members;
        this.cuboid = cuboid;
        this.size = size;
        this.points = points;
    }

    @Override
    public Island wrap(){
        List<UUID> members = new ArrayList<>();
        this.members.forEach(member -> members.add(UUID.fromString(member)));

        return new Island(this.name, UUID.fromString(leader), members,
                new Cuboid(LocationSerializer.deserialize(cuboid.get(0)), LocationSerializer.deserialize(cuboid.get(1))),
                this.size, this.points);
    }

    public String getName() {
        return name;
    }

    public List<String> getMembers() {
        return members;
    }

    public int getPoints() {
        return points;
    }

    public List<String> getCuboid() {
        return cuboid;
    }

    public int getSize() {
        return size;
    }

    public String getLeader() {
        return leader;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setCuboid(List<String> cuboid) {
        this.cuboid = cuboid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addMember(String profileUUID) {
        List<String> members = new ArrayList<>(this.members);
        members.add(profileUUID);
        this.members = members;
    }
}