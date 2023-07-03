package com.ethreal.lavarises.utilities;

import com.ethreal.lavarises.LavaRisesData;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LavaUtils {
    public boolean startLava() {
        if (LavaRisesData.currentTime == LavaRisesData.maxTime) {
            LavaRisesData.lavaTask = new BukkitRunnable() {
                @Override
                public void run() { lavaTask(); }
            };
            LavaRisesData.lavaTask.runTaskTimer(LavaRisesData.plugin, 0L, 20L);
            LavaRisesData.server.getOnlinePlayers().forEach(player -> {
                if(!hasActiveBossBar(player, LavaRisesData.bossBar)) { player.showBossBar(LavaRisesData.bossBar); }
            });
            return true;
        } else { return false; }
    }

    public void stopLava() {
        LavaRisesData.lavaTask.cancel();
    }

    public void restartLava() {
        stopLava();
        LavaRisesData.currentTime = LavaRisesData.maxTime;
        LavaRisesData.lavaTask = new BukkitRunnable() {
            @Override
            public void run() {
                lavaTask();
            }
        };
        LavaRisesData.lavaTask.runTaskTimer(LavaRisesData.plugin, 0L, 20L);
    }

    public void resumeLava() {
        stopLava();
        LavaRisesData.lavaTask = new BukkitRunnable() {
            @Override
            public void run() {
                lavaTask();
            }
        };
        LavaRisesData.lavaTask.runTaskTimer(LavaRisesData.plugin, 0L, 20L);
    }

    private void lavaTask() {
        if (LavaRisesData.maxReached) {
            LavaRisesData.server.getOnlinePlayers().forEach(player -> {
                LavaRisesData.bossBar.name(MiniMessage.miniMessage().deserialize(
                        "<green>Lava is at max height!</green>"
                )).progress(1f);
            });
            stopLava();
        }

        if (LavaRisesData.currentTime > 0) {
            LavaRisesData.server.getOnlinePlayers().forEach(player -> {
                LavaRisesData.bossBar.name(MiniMessage.miniMessage().deserialize(
                        "Time until lava rises: " + LavaRisesData.currentTime
                )).progress((float)LavaRisesData.currentTime/60f);
            });
            LavaRisesData.currentTime -= 1;
        } else {
            LavaRisesData.bossBar.name(MiniMessage.miniMessage().deserialize(
                    "Time until lava rises: " + LavaRisesData.currentTime
            )).progress(0f);
            lavaSpawn();
            LavaRisesData.currentTime = LavaRisesData.maxTime;
        }
    }

    public void lavaSpawn() {
        LavaRisesData.level += 1; // Increment level by 1
        announceLevel();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (LavaRisesData.level > LavaRisesData.levelMax) {
                    LavaRisesData.maxReached = true;
                    return;
                }
                for (double x = LavaRisesData.xMin; x <= LavaRisesData.xMax; x++) {
                    for(double z = LavaRisesData.zMin; z <= LavaRisesData.zMax; z++) {
                        Block block = LavaRisesData.world.getBlockAt((int) x, (int) LavaRisesData.level, (int) z);
                        if (block.getType().isAir() ||
                            block.isPassable()
                        ) {
                            block.setType(Material.LAVA);
                        }
                    }
                }
            }
        }.runTask(LavaRisesData.plugin); // Can't be async :cry:
    }

    public void lavaClear() {
        LavaRisesData.level = LavaRisesData.levelMin;
        announceLevel();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (double y = LavaRisesData.levelMin; y <= LavaRisesData.levelMax; y++)
                    for (double x = LavaRisesData.xMin; x <= LavaRisesData.xMax; x++)
                        for (double z = LavaRisesData.zMin; z <= LavaRisesData.zMax; z++) {
                            Block block = LavaRisesData.world.getBlockAt((int) x, (int) y, (int) z);
                            if (block.getType() == Material.LAVA) {
                                block.setType(Material.AIR);
                            }
                        }
            }
        }.runTask(LavaRisesData.plugin);
    }

    public void lavaDown() {
        announceLevel();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (double x = LavaRisesData.xMin; x <= LavaRisesData.xMax; x++)
                    for (double z = LavaRisesData.zMin; z <= LavaRisesData.zMax; z++) {
                        Block block = LavaRisesData.world.getBlockAt((int) x, (int) LavaRisesData.level+1, (int) z);
                        if (block.getType() == Material.LAVA) {
                            block.setType(Material.AIR);
                        }
                    }
            }
        }.runTask(LavaRisesData.plugin);
        if (LavaRisesData.level - 1 >= LavaRisesData.levelMin) { LavaRisesData.level -= 1; } // Lower level by 1, unless it is lower than the minimum
    }

    public void announceLevel() {
        LavaRisesData.server.sendMessage(MiniMessage.miniMessage().deserialize(
                "<green>Lava level is now</green> <red>Y:" +(int) LavaRisesData.level + "</red>"
        ));
    }

    private boolean hasActiveBossBar(Player player, BossBar bossBar) {
        for (BossBar bar : player.activeBossBars()) {
            if (bar == bossBar) { return true; }
        }
        return false;
    }
}
