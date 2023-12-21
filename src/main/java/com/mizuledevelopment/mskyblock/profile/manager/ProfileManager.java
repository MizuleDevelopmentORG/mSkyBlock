package com.mizuledevelopment.mskyblock.profile.manager;

import com.mizuledevelopment.mskyblock.profile.Profile;

import java.util.HashSet;
import java.util.Set;

public class ProfileManager {

    private final Set<Profile> profiles = new HashSet<>();


    public Set<Profile> getProfiles() {

        return profiles;
    }
}
