package com.ethreal.lavarises.commands;

import com.ethreal.lavarises.utilities.LavaUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LavaCommand implements CommandExecutor {
    LavaUtils utils = new LavaUtils();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) { // If the sender is a player
            Player senderAsPlayer = (Player) sender;
            if (args.length == 0) {
                senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize("<red>No args, this command requires args.</red>"));
            } else if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "start":
                        if (utils.startLava()) {
                            senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                    "<green>Game started</green>"
                            ));
                        } else {
                            senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                    "<red>Failed to start, already running.</red>"
                            ));
                        }
                        return true;
                    case "stop":
                        utils.stopLava();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "<red>Game stopped</red>"
                        ));
                        return true;
                    case "resume":
                        utils.resumeLava();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "<green>Game resumed</green>"
                        ));
                        return true;
                    case "restart":
                        utils.restartLava();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "<green>Game restarted</green>"
                        ));
                        return true;
                    case "clear":
                        utils.lavaClear();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "<green>Clearing lava</green>"
                        ));
                        return true;
                    case "up":
                        utils.lavaSpawn();
                        return true;
                    case "down":
                        utils.lavaDown();
                        return true;
                }
            } else {
                senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                        "<red>Too many args</red>"
                ));
            }
        }
        return false;
    }
}
