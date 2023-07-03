package com.ethreal.lavarises;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LavaRisesData {
    // All data
    // Game settings
    public static double level = 26.0; // Initial block level (-1) for the lava
    public static double levelMax = 100.0; // Lava won't go over this.
    public static final double levelMin = level; // For cleaning the lava. Same as the starting level
    public static int maxTime = 10; // How long between the lava rises
    public static int currentTime = maxTime;
    public static boolean maxReached = false; // If the max height has been reached
    // Stuff, I guess
    public static Plugin plugin;
    public static Server server;
    public static BukkitRunnable lavaTask;
    public static BossBar bossBar;
    // Used to calculate where to spawn lava
    public static WorldBorder worldBorder;
    public static World world;
    public static double xMin;
    public static double xMax;
    public static double zMin;
    public static double zMax;
}
