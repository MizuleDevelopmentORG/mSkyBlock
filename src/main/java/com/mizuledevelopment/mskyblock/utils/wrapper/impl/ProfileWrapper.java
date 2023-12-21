package com.mizuledevelopment.mskyblock.utils.wrapper.impl;

import com.mizuledevelopment.mskyblock.profile.Profile;
import com.mizuledevelopment.mskyblock.utils.wrapper.Wrapper;

import java.util.UUID;

public record ProfileWrapper(String player, String island) implements Wrapper<Profile> {

    public Profile wrap(){
        return new Profile(UUID.fromString(player), island);
    }
}
