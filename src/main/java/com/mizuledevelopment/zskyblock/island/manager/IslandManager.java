package com.mizuledevelopment.zskyblock.island.manager;

import com.mizuledevelopment.zskyblock.island.Island;

import java.util.HashSet;
import java.util.Set;

public class IslandManager {

    private final Set<Island> islands = new HashSet<>();

    public Set<Island> getIslands() {
        return islands;
    }
}
