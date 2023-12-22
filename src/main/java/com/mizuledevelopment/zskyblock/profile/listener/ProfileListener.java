package com.mizuledevelopment.zskyblock.profile.listener;

import com.mizuledevelopment.zskyblock.zSkyBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class ProfileListener implements Listener {

    @EventHandler
    public void onProfileCreation(AsyncPlayerPreLoginEvent event) {
        zSkyBlock.getInstance().getDatabase().getStorage().loadPlayer(event.getUniqueId());
    }
}
