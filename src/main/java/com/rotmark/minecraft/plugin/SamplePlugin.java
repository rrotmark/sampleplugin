package com.rotmark.minecraft.plugin;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is a sample plugin that that contains four mods:
 * 
 * <ul>
 * <li>Adds the /ignite command, which sets the target player on fire.</li>
 * <li>Adds the /extinguish command, which extinguishes a burning player.</li>
 * <li>Adds the /heal command, which heals the target player to full heath.</li>
 * <li>An event listener that generates a diamond block below the feet of the player whenever he or
 * she jumps.</li>
 * 
 * These mods are not intended to be particularly useful, but more of a "Hello, world"
 * implementation of the Bukkit APIs.
 * </ul>
 * 
 * @author Ryan Rotmark <rrotmark@gmail.com>
 */
public class SamplePlugin extends JavaPlugin {

    public SamplePlugin() {
    }

    /**
     * Called when this plugin is disabled. Logs a message to the server console.
     */
    @Override
    public void onDisable() {
        getLogger().info("Called onDisable.");
    }

    /**
     * Called when this plugin is enabled. Logs a message to the server console and initializes the
     * mods.
     */
    @Override
    public void onEnable() {
        getLogger().info("Enter onEnable.");

        getCommand("ignite").setExecutor(new IgniteCommandExecutor());
        getCommand("extinguish").setExecutor(new ExtinguishCommandExecutor());
        getCommand("heal").setExecutor(new HealCommandExecutor());
        getServer().getPluginManager().registerEvents(new PlayerJumpListener(this), this);

        getLogger().info("Exit onEnable.");
    }
}
