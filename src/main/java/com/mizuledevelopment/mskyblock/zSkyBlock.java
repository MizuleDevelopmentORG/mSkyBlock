package com.mizuledevelopment.mskyblock;

import com.mizuledevelopment.mskyblock.database.Database;
import com.mizuledevelopment.mskyblock.database.DatabaseType;
import com.mizuledevelopment.mskyblock.island.manager.IslandManager;
import com.mizuledevelopment.mskyblock.profile.manager.ProfileManager;
import com.mizuledevelopment.mskyblock.utils.color.Color;
import com.mizuledevelopment.mskyblock.utils.config.Config;
import com.mizuledevelopment.mskyblock.utils.world.WorldManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Locale;
import java.util.Objects;

public final class zSkyBlock extends JavaPlugin {

    private static zSkyBlock instance;
    private Config configuration;
    private Database database;
    private Color color;
    private WorldManager worldManager;
    private IslandManager islandManager;
    private ProfileManager profileManager;

    public static zSkyBlock getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.initializeConfig();

        this.database = new Database(DatabaseType.valueOf(Objects.requireNonNull(getConfiguration().getString("database")).toUpperCase(Locale.ROOT)));
        this.database.getStorage().load();

        this.color = new Color();
        this.color.validate();

        this.worldManager = new WorldManager();
        this.worldManager.create();
        
        this.islandManager = new IslandManager();
        this.profileManager = new ProfileManager();
    }

    @Override
    public void onDisable() {
        this.database.getStorage().save();
    }

    private void initializeConfig() {
        this.configuration = new Config(this, new File(getDataFolder(), "configuration.yml"),
                new YamlConfiguration(), "configuration.yml");

        this.configuration.create();
    }

    public Database getDatabase() {
        return database;
    }

    public Color getColor() {
        return color;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public IslandManager getIslandManager() {
        return islandManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration.getConfiguration();
    }
}
