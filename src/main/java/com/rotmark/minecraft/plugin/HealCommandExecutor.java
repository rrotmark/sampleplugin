package com.rotmark.minecraft.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Adds the /heal command. It takes one parameter:
 * 
 * <p>
 * /heal &lt;player&gt; <br>
 * Heals the target player to full health.
 * </p>
 * 
 * @author Ryan Rotmark <rrotmark@gmail.com>
 */
public class HealCommandExecutor implements CommandExecutor {

    public HealCommandExecutor() {
    }

    /**
     * Called when the /h command is used by a player or on the console.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /heal <player>");
            return false;
        }

        String targetName = args[0];
        Player target = sender.getServer().getPlayer(targetName);

        if (target == null) {
            sender.sendMessage("Player [" + targetName + "] was not found.");
            return false;
        }

        target.setHealth(target.getMaxHealth());
        return true;
    }
}
