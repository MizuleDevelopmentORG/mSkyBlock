package com.mizuledevelopment.mskyblock;

import com.mizuledevelopment.mskyblock.database.Database;
import com.mizuledevelopment.mskyblock.database.DatabaseType;
import com.mizuledevelopment.mskyblock.utils.color.Color;
import com.mizuledevelopment.mskyblock.utils.config.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Locale;
import java.util.Objects;

public final class mSkyBlock extends JavaPlugin {

    private static mSkyBlock instance;
    private Config configuration;
    private Database database;
    private Color color;

    public static mSkyBlock getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.initializeConfig();

        this.database = new Database(DatabaseType.valueOf(Objects.requireNonNull(getConfiguration().getString("database")).toUpperCase(Locale.ROOT)));

        this.color = new Color();
        this.color.validate();
    }

    @Override
    public void onDisable() {

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

    public YamlConfiguration getConfiguration() {
        return this.configuration.getConfiguration();
    }
}
