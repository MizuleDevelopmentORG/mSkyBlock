package com.mizuledevelopment.zskyblock.utils.color;

import com.mizuledevelopment.zskyblock.zSkyBlock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;

import java.util.Objects;

public class Color {

    public void validate() {
        String format = zSkyBlock.getInstance().getConfiguration().getString("color-support");

        if (!Objects.requireNonNull(format).equalsIgnoreCase("modern") && !format.equalsIgnoreCase("legacy")) {
            Bukkit.getPluginManager().disablePlugin(zSkyBlock.getInstance());
            Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("[MSkyBlock] disabled due to unknown color source in configuration.yml line:17"));
            throw new RuntimeException("Invalid color type");
        }
    }


    public Component parse(String str){
        FormatType format = FormatType.valueOf
                (Objects.requireNonNull(zSkyBlock.getInstance().getConfiguration().getString("color-support")).toUpperCase());

        if (format.equals(FormatType.LEGACY)) {
            return LegacyComponentSerializer.legacyAmpersand().deserializeOr(str, Component.empty());
        } else if (format.equals(FormatType.MODERN)) {
            return MiniMessage.miniMessage().deserialize(str);
        }
        return null;
    }

    public FormatType getType(){
        return FormatType.valueOf(Objects.requireNonNull(zSkyBlock.getInstance().getConfiguration().getString("color-support")).toUpperCase());
    }
}
