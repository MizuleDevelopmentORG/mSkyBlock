package com.mizuledevelopment.zskyblock;

import com.mizuledevelopment.zskyblock.board.BoardAdapter;
import com.mizuledevelopment.zskyblock.database.Database;
import com.mizuledevelopment.zskyblock.database.DatabaseType;
import com.mizuledevelopment.zskyblock.island.manager.IslandManager;
import com.mizuledevelopment.zskyblock.listener.SkyBlockListener;
import com.mizuledevelopment.zskyblock.profile.manager.ProfileManager;
import com.mizuledevelopment.zskyblock.utils.color.Color;
import com.mizuledevelopment.zskyblock.utils.command.manager.CommandManager;
import com.mizuledevelopment.zskyblock.utils.config.Config;
import com.mizuledevelopment.zskyblock.utils.world.WorldManager;
import fr.mrmicky.fastboard.adventure.FastBoard;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class zSkyBlock extends JavaPlugin {

    private static zSkyBlock instance;
    private Config configuration;
    private Config scoreboard;
    private Config language;
    private Database database;
    private Color color;
    private WorldManager worldManager;
    private IslandManager islandManager;
    private ProfileManager profileManager;
    private BoardAdapter boardAdapter;

    public static zSkyBlock getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.initializeConfig();

        this.database = new Database(DatabaseType.valueOf(Objects.requireNonNull(getConfiguration().getString("database")).toUpperCase(Locale.ROOT)));
        this.database.getStorage().load();

        this.boardAdapter = new BoardAdapter();

        this.color = new Color();
        this.color.validate();

        this.worldManager = new WorldManager();
        this.worldManager.create();
        
        this.islandManager = new IslandManager();
        this.profileManager = new ProfileManager();

        this.initializeCommands();
        this.initializeListeners(Bukkit.getPluginManager());
        this.initializeScoreboard();

        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<blue>---<white>------<blue>---"));
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<blue>zSkyBlock <white>Successfully <green>enabled."));
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(" "));
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<white>Database: <blue>" + this.getConfiguration().getString("database")));
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<white>Color: <blue>" + this.getConfiguration().getString("color-support")));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<white>Thank you for using zSkyBlock! In case of any issues join our discord - <blue>https://discord.gg/82fSnmm5Ds"));
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<blue>---<white>------<blue>---"));
    }

    @Override
    public void onDisable() {
        this.database.getStorage().save();
    }

    private void initializeConfig() {
        this.configuration = new Config(this, new File(getDataFolder(), "configuration.yml"),
                new YamlConfiguration(), "configuration.yml");
        this.scoreboard = new Config(this, new File(getDataFolder(), "scoreboard.yml"),
                new YamlConfiguration(), "scoreboard.yml");
        this.language = new Config(this, new File(getDataFolder(), "language.yml"),
                new YamlConfiguration(), "language.yml");

        this.configuration.create();
        this.scoreboard.create();
        this.language.create();
    }

    private void initializeListeners(PluginManager pluginManager){
        List.of(
                new SkyBlockListener()
        ).forEach(listener -> pluginManager.registerEvents(listener, this));
    }

    private void initializeCommands(){
        CommandManager islandCommandManager = new CommandManager(this.getCommand("island"));

        islandCommandManager.registerCommands();
    }

    private void initializeScoreboard() {
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard fastBoard : getBoardAdapter().getMap().values()) {
                getBoardAdapter().updateBoard(fastBoard);
            }
        }, 0L, 20L);
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

    public BoardAdapter getBoardAdapter() {
        return boardAdapter;
    }

    public YamlConfiguration getScoreboard() { return this.scoreboard.getConfiguration(); }

    public YamlConfiguration getLanguage(){ return this.language.getConfiguration(); }

    public YamlConfiguration getConfiguration() {
        return this.configuration.getConfiguration();
    }
}
