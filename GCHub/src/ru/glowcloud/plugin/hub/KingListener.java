package ru.glowcloud.plugin.hub;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class KingListener implements Listener {
	Plugin plugin;

	public KingListener(Plugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if(x >= 165 && x <= 173 && z >= -228 && z <= -219) {
			p.setMetadata("koh", new FixedMetadataValue(plugin, "true"));
		} else {
			if(p.hasMetadata("koh")) p.removeMetadata("koh", plugin);
		}
	}

}
