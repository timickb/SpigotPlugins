package ru.glowcloud.plugin.survival;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.MetadataValue;

import net.md_5.bungee.api.ChatColor;

public class ParkourListener implements Listener {
	Plugin pl;
	MetadataValue v;

	public ParkourListener(Plugin plugin) {
		this.pl = plugin;
	}
	
	@EventHandler
	public void onStart(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if(x == -78 && y == 73 && z == 245) {
				p.sendTitle(ChatColor.GOLD + "Паркур", ChatColor.RED + "Вы начали прохождение");
				p.setGameMode(GameMode.SURVIVAL);
				p.setAllowFlight(false);
				p.setFlying(false);
		}
		
	}
	
}
