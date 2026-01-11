package dev.artifabiran.sunlight.commands;

import dev.artifabiran.sunlight.Sunlight;
import dev.artifabiran.sunlight.SunlightData;
import dev.artifabiran.sunlight.TimeController;
import dev.artifabiran.sunlight.util.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Time;
import java.util.List;

public class SetNighttimeCommand implements CommandExecutor, TabCompleter {

    private static final int DAY_NIGHT_DURATION = 12000;
    private SunlightData config;
    private TimeController controller;

    public SetNighttimeCommand(Sunlight plugin, TimeController controller, SunlightData config) {
        this.config = config;
        this.controller = controller;
        plugin.getCommand("setnighttime").setExecutor(this);
        plugin.getCommand("setnighttime").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        int seconds;

        if (args.length != 1) {
            sender.sendMessage(Colorize.format("&cUsage: /setnighttime <durationSeconds>"));
            return false;
        }

        try {
            seconds = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(Colorize.format("&cArgument must be an integer"));
            return false;
        }

        if (seconds <= 0) {
            sender.sendMessage(Colorize.format("&cArgument can not be less than or equal to 0"));
            return false;
        }
        float multiplier = calculateMultiplier(seconds);
        config.set("nighttimeMultiplier", multiplier);
        controller.setNighttimeMultiplier(multiplier);

        return false;
    }

    private float calculateMultiplier(int timeSeconds) {
        return DAY_NIGHT_DURATION / (timeSeconds * 20F);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of("<durationSeconds>");
    }
}
