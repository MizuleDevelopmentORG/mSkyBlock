package com.mizuledevelopment.zskyblock.listener;

import com.mizuledevelopment.zskyblock.zSkyBlock;
import fr.mrmicky.fastboard.adventure.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class SkyBlockListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(null);

        if (!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().teleport(new Location(Bukkit.getWorld(Objects.requireNonNull(zSkyBlock.getInstance().getConfiguration().getString("spawn.world"))),
                    zSkyBlock.getInstance().getConfiguration().getInt("spawn.x"),
                    zSkyBlock.getInstance().getConfiguration().getInt("spawn.y"),
                    zSkyBlock.getInstance().getConfiguration().getInt("spawn.z")));
        }

        FastBoard board = new FastBoard(event.getPlayer());
        board.updateTitle(zSkyBlock.getInstance().getColor().parse(zSkyBlock.getInstance().getScoreboard().getString("scoreboard.title")));
        zSkyBlock.getInstance().getBoardAdapter().getMap().put(event.getPlayer().getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        FastBoard board = zSkyBlock.getInstance().getBoardAdapter().getMap().remove(event.getPlayer().getUniqueId());
        if (board != null) {
            board.delete();
        }
    }
}
