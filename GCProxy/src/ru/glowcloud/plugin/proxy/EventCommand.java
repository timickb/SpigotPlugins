package ru.glowcloud.plugin.proxy;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class EventCommand extends Command {
	private ProxiedPlayer p;
	private Title title;
	private BaseComponent bc;

	public EventCommand() {
		super("event","glowcloud.event",new String[0]);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			p = (ProxiedPlayer) sender;
			if(p.getServer().getInfo().getName().equalsIgnoreCase("auth")) {
				p.sendMessage("Нельзя");
			} else {
				ServerInfo event = ProxyServer.getInstance().getServerInfo("event");
				p.connect(event);
			}
		} else {
			sender.sendMessage("You are not a player");
		}
		
	}

}
