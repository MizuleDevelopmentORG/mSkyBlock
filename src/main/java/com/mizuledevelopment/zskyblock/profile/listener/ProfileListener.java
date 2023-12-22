package com.mizuledevelopment.zskyblock.profile.listener;

import com.mizuledevelopment.zskyblock.profile.Profile;
import com.mizuledevelopment.zskyblock.zSkyBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class ProfileListener implements Listener {

    @EventHandler
    public void onProfileCreation(AsyncPlayerPreLoginEvent event) {
        if (!zSkyBlock.getInstance().getProfileManager().exists(event.getUniqueId())) {
            zSkyBlock.getInstance().getProfileManager().getProfiles().add(
                    new Profile(event.getUniqueId(), null, false)
            );
        }
    }
}
