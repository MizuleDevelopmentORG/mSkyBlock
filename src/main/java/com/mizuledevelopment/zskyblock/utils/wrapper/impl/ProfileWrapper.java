package com.mizuledevelopment.zskyblock.utils.wrapper.impl;

import com.mizuledevelopment.zskyblock.profile.Profile;
import com.mizuledevelopment.zskyblock.utils.wrapper.Wrapper;

import java.util.UUID;

public record ProfileWrapper(String player, String island, boolean reclaimed) implements Wrapper<Profile> {

    public Profile wrap(){
        return new Profile(UUID.fromString(player), island, reclaimed);
    }
}
