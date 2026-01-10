package dev.artifabiran.sunlight;

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
        timeController = new TimeController(config.getLong("tickCounter"));
    }

    @Override
    public void onDisable() {
        config.set("tickCounter", timeController.getTickCounter());
    }
}
