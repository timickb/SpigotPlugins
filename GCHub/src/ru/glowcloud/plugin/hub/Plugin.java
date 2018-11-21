package ru.glowcloud.plugin.hub;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	private Location spawn;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new MainListener(this), this);
		getServer().getPluginManager().registerEvents(new PakourListener(this), this);
		getServer().getPluginManager().registerEvents(new KingListener(this), this);
		getServer().getPluginManager().registerEvents(new LabirintListener(this), this);
		spawn = new Location(getServer().getWorld("world"), 187, 21, -158);
		getServer().getWorld("world").setKeepSpawnInMemory(true);
		getServer().getWorld("world").setSpawnLocation(187, 21, -158);
	}
	public Location getSpawnLocation() { return spawn; }
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.teleport(spawn);
			}
		}
		if(cmd.getName().equalsIgnoreCase("fly")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("glowcloud.gold")) {
					if(p.getAllowFlight()) {
						p.setFlying(false);
						p.setAllowFlight(false);
						p.sendMessage(ChatColor.YELLOW+"Режим полета отключен");
					} else {
						p.setAllowFlight(true);
						p.sendMessage(ChatColor.YELLOW+"Режим полета включен");
					}
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"Чтобы летать в лобби, необходимо приобрести статус "+ChatColor.GOLD+"[Gold]"
							+ChatColor.RED+". Сделать это можно в магазине: "+ChatColor.GOLD+"glowcloud.ru/store");
				}
			}
		}
		return false;
	}
	public void onDisable() {}
}
