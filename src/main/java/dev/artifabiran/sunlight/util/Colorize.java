package dev.artifabiran.sunlight.util;

import org.bukkit.ChatColor;

public class Colorize {
        public static String format(String str) {
            return ChatColor.translateAlternateColorCodes('&', str);
    }
}
