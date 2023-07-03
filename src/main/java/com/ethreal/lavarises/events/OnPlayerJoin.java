package com.ethreal.lavarises.events;

import com.ethreal.lavarises.LavaRisesData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (LavaRisesData.currentTime != 60) {
            event.getPlayer().showBossBar(LavaRisesData.bossBar);
        }
        //event.joinMessage(null);
    }
}
