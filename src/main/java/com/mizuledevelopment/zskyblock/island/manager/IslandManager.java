package com.mizuledevelopment.zskyblock.island.manager;

import com.mizuledevelopment.zskyblock.island.Island;
import com.mizuledevelopment.zskyblock.zSkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class IslandManager {

    private final int x;
    private final HashSet<Island> islands = new HashSet<>();

    public IslandManager(){
        this.x = zSkyBlock.getInstance().getData().getInt("x");
    }

    public void createIsland(){

    }

    private Location calculateLocation(){
        int maxIslandSize = zSkyBlock.getInstance().getConfiguration().getInt("max-island-size");
        int islandRadius = zSkyBlock.getInstance().getConfiguration().getInt("island-radius");

        int lastX;

        if (islands.isEmpty()) {
            lastX = zSkyBlock.getInstance().getConfiguration().getInt("first-island.x");
            int y = zSkyBlock.getInstance().getConfiguration().getInt("first-island.y");
            int z = zSkyBlock.getInstance().getConfiguration().getInt("first-island.z");

            return new Location(zSkyBlock.getInstance().getWorldManager().getWorld(), lastX, y, z);
        }

        lastX = zSkyBlock.getInstance().getData().getInt("x");
        int y = zSkyBlock.getInstance().getData().getInt("first-island.y");
        int z = zSkyBlock.getInstance().getData().getInt("first-island.z");

        return new Location(zSkyBlock.getInstance().getWorldManager().getWorld(), (lastX + (islandRadius + maxIslandSize)), y, z);
    }

    public HashSet<Island> getIslands() {
        return islands;
    }

    public int getX() {
        return x;
    }
}
