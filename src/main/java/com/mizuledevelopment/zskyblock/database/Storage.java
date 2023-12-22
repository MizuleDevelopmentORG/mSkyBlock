package com.mizuledevelopment.zskyblock.database;

import java.util.UUID;

public abstract class Storage {

    public abstract void init();

    public abstract void load();

    public abstract void save();

    public abstract void loadPlayer(UUID uuid);

    public abstract String name();
}
