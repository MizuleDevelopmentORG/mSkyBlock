package com.mizuledevelopment.zskyblock.profile.manager;

import com.mizuledevelopment.zskyblock.profile.Profile;

import java.util.*;

public class ProfileManager {

    private final Map<UUID, Profile> profiles = new HashMap<>();

    public Map<UUID, Profile> getProfiles() {
        return profiles;
    }

    public boolean exists(UUID uniqueId) {
        for (final UUID uuid : profiles.keySet()) {
            if (uuid.equals(uniqueId)) {
                return true;
            }
        }

        return false;
    }
}
