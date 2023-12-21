package com.mizuledevelopment.zskyblock.island.command.player;

import com.mizuledevelopment.zskyblock.utils.command.Command;
import com.mizuledevelopment.zskyblock.zSkyBlock;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class IslandCreateCommand extends Command {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(zSkyBlock.getInstance().getColor().parse(zSkyBlock.getInstance().getLanguage().getString("command.only-player")));
            return;
        }

        if (!(player.hasPermission("zskyblock.command.create"))) {
            sender.sendMessage(zSkyBlock.getInstance().getColor().parse(zSkyBlock.getInstance().getLanguage().getString("command.no-permissions")));
            return;
        }

        if (zSkyBlock.getInstance().getConfiguration().getBoolean("islands.multiple-selection")) {
            Inventory inventory = Bukkit.createInventory(
                    player, zSkyBlock.getInstance().getConfiguration().getInt("islands.inventory.size"),
                    zSkyBlock.getInstance().getColor().parse(zSkyBlock.getInstance().getConfiguration().getString("islands.inventory.title"))
            );

            for (final String schematic : Objects.requireNonNull(zSkyBlock.getInstance().getConfiguration().getConfigurationSection("islands.schematics")).getKeys(false)) {
                ItemStack itemStack = new ItemStack(Material.valueOf(zSkyBlock.getInstance().getConfiguration().getString("islands.schematics." + schematic + ".item")));
                ItemMeta meta = itemStack.getItemMeta();
                meta.displayName(zSkyBlock.getInstance().getColor().parse(zSkyBlock.getInstance().getConfiguration().getString("islands.schematics." + schematic + ".name")));
                ArrayList<Component> components = new ArrayList<>();
                for (final String lore : zSkyBlock.getInstance().getConfiguration().getStringList("islands.schematics." + schematic + ".lore")) {
                    components.add(zSkyBlock.getInstance().getColor().parse(lore));
                }
                meta.lore(components);

                inventory.setItem(zSkyBlock.getInstance().getConfiguration().getInt("islands.schematics." + schematic + ".slot"), itemStack);
            }

            player.openInventory(inventory);
        }
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public boolean allow() {
        return true;
    }

    @Override
    public Component getUsage() {
        return zSkyBlock.getInstance().getColor().parse(zSkyBlock.getInstance().getLanguage().getString("island.create-usage"));
    }
}
