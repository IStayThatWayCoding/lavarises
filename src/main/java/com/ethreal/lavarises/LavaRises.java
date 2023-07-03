package com.ethreal.lavarises;

import com.ethreal.lavarises.commands.LavaCommand;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LavaRises extends JavaPlugin {
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        LavaRisesData.plugin = this;
        LavaRisesData.server = getServer();
        LavaRisesData.world = LavaRisesData.server.getWorlds().get(0);
        // World border stuff.
        LavaRisesData.worldBorder = LavaRisesData.world.getWorldBorder();
        Location center = LavaRisesData.worldBorder.getCenter();
        double radius = LavaRisesData.worldBorder.getSize() / 2d;
        LavaRisesData.xMin = center.getX() - radius;
        LavaRisesData.xMax = center.getX() + radius;
        LavaRisesData.zMin = center.getZ() - radius;
        LavaRisesData.zMax = center.getZ() + radius;

        LavaRisesData.level = getConfig().getDouble("Settings.minY");
        LavaRisesData.levelMax = getConfig().getDouble("Settings.maxY");
        LavaRisesData.maxTime = getConfig().getInt("Settings.timeBetween");
        LavaRisesData.currentTime = LavaRisesData.maxTime;

        LavaRisesData.bossBar = BossBar.bossBar(MiniMessage.miniMessage().deserialize(
                "Time Until Lava Rises"
        ), 0, BossBar.Color.RED, BossBar.Overlay.PROGRESS);

        getLogger().info("Plugin enabled");

        Objects.requireNonNull(getCommand("lava")).setExecutor(new LavaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
