Sample CraftBukkit Plugin
============

This is a sample plugin that contains four mods:

 * Adds the /ignite command, which sets the target player on fire.
 * Adds the /extinguish command, which extingishes a burning player.
 * Adds the /heal command, which heals the target player to full heath.
 * An event listener that generates a diamond block below the feet of the player whenever he or
 * she jumps.

These mods are not intended to be particularly useful, but more of a "Hello, world" implementation of the Bukkit APIs.

To install this plugin, package it with Maven using the command:

mvn clean package

and copy the resulting .jar file to the plugins directory of your CraftBukkit install.