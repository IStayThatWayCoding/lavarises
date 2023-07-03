package com.ethreal.lavarises;

import org.bukkit.plugin.java.JavaPlugin;

public final class LavaRises extends JavaPlugin {
    public static Double level = 0.0; // Initial block level for the lava
    @Override
    public void onEnable() {


        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
