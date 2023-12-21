package com.mizuledevelopment.zskyblock.utils.world;

import com.mizuledevelopment.zskyblock.zSkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.util.Objects;

public class WorldManager {

    private World world;

    public void create(){
        if (zSkyBlock.getInstance().getConfiguration().getString("islands-world") == null) {
            Bukkit.getPluginManager().disablePlugin(zSkyBlock.getInstance());
            throw new RuntimeException("World for islands cannot be null");
        }
        WorldCreator worldCreator = new WorldCreator(Objects.requireNonNull(zSkyBlock.getInstance().getConfiguration().getString("islands-world")));
        worldCreator.type(WorldType.FLAT);
        worldCreator.generateStructures(false);
        this.world = worldCreator.createWorld();
    }

    public World getWorld() {
        return world;
    }
}
