package dev.artifabiran.sunlight.commands;

import dev.artifabiran.sunlight.Sunlight;
import dev.artifabiran.sunlight.util.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SetDaytimeCommand implements CommandExecutor, TabCompleter {

    private static final int DAY_NIGHT_DURATION = 12000;
    private YamlConfiguration config;

    public SetDaytimeCommand(Sunlight plugin, YamlConfiguration config) {
        this.config = config;
        plugin.getCommand("setdaytime").setExecutor(this);
        plugin.getCommand("setdaytime").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length < 0) {
            sender.sendMessage(Colorize.format("&cUsage: /setdaytime <durationSeconds"));
            return false;
        }

        return false;
    }

    private double calculateMultiplier(int timeSeconds) {
        return DAY_NIGHT_DURATION / (timeSeconds * 20);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of("<durationSeconds");
    }
}
