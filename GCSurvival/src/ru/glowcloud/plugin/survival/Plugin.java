package ru.glowcloud.plugin.survival;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	private Location spawn;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new MainListener(this), this);
		getServer().getPluginManager().registerEvents(new ParkourListener(this), this);
		spawn = new Location(getServer().getWorld("world"), -66, 75, 275);
	}
	public Location getSpawnLocation() { return spawn; }
	public void onDisable() {
		
	}
}
