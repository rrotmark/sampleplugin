package com.rotmark.minecraft.plugin;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

/**
 * This listener fires when the player moves upwards suddenly, which is normally a jump. A diamond
 * block will appear under the player.
 * 
 * @author Ryan Rotmark <rrotmark@gmail.com>
 */
public class PlayerJumpListener implements Listener {

    /**
     * The jump state.
     */
    private static enum JumpState {
        ASCENDING, DESCENDING, NOT_JUMPING
    }

    /**
     * The name of the metadata item to store the jump state.
     */
    private static final String JUMP_STATE_METADATA = "jumpingState";

    private SamplePlugin plugin;

    public PlayerJumpListener(SamplePlugin plugin) {
        setPlugin(plugin);
    }

    /**
     * Gets the first element of the returned list as a {@link JumpState} if it exists. Otherwise,
     * it will return @{link {@link JumpState#NOT_JUMPING}.
     * 
     * @param metadatas
     *            The metadata list returned from {@link Player#getMetadata(String)}.
     * @return The current jump state.
     */
    protected JumpState getJumpState(List<MetadataValue> metadatas) {
        if (metadatas.size() == 0) {
            return JumpState.NOT_JUMPING;
        }
        else {
            return JumpState.valueOf(metadatas.get(0).asString());
        }
    }

    /**
     * Gets the parent plugin
     * 
     * @return the parent plugin
     */
    protected SamplePlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Event handler that fires when the player moves. Only jumping movements are significant.
     * 
     * @param event
     *            The PlayerMoveEvent.
     */
    @EventHandler
    public void onPlayerJump(PlayerMoveEvent event) {
        Logger logger = event.getPlayer().getServer().getLogger();
        logger.entering(PlayerJumpListener.class.getName(), "onPlayerJump");

        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        JumpState jumpState = getJumpState(player.getMetadata(JUMP_STATE_METADATA));

        // logger.info("jumpState = " + jumpState);

        double fromY = event.getFrom().getY();
        double toY = event.getTo().getY();

        double deltaY = toY - fromY;

        logger.info("deltaY = " + deltaY);

        if (deltaY > 0.4d && jumpState.equals(JumpState.NOT_JUMPING)) {
            // this is the start of the jump
            // assume moving upwards means it's a jump, though it could also be an explosion
            jumpState = JumpState.ASCENDING;
            player.setMetadata(JUMP_STATE_METADATA, new FixedMetadataValue(getPlugin(), jumpState));

        }
        else if (deltaY < 0.1d && deltaY > -0.1d && jumpState.equals(JumpState.ASCENDING)) {
            // this is when the jump has reached its peak
            jumpState = JumpState.DESCENDING;
            player.setMetadata(JUMP_STATE_METADATA, new FixedMetadataValue(getPlugin(), jumpState));

            int blockX = playerLocation.getBlockX();
            int blockY = playerLocation.getBlockY();
            int blockZ = playerLocation.getBlockZ();

            Block blockBelowPlayer = playerLocation.getWorld().getBlockAt(blockX, blockY - 1, blockZ);

            blockBelowPlayer.setTypeId(Material.DIAMOND_BLOCK.getId());
        }
        else if (jumpState.equals(JumpState.DESCENDING)) {
            // this is the end of the jump
            jumpState = JumpState.NOT_JUMPING;
            player.setMetadata(JUMP_STATE_METADATA, new FixedMetadataValue(getPlugin(), jumpState));
        }

        logger.exiting(PlayerJumpListener.class.getName(), "onPlayerJump");
    }

    /**
     * Sets the parent plugin
     * 
     * @param plugin
     *            the parent plugin
     */
    protected void setPlugin(SamplePlugin plugin) {
        this.plugin = plugin;
    }
}
