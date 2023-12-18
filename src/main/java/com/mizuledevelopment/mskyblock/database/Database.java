package com.mizuledevelopment.mskyblock.database;

import com.mizuledevelopment.mskyblock.database.impl.FlatFile;
import com.mizuledevelopment.mskyblock.database.impl.Mongo;
import com.mizuledevelopment.mskyblock.database.impl.MySQL;
import com.mizuledevelopment.mskyblock.mSkyBlock;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class Database {

    private final Storage storage;

    public Database(final DatabaseType databaseType) {
        if (databaseType.equals(DatabaseType.FLAT_FILE)) {
            this.storage = new FlatFile(mSkyBlock.getInstance());
        } else if (databaseType.equals(DatabaseType.MYSQL)) {
            this.storage = new MySQL(mSkyBlock.getInstance());
        } else if (databaseType.equals(DatabaseType.MONGO)) {
            this.storage = new Mongo(mSkyBlock.getInstance());
        } else {
            Bukkit.getConsoleSender().sendMessage(MiniMessage
                    .miniMessage().deserialize("<red>[MSkyBlock] disabled due to unknown database source in configuration.yml line:1"));
            Bukkit.getPluginManager().disablePlugin(mSkyBlock.getInstance());
            throw new RuntimeException("Invalid storage type"); // better error handling
        }
        this.storage.init();
    }

    public Storage storage() {
        return this.storage;
    }
}
