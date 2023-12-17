package com.mizuledevelopment.utils.color;

import com.mizuledevelopment.MSkyBlock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;

import java.util.Objects;

public class Color {

    public void validate(){
        String format = MSkyBlock.getInstance().getConfiguration().getString("color-support");
        if (format != null && !format.equalsIgnoreCase("legacy")
                || format != null && format.equalsIgnoreCase("modern")) {
            Bukkit.getConsoleSender().sendMessage(MiniMessage
                    .miniMessage().deserialize("<red>[MSkyBlock] disabled due to unknown color source in configuration.yml line:17"));
            Bukkit.getPluginManager().disablePlugin(MSkyBlock.getInstance());
        }
    }

    public Component parse(String str){
        FormatType format = FormatType.valueOf
                (Objects.requireNonNull(MSkyBlock.getInstance().getConfiguration().getString("color-support")).toUpperCase());

        if (format.equals(FormatType.LEGACY)) {
            return LegacyComponentSerializer.legacyAmpersand().deserializeOr(str, Component.empty());
        } else if (format.equals(FormatType.MODERN)) {
            return MiniMessage.miniMessage().deserialize(str);
        }
        return null;
    }
}
