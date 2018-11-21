package ru.glowcloud.plugin.game;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Countdown extends Thread {
	private Plugin p;
	private Player[] pl;
	
	public Countdown(Plugin p) { this.p = p; }
	
	@Override
	public void run() {
		pl = (Player[]) p.getServer().getOnlinePlayers().toArray();
		for(int c = 10; c > 0; c--) {
			for(int i = 0; i < pl.length; i++) {
				pl[i].sendTitle(ChatColor.GOLD+""+ChatColor.BOLD+(Integer.toString(c)), "");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Values.started=true;
		this.interrupt();
		return;
	}
	public void broke() {
		pl = (Player[]) p.getServer().getOnlinePlayers().toArray();
		for(int i = 0; i < pl.length; i++) {
			pl[i].sendTitle(ChatColor.RED+"Отсчет отменен","");
		}
	}
	
}
