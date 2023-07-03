package com.ethreal.lavarises;

import com.ethreal.lavarises.commands.LavaCommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public final class LavaRises extends JavaPlugin {
    public static Double level = 0.0; // Initial block level for the lava
    public static Plugin plugin;
    public static Server server;
    public static boolean isPaused = false;
    public static int currentTime = 0;
    public static BukkitRunnable lavaTask = new BukkitRunnable() {
        @Override
        public void run() {
            if (currentTime < 60) {
                server.sendMessage(MiniMessage.miniMessage().deserialize(
                        String.valueOf(currentTime)
                ));
                currentTime += 1;
            } else {
                level += 1;
                currentTime = 0;
            }
        }
    };
    @Override
    public void onEnable() {
        plugin = this;
        server = getServer();
        getLogger().info("Plugin enabled");

        Objects.requireNonNull(getCommand("lava")).setExecutor(new LavaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
