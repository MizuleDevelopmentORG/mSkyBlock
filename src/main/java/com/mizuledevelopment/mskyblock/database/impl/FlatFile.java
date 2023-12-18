package com.mizuledevelopment.mskyblock.database.impl;

import com.mizuledevelopment.mskyblock.database.Storage;
import com.mizuledevelopment.mskyblock.mSkyBlock;

public class FlatFile extends Storage {

    private final mSkyBlock plugin;

    public FlatFile(mSkyBlock plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init(){

    }

    @Override
    public String name() {
        return "Flatfile";
    }
}
