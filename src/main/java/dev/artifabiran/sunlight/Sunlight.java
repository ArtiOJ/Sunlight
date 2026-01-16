package dev.artifabiran.sunlight;

import dev.artifabiran.sunlight.commands.SetDaytimeCommand;
import dev.artifabiran.sunlight.commands.SetNighttimeCommand;
import dev.artifabiran.sunlight.util.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sunlight extends JavaPlugin {

    SunlightData sunlightData;
    TimeController timeController;
    YamlConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        sunlightData = new SunlightData(this);
        config = sunlightData.getConfig();
        if (Boolean.TRUE.equals(Bukkit.getWorld("world").getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE))) {
            Bukkit.getLogger().warning(Colorize.format("[Sunlight] &cGamerule doDaylightCycle has been automatically disabled to allow Sunlight plugin to handle day/night cycle."));
            Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        }

        timeController = new TimeController(this, config.getLong("tickCounter"), config.getInt("daytimeMultiplier"), config.getInt("nighttimeMultiplier"));

        new SetDaytimeCommand(this, timeController, sunlightData);
        new SetNighttimeCommand(this, timeController, sunlightData);
    }

    @Override
    public void onDisable() {
        sunlightData.set("tickCounter", timeController.getTickCounter());
    }
}
