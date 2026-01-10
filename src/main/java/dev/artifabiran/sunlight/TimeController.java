package dev.artifabiran.sunlight;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

public class TimeController {
    private long tickCounter;
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

        scheduler.runTaskTimer(sunlight, this::updateTime, 0L, 1L);
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

    public void setDaytimeMultiplier(float value) {
        daytimeMultiplier = value;
    }

    public void setNighttimeMultiplier(float value) {
        nighttimeMultiplier = value;
    }

    public long getTickCounter() {
        return tickCounter;
    }


    public void setTickCounter(long tickCounter) {
        this.tickCounter = tickCounter;
    }
}

