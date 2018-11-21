package ru.glowcloud.plugin.hub;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

import net.md_5.bungee.api.ChatColor;

public class PakourListener implements Listener {
	Plugin plugin;
	Location p1,p2,p3,p4,p5,p6,p7,p8,p9;

	public PakourListener(Plugin plugin) {
		this.plugin = plugin;
		p1 = new Location(plugin.getServer().getWorld("world"), 182, 11, -211);
		p2 = new Location(plugin.getServer().getWorld("world"), 234, 24, -149);
		p3 = new Location(plugin.getServer().getWorld("world"), 229, 34, -110);
		p4 = new Location(plugin.getServer().getWorld("world"), 204, 43, -74);
		p5 = new Location(plugin.getServer().getWorld("world"), 149, 37, -74);
		p6 = new Location(plugin.getServer().getWorld("world"), 108, 39, -89);
		p7 = new Location(plugin.getServer().getWorld("world"), 94, 62, -135);
		p8 = new Location(plugin.getServer().getWorld("world"), 110, 84, -176);
		p9 = new Location(plugin.getServer().getWorld("world"), 151, 100, -180);
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		//Начало паркура
		if(x == 182 && y == 11 && z == -211) {
			p.sendTitle(ChatColor.YELLOW+"Огромный паркур", ChatColor.GREEN+"Вы начали прохождение");
			p.setMetadata("parkour", new FixedMetadataValue(plugin, "true"));
			p.setMetadata("p1", new FixedMetadataValue(plugin, "true"));
		}
		//1-й чекпоинт
		if(x == 234 && y == 24 && z == -149) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p1", plugin);
			p.setMetadata("p2", new FixedMetadataValue(plugin, "true"));
		}
		//2-й чекпоинт
		if(x == 229 && y == 34 && z == -110) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p2", plugin);
			p.setMetadata("p3", new FixedMetadataValue(plugin, "true"));
		}
		//3-й чекпоинт
		if(x == 204 && y == 43 && z == -74) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p3", plugin);
			p.setMetadata("p4", new FixedMetadataValue(plugin, "true"));
		}
		//4-й чекпоинт
		if(x == 149 && y == 37 && z == -74) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p4", plugin);
			p.setMetadata("p5", new FixedMetadataValue(plugin, "true"));
		}
		//5-й чекпоинт
		if(x == 108 && y == 39 && z == -89) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p5", plugin);
			p.setMetadata("p6", new FixedMetadataValue(plugin, "true"));
		}
		//6-й чекпоинт
		if(x == 94 && y == 62 && z == -135) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p6", plugin);
			p.setMetadata("p7", new FixedMetadataValue(plugin, "true"));
		}	
		//7-й чекпоинт
		if(x == 110 && y == 84 && z == -176) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p7", plugin);
			p.setMetadata("p8", new FixedMetadataValue(plugin, "true"));
		}	
		//8-й чекпоинт
		if(x == 151 && y == 100 && z == -180) {
			p.sendTitle(ChatColor.GREEN+"Прогресс сохранен", ChatColor.YELLOW+"При падении в воду вы вернетесь сюда");
			p.removeMetadata("p8", plugin);
			p.setMetadata("p9", new FixedMetadataValue(plugin, "true"));
		}	
		//Окончательный приз
		if(x == 165 && y == 115 && z == -131) {
			if(p.hasMetadata("p9")) {
				p.sendTitle(ChatColor.GREEN+"Вы получили приз", ChatColor.YELLOW+"На ваш счет зачислено 2000 коинов");
				p.removeMetadata("p9", plugin);
				p.removeMetadata("parkour", plugin);
				p.teleport(plugin.getSpawnLocation());
				int balance = p.getMetadata("balance").get(0).asInt();
				int newbalance = balance+2000;
				p.setMetadata("balance", new FixedMetadataValue(plugin, newbalance));
				plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "updatebalance "+p.getDisplayName()+" "+newbalance);
			}
		}
		//Промежуточный приз
		if(x == 183 && y == 36 && z == -58) {
			if(p.hasMetadata("p4")) {
				p.sendTitle(ChatColor.GREEN+"Вы получили приз", ChatColor.YELLOW+"На ваш счет зачислено 800 коинов");
				p.removeMetadata("parkour", plugin);
				p.removeMetadata("p4", plugin);
				p.teleport(plugin.getSpawnLocation());
				int balance = p.getMetadata("balance").get(0).asInt();
				int newbalance = balance+800;
				p.setMetadata("balance", new FixedMetadataValue(plugin, newbalance));
				plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "updatebalance "+p.getDisplayName()+" "+newbalance);
			}	
		}
		//Если упал в воду
		if(p.getLocation().getBlock().getTypeId() == 9) {
				if(p.hasMetadata("p1")) p.teleport(p1);
				else if(p.hasMetadata("p2")) p.teleport(p2);
				else if(p.hasMetadata("p3")) p.teleport(p3);
				else if(p.hasMetadata("p4")) p.teleport(p4);
				else if(p.hasMetadata("p5")) p.teleport(p5);
				else if(p.hasMetadata("p6")) p.teleport(p6);
				else if(p.hasMetadata("p7")) p.teleport(p7);
				else if(p.hasMetadata("p8")) p.teleport(p8);
				else if(p.hasMetadata("p9")) p.teleport(p9);
		}
	}
}