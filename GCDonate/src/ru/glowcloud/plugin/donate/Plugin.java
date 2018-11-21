package ru.glowcloud.plugin.donate;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {
	private Database db;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		db = new Database(this);
	}
	
	public void onDisable() {}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String login = p.getDisplayName();
		String uuid = p.getUniqueId().toString();
		String status = db.getStatus(login);
	}
	
	
}
