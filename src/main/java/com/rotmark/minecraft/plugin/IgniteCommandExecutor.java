package com.rotmark.minecraft.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Adds the /ignite command. It can take one or two parameters:
 * 
 * <p>
 * /ignite &lt;player&gt; <br>
 * Sets the target player on fire for a default number of ticks (currently 100).
 * </p>
 * 
 * <p>
 * /ignite &lt;player&gt; &lt;ticks&gt;<br>
 * Sets the target player on fire for a specified number of ticks.
 * </p>
 * 
 * @author Ryan Rotmark <rrotmark@gmail.com>
 */
public class IgniteCommandExecutor implements CommandExecutor {

    public IgniteCommandExecutor() {
    }

    /**
     * Called when the /ignite command is used by a player or on the console.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1 && args.length != 2) {
            sender.sendMessage("Usage: /ignite <player> [ticks]");
            return false;
        }

        String targetName = args[0];
        Player target = sender.getServer().getPlayer(targetName);

        if (target == null) {
            sender.sendMessage("Player [" + targetName + "] was not found.");
            return false;
        }

        int ticks = 100;
        if (args.length == 2) {
            try {
                ticks = Integer.parseInt(args[1]);
            }
            catch (Exception e) {
                sender.sendMessage("[" + args[1] + "] is not a valid number.");
            }
        }

        if (ticks <= 0) {
            sender.sendMessage("[" + ticks + "] is not a valid number of ticks.");
        }

        target.setFireTicks(ticks);
        return true;
    }
}
