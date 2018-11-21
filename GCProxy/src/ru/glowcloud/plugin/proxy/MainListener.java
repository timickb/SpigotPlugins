package ru.glowcloud.plugin.proxy;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MainListener implements Listener {
	private Main plugin;
	public MainListener(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onConnect(ServerConnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		
	}
	@EventHandler
	public void onServerSwitch(ServerSwitchEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if(p.getServer().equals(ProxyServer.getInstance().getServerInfo("hub"))) {
			int status = plugin.getDB().getOnJoin(p.getDisplayName());
			if(status == 1) {
				ServerInfo survival = ProxyServer.getInstance().getServerInfo("survival");
				p.connect(survival);
			}
		}
	}
	@EventHandler
	public void onQuit(net.md_5.bungee.api.event.ServerDisconnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
	}
}
