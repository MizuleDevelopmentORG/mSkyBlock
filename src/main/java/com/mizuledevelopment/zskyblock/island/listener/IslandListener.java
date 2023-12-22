package com.mizuledevelopment.zskyblock.island.listener;

import com.mizuledevelopment.zskyblock.zSkyBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IslandListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getView().title().equals(zSkyBlock.getInstance().getColor()
                .parse(zSkyBlock.getInstance().getConfiguration().getString("islands.inventory.title")))) {
            event.setCancelled(true);
        }
    }
}
