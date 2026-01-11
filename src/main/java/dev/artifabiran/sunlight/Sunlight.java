package dev.artifabiran.sunlight;

import dev.artifabiran.sunlight.commands.SetDaytimeCommand;
import dev.artifabiran.sunlight.commands.SetNighttimeCommand;
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
        timeController = new TimeController(this, config.getLong("tickCounter"), config.getInt("daytimeMultiplier"), config.getInt("nighttimeMultiplier"));

        new SetDaytimeCommand(this, timeController, sunlightData);
        new SetNighttimeCommand(this, timeController, sunlightData);
    }

    @Override
    public void onDisable() {
        sunlightData.set("tickCounter", timeController.getTickCounter());
    }
}
