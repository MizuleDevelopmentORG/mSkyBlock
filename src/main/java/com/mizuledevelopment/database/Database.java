package com.mizuledevelopment.database;

import com.mizuledevelopment.MSkyBlock;
import com.mizuledevelopment.database.impl.FlatFile;
import com.mizuledevelopment.database.impl.Mongo;
import com.mizuledevelopment.database.impl.MySQL;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class Database {

    private final FlatFile flatFile;
    private final Mongo mongo;
    private final MySQL mySQL;

    public Database() {
        this.flatFile = new FlatFile(MSkyBlock.getInstance());
        this.mongo = new Mongo(MSkyBlock.getInstance());
        this.mySQL = new MySQL(MSkyBlock.getInstance());
    }

    public void initialize(DatabaseType type) {
        if (type.equals(DatabaseType.FLAT_FILE)) {
            this.flatFile.init();
        } else if (type.equals(DatabaseType.MYSQL)) {
            this.mongo.init();
        } else if (type.equals(DatabaseType.MONGO)) {
            this.mySQL.init();
        } else {
            Bukkit.getConsoleSender().sendMessage(MiniMessage
                    .miniMessage().deserialize("<red>[MSkyBlock] disabled due to unknown database source in configuration.yml line:1"));
            Bukkit.getPluginManager().disablePlugin(MSkyBlock.getInstance());
        }
    }

    public FlatFile getFlatFile() {
        return flatFile;
    }

    public Mongo getMongo() {
        return mongo;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
