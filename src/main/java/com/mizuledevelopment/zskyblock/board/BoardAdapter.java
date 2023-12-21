package com.mizuledevelopment.zskyblock.board;

import com.mizuledevelopment.zskyblock.zSkyBlock;
import fr.mrmicky.fastboard.adventure.FastBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BoardAdapter {

    private final Map<UUID, FastBoard> map = new HashMap<>();

    public void updateBoard(FastBoard board) {
        for (final String lines : zSkyBlock.getInstance().getScoreboard().getStringList("scoreboard.lines")) {
            board.updateLines(zSkyBlock.getInstance().getColor().parse(lines));
        }
    }

    public Map<UUID, FastBoard> getMap() {
        return map;
    }
}
