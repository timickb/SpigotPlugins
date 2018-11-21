package ru.glowcloud.plugin.spy;

import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {
	Database db;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		db = new Database(this);
		db.start();
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		long startTime = new Date().getTime();
		p.setMetadata("spy-start-time", new FixedMetadataValue(this, startTime));
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		long endTime = new Date().getTime();
		long startTime = p.getMetadata("spy-start-time").get(0).asLong();
		long played = (endTime - startTime)/1000;
		getServer().getLogger().info("Player "+p.getDisplayName()+" played " + played + " seconds.");
		db.addTime(p.getDisplayName(), played);
		
	}
}
