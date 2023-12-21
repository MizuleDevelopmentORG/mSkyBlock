package com.mizuledevelopment.zskyblock.profile.manager;

import com.mizuledevelopment.zskyblock.profile.Profile;

import java.util.HashSet;
import java.util.Set;

public class ProfileManager {

    private final Set<Profile> profiles = new HashSet<>();

    public Set<Profile> getProfiles() {
        return profiles;
    }
}
