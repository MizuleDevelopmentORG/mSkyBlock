package com.mizuledevelopment.zskyblock.profile.manager;

import com.mizuledevelopment.zskyblock.profile.Profile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProfileManager {

    private final Set<Profile> profiles = new HashSet<>();

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public boolean exists(UUID uniqueId) {
        for (Profile profile : profiles) {
            if (profile.uuid().equals(uniqueId)) {
                return true;
            }
        }

        return false;
    }
}
