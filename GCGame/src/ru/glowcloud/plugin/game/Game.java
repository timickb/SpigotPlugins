package ru.glowcloud.plugin.game;

import java.io.File;
import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.java.*;

import net.md_5.bungee.api.ChatColor;

public class Game extends JavaPlugin {
	private int stadium = 0;
	private int teams;
	private int inteam;
	private ItemStack leaveItem, teleportItem;
	private ItemMeta leaveItemMeta, teleportItemMeta;
	private String title;
	private Collection<? extends Player> players;
	private World wait, game;
	private Location waitSpawn, spectatorSpawn;
	private Countdown countdown;
	
	//General
	public void onEnable() {
		getServer().getLogger().info("Enabled");
		getServer().getPluginManager().registerEvents(new MainListener(this), this);
		createLeaveItem();
		createTeleportItem();
		configure();
		
	}
	

	public void configure() {}


	public void onDisable() {
		getServer().getLogger().info("Disabled");
	}
	public void addListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	//Getters
	public int getStadium() { return stadium; }
	public int getSlots() { return teams*inteam; }
	public int getTeamsAmount() { return teams; }
	public int getPlayersInTeamAmount() { return inteam; }
	public String getTitle() { return title; }
	public String getPrefix() { return title + ChatColor.GRAY+ChatColor.BOLD+" | " + ChatColor.RESET; }
	public ItemStack getLeaveItem() { return leaveItem; }
	public ItemStack getTeleportItem() { return teleportItem; }
	
	//Setters
	public void setTitle(String title) { this.title = title; }
	public void setStadium(int stadium) { this.stadium = stadium; }
	
	//Private
	private void createLeaveItem() {
		leaveItem = new ItemStack(Material.SLIME_BALL);
		leaveItemMeta = leaveItem.getItemMeta();
		leaveItemMeta.setDisplayName(ChatColor.RESET + "Выйти в лобби");
		leaveItem.setItemMeta(leaveItemMeta);
	}
	
	private void createTeleportItem() {
		teleportItem = new ItemStack(Material.COMPASS);
		teleportItemMeta = teleportItem.getItemMeta();
		teleportItemMeta.setDisplayName(ChatColor.RESET + "Телепортироваться к игроку");
		teleportItem.setItemMeta(teleportItemMeta);
		
	}
	
	//Procedures
	public void sendGlobalMessage(String msg) {
		players = getServer().getOnlinePlayers();
		Player[] tmparr = (Player[]) players.toArray();
		for(int i = 0; i < tmparr.length; i++) {
			tmparr[i].sendMessage(getPrefix() + msg);
		}
	}
	
	public void makePlayerSpectator(Player p) {
		p.setGameMode(GameMode.SPECTATOR);
		p.setAllowFlight(true);
		p.setFlying(true);
		p.getInventory().addItem(getTeleportItem());
		p.getInventory().addItem(getLeaveItem());
	}
	
	public void connectPlayer(Player p) {
		p.setAllowFlight(false);
		p.setCanPickupItems(false);
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().addItem(getLeaveItem());
		sendGlobalMessage(ChatColor.RED+p.getDisplayName()+ChatColor.YELLOW
					+" присоединился к игре "+ChatColor.RED+"("+getServer().getOnlinePlayers().size()
					+"/"+getPlayersInTeamAmount()+")");
		if(getServer().getOnlinePlayers().size() == teams*inteam) {
			 //Меняем стадию игры
			 this.setStadium(1);
			 //Подготавливаем всех игроков
			 Player[] parr = (Player[]) getServer().getOnlinePlayers().toArray();
			 for(int i = 0; i < parr.length; i++) {
				 preparePlayer(parr[i]);
			 }
			 //Начинаем отсчет до начала
			 countdown = new Countdown(this);
			 countdown.start();
		}
		
	}
	public void disconnectPlayerFromWait(Player p) {
		sendGlobalMessage(ChatColor.RED+p.getDisplayName()+ChatColor.YELLOW
				+" покинул игру "+ChatColor.RED+"("+(getServer().getOnlinePlayers().size()-1)
				+"/"+getPlayersInTeamAmount()+")");
	}
	public void disconnectPlayer(Player p) {
		sendGlobalMessage(ChatColor.RED+p.getDisplayName()+ChatColor.YELLOW+" покинул игру ");
	}
	public void disconnectPlayerFromCountdown(Player p) {
		countdown.broke();
		countdown.interrupt();
		sendGlobalMessage(ChatColor.RED+p.getDisplayName()+ChatColor.YELLOW+" покинул игру ");
		
	}
	
	public void preparePlayer(Player p) {}



	
	
	
}

