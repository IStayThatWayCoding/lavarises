package com.ethreal.lavarises.commands;

import com.ethreal.lavarises.LavaRises;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LavaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) { // If the sender is a player
            Player senderAsPlayer = (Player) sender;
            if (args.length == 0) {
                sendVersion(senderAsPlayer);
            } else if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "up":
                        LavaRises.level += 1; // Increment level by 1
                    case "down":
                        LavaRises.level -= 1; // Lower level by 1
                    case "version":
                        sendVersion(senderAsPlayer);
                }
            } else {

            }
        }
        return false;
    }

    private void sendVersion(Player player) {
        player.sendMessage(MiniMessage.miniMessage().deserialize(
                ""
        ));
    }
}
