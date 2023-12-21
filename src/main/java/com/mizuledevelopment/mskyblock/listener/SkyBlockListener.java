package com.mizuledevelopment.mskyblock.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SkyBlockListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
    }
}
