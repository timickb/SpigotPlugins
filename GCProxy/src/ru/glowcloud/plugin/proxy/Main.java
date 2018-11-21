package ru.glowcloud.plugin.proxy;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	private ProxiedPlayer p;
	private Database db;
	@Override
	public void onEnable() {
		getLogger().info("Enabled");
		getProxy().getPluginManager().registerCommand(this, new Commands());
		getProxy().getPluginManager().registerCommand(this, new EventCommand());
		getProxy().getPluginManager().registerListener(this, new MainListener(this));
		db = new Database(this);
		db.start();
	}
	@Override
	public void onDisable() {
		getLogger().info("Disabled");
		//getProxy().getPluginManager().registerListener(this, new Events());
	}
	public Database getDB() { return db; }
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		//Команда hub
		if(cmd.getName().equalsIgnoreCase("hub")) {
			return true;
		}
		return false;
		
	}

}
