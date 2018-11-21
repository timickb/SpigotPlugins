package ru.glowcloud.plugin.proxy;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Commands extends Command {
	private ProxiedPlayer p;
	private Title title;
	private BaseComponent bc;

	public Commands() {
		super("hub","glowcloud.hub",new String[0]);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			p = (ProxiedPlayer) sender;
			if(p.getServer().getInfo().getName().equalsIgnoreCase("auth") || p.getServer().getInfo().getName().equalsIgnoreCase("hub")) {
				p.sendMessage("Вы не можете перейти в лобби из лобби или авторизации");
			} else {
				ServerInfo hub = ProxyServer.getInstance().getServerInfo("hub");
				p.connect(hub);
			}
		} else {
			sender.sendMessage("You are not a player!");
		}
		
	}

}
