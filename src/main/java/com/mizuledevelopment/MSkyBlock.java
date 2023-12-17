package com.mizuledevelopment;

import com.mizuledevelopment.database.Database;
import com.mizuledevelopment.database.DatabaseType;
import com.mizuledevelopment.utils.color.Color;
import com.mizuledevelopment.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class MSkyBlock extends JavaPlugin {

    private static MSkyBlock instance;
    private Config configuration;
    private Database database;
    private Color color;

    @Override
    public void onEnable() {
        instance = this;

        this.initializeConfig();

        this.database = new Database();
        this.database.initialize(DatabaseType.valueOf(Objects.requireNonNull(
                this.getConfiguration().getString("database")).toUpperCase()));

        this.color = new Color();
        this.color.validate();
    }

    public static MSkyBlock getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {

    }

    private void initializeConfig(){
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

    public YamlConfiguration getConfiguration(){
        return this.configuration.getConfiguration();
    }
}
