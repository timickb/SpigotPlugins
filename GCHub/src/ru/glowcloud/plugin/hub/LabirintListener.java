package ru.glowcloud.plugin.hub;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class LabirintListener implements Listener {
	private Plugin plugin;

	public LabirintListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if(x == 113 && y == 10 && z == -222) {
			int balance = p.getMetadata("balance").get(0).asInt();
			int newbalance = balance+100;
			p.setMetadata("balance", new FixedMetadataValue(plugin, newbalance));
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "updatebalance "+p.getDisplayName()+" "+newbalance);
			p.teleport(plugin.getSpawnLocation());
			p.sendTitle(ChatColor.GREEN+"Вы прошли лабиринт", ChatColor.YELLOW+"На ваш счет зачислено 100 коинов");
		}
	}

}
