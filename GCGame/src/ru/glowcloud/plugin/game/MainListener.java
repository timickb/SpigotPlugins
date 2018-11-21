package ru.glowcloud.plugin.game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginEvent;

import net.md_5.bungee.api.ChatColor;

public class MainListener implements Listener {
	private Game plugin;
	private Player p;
	
	public MainListener(Game p) { this.plugin = p; }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		p = e.getPlayer();
		p.setOp(false);
		switch(plugin.getStadium()) {
		//������ �������� ����
		case 0:
			p.sendTitle(plugin.getTitle(), ChatColor.YELLOW + "�� ����� � ����");
			plugin.connectPlayer(p);
		break;
		//������ ������� �� ������
		case 1:
			
		break;
		//������ ����
		case 2:
			p.sendTitle(plugin.getTitle(), ChatColor.YELLOW+"�� ����� ��� �����������");
			plugin.makePlayerSpectator(p);
		break;
		//���� ��������
		case 3:
			p.kickPlayer(ChatColor.RED + "���� �� ���� ������� ��� �����������. ������ �� ����� ������������.");
		break;
		}
		
	}
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) { e.setCancelled(true); }
	
	@EventHandler
	public void onPortal(PlayerPortalEvent e) { e.setCancelled(true); }
	
	@EventHandler
	public void onCountdownComplete(PluginEvent e) {
		if(Values.started) {
			plugin.setStadium(2);
			
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		p = e.getPlayer();
		switch(plugin.getStadium()) {
		case 0:
			plugin.disconnectPlayerFromWait(p);
		break;
		case 1:
			plugin.disconnectPlayerFromWait(p);
			plugin.disconnectPlayerFromCountdown(p);
		case 2:
			plugin.disconnectPlayer(p);
		break;
		}
	}
}
