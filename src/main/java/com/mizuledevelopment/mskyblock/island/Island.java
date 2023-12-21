package com.mizuledevelopment.mskyblock.island;

import com.mizuledevelopment.mskyblock.utils.cuboid.Cuboid;

import java.util.List;
import java.util.UUID;

public record Island(String name, UUID leader, List<UUID> members, Cuboid cuboid, int size, int points) {}
