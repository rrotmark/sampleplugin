package com.rotmark.minecraft.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Adds the /extinguish command. It takes one parameter:
 * 
 * <p>
 * /extinguish &lt;player&gt; <br>
 * Extinguishes the target player.
 * </p>
 * 
 * @author Ryan Rotmark <rrotmark@gmail.com>
 */
public class ExtinguishCommandExecutor implements CommandExecutor {

    public ExtinguishCommandExecutor() {
    }

    /**
     * Called when the /extinguish command is used by a player or on the console.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /extinguish <player>");
            return false;
        }

        String targetName = args[0];
        Player target = sender.getServer().getPlayer(targetName);

        if (target == null) {
            sender.sendMessage("Player [" + targetName + "] was not found.");
            return false;
        }

        target.setFireTicks(0);
        return true;
    }
}
