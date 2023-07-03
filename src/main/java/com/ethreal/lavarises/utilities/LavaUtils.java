package com.ethreal.lavarises.utilities;

import com.ethreal.lavarises.LavaRises;

public class LavaUtils {
    public void startLava() {
        LavaRises.lavaTask.runTaskTimer(LavaRises.plugin, 0L, 1L);
    }

    public void pauseLava() {
        LavaRises.isPaused = true;
        LavaRises.lavaTask.cancel();
    }

    public void unpauseLava() {
        LavaRises.isPaused = false;
        LavaRises.lavaTask.runTaskTimer(LavaRises.plugin, LavaRises.currentTime, 1L);
    }
}
