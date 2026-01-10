package dev.artifabiran.sunlight;

import org.bukkit.plugin.java.JavaPlugin;

public final class Sunlight extends JavaPlugin {

    SunlightData sunlightData;
    @Override
    public void onEnable() {
        // Plugin startup logic
        sunlightData = new SunlightData(this);
//        sunlightData.getConfig().get("tickCounter");
//        sunlightData.getConfig().set("tickCounter", 0);
    }

    @Override
    public void onDisable() {

    }
}
