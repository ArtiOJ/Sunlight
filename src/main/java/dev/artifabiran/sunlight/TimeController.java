package dev.artifabiran.sunlight;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

public class TimeController {
    private long tickCounter;
    private float daytimeMultiplier;
    private float nighttimeMultiplier;
    BukkitScheduler scheduler;
    private float acc;

    World world;

    public TimeController(Sunlight sunlight, long tickCounter, int daytimeMultiplier, int nighttimeMultiplier) {
        this.daytimeMultiplier = daytimeMultiplier;
        this.nighttimeMultiplier = nighttimeMultiplier;
        this.tickCounter = tickCounter;
        acc = 0.0F;

        this.scheduler = Bukkit.getScheduler();
        world = Bukkit.getWorld("world");
        scheduler.runTaskTimer(sunlight, this::updateTime, 0L, 1L);
    }

    private void updateTime() {
        long time = world.getTime();
        if (time <= 12000) { // Day
            world.setTime(time + tick(daytimeMultiplier));
        } else { // night
            world.setTime(time + tick(nighttimeMultiplier));
        }

    }

    private int tick(float mult) {
        acc += mult;
        int out = (int) acc;
        acc -= out;
        return out;
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

