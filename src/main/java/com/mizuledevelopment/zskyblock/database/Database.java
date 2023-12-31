package com.mizuledevelopment.zskyblock.database;

import com.mizuledevelopment.zskyblock.database.impl.Mongo;
import com.mizuledevelopment.zskyblock.database.impl.MySQL;
import com.mizuledevelopment.zskyblock.zSkyBlock;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class Database {

    private final Storage storage;

    public Database(final DatabaseType databaseType) {
        if (databaseType.equals(DatabaseType.MYSQL)) {
            this.storage = new MySQL();
        } else if (databaseType.equals(DatabaseType.MONGO)) {
            this.storage = new Mongo();
        } else {
            Bukkit.getConsoleSender().sendMessage(MiniMessage
                    .miniMessage().deserialize("<red>[MSkyBlock] disabled due to unknown database source in configuration.yml line:1"));
            Bukkit.getPluginManager().disablePlugin(zSkyBlock.getInstance());
            throw new RuntimeException("Invalid storage type"); // better error handling
        }
        this.storage.init();
    }

    public Storage getStorage() {
        return this.storage;
    }
}
