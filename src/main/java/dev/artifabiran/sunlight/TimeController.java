package dev.artifabiran.sunlight;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

public class TimeController {
    private long tickCounter;
    private static final int DAY_NIGHT_DURATION = 12000;
    private float daytimeMultiplier;
    private float nighttimeMultiplier;
    BukkitScheduler scheduler;

    World world;

    public TimeController(Sunlight sunlight, long tickCounter, int daytimeMultiplier, int nighttimeMultiplier) {
        this.daytimeMultiplier = daytimeMultiplier;
        this.nighttimeMultiplier = nighttimeMultiplier;
        this.tickCounter = tickCounter;

        this.scheduler = Bukkit.getScheduler();
        world = Bukkit.getWorld("world");

        scheduler.runTaskTimerAsynchronously(sunlight, this::updateTime, 0L, 1L);
    }

    private void updateTime() {
        long time = world.getTime();

        if (time <= 12000) { // Day
            if (daytimeMultiplier >= 1.0) {
                world.setTime(time + (long) daytimeMultiplier);
            } else if (daytimeMultiplier > 0) {
                if (tickCounter > 1.0 / daytimeMultiplier) {
                    tickCounter = 0;
                    world.setTime(time + 1);
                }
                tickCounter++;
            }
        } else { // night
            if (nighttimeMultiplier >= 1.0) {
                world.setTime(time + (long) nighttimeMultiplier);
            } else if (nighttimeMultiplier > 0) {
                if (tickCounter > 1.0 / nighttimeMultiplier) {
                    tickCounter = 0;
                    world.setTime(time + 1);
                }
                tickCounter++;
            }
        }
    }

    private double calculateMultiplier(int timeSeconds) {
        return DAY_NIGHT_DURATION / (timeSeconds * 20);
    }
    public long getTickCounter() {
        return tickCounter;
    }


    public void setTickCounter(long tickCounter) {
        this.tickCounter = tickCounter;
    }
}

