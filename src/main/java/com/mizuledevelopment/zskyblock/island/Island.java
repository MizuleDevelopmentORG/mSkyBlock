package com.mizuledevelopment.zskyblock.island;

import com.mizuledevelopment.zskyblock.utils.cuboid.Cuboid;

import java.util.List;
import java.util.UUID;

public record Island(String name, UUID leader, List<UUID> members, Cuboid cuboid, int size, int points) {}
