package com.ethreal.lavarises.commands;

import com.ethreal.lavarises.LavaRises;
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
                sendVersion(senderAsPlayer);
            } else if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "start":
                        utils.startLava();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "Lava started"
                        ));
                    case "pause":
                        utils.pauseLava();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "Lava paused"
                        ));
                    case "unpause":
                        utils.unpauseLava();
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "Lava un-paused"
                        ));
                    case "up":
                        LavaRises.level += 1; // Increment level by 1
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "Lava level is now " + LavaRises.level
                        ));
                    case "down":
                        LavaRises.level -= 1; // Lower level by 1
                        senderAsPlayer.sendMessage(MiniMessage.miniMessage().deserialize(
                                "Lava level is now " + LavaRises.level
                        ));
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
