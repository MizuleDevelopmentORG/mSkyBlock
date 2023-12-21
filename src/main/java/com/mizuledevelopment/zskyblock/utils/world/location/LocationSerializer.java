package com.mizuledevelopment.zskyblock.utils.world.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializer {

    public static String serialize(Location location, World world) {
        if (location == null)
            return null;

        return world.getName() + "," +
                location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getYaw() + "," +
                location.getPitch();
    }

    public static Location deserialize(String location) {
        if (location == null || location.isEmpty())
            return null;

        String[] parts = location.split(",");
        return new Location(
                Bukkit.getWorld(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Float.parseFloat(parts[4]),
                Float.parseFloat(parts[5])
        );
    }
}