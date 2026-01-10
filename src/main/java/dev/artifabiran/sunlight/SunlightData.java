package dev.artifabiran.sunlight;

import dev.artifabiran.sunlight.Sunlight;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SunlightData {
    private Sunlight plugin;
    private File file;
    private YamlConfiguration config;

    public SunlightData(Sunlight plugin){
        this.plugin = plugin;
        load();
    }

    private void load(){
        file = new File(plugin.getDataFolder(), "config.yml");

        plugin.saveResource("config.yml", false);

        config = new YamlConfiguration();
        config.options().parseComments(false);

        try {
            config.load(file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public YamlConfiguration getConfig(){
        return config;
    }
}