package com.mizuledevelopment.mskyblock.database;

public abstract class Storage {

    public abstract void init();

    public abstract void load();

    public abstract void save();

    public abstract String name();
}
