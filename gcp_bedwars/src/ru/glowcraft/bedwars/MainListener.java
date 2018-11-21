package ru.glowcraft.bedwars;


import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class MainListener implements Listener {
	Player p, killer;
	Entity en;
	World w;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		p = e.getPlayer();
		p.setOp(false);
		if(Plugin.process) {
			p.setGameMode(GameMode.SPECTATOR);
			p.setAllowFlight(true);
			p.setCanPickupItems(false);
			p.setFlying(true);
			p.setHealthScaled(false);
		} else if(Plugin.end) {
			p.kickPlayer("Игра уже завершена. Сервер будет перезагружен");
		} else {
			p.setGameMode(GameMode.SURVIVAL);
			p.setAllowFlight(false);
			p.sendTitle(Plugin.title, ChatColor.RED + "Вы вошли в игру.");
			p.teleport(Plugin.waitSpawn);
			p.setHealthScaled(false);
			p.setCanPickupItems(false);
			p.getInventory().clear();
			p.getInventory().addItem(Plugin.teamChoose);
			Plugin.sendGlobalChatMsg(ChatColor.RED + p.getDisplayName() + ChatColor.YELLOW 
					+ " присоединился к игре ("+Plugin.getPlayersAmount()+"/"+(Plugin.TEAMS*Plugin.INTEAM)+")");
			
		}
	}
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) { e.setCancelled(true); }
	@EventHandler
	public void onArmorStandManipulate(PlayerArmorStandManipulateEvent e) { e.setCancelled(true); }
	@EventHandler
	public void onQuit(PlayerQuitEvent e) { 
		p = e.getPlayer();
		Plugin.sendGlobalChatMsg(ChatColor.RED + p.getDisplayName() + ChatColor.YELLOW 
				+ " покинул игру ("+(Plugin.getPlayersAmount()-1)+"/"+(Plugin.TEAMS*Plugin.INTEAM)+")");
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		p = e.getPlayer();
		w = p.getWorld();
		if(w.getName().equals("world")) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		p = e.getPlayer();
		w = p.getWorld();
		if(w.getName().equals("world")) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if(y < 5) p.teleport(Plugin.waitSpawn);
	}
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		en = e.getEntity();
		if(en instanceof Player) {
			p = (Player) en;
			if(p.getWorld().getName().equals("world")) e.setCancelled(true);
		}
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		en = e.getEntity();
		if(en instanceof Player) {
			p = (Player) en;
			if(p.getWorld().getName().equals("world")) e.setCancelled(true);
		}
		
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		p = e.getPlayer();
		if(p.getWorld().getName().equals("world")) e.setCancelled(true);
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Action a = e.getAction();
		ItemStack is = e.getItem();
		
		if(a == Action.PHYSICAL || is == null || is.getType() == Material.AIR) return;
		
		if(is.getType() == Material.BED) {
			Plugin.openTeamChooser(e.getPlayer());
			e.getPlayer().sendMessage("Мамка");
		}
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!e.getInventory().getName().equals("Выбор команды")) return;
		p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()) {
			p.closeInventory();
			return;
		}
		switch(e.getCurrentItem().getType()) {
			case BLUE_SHULKER_BOX:
				if(Plugin.blueTeam.getAmount() < Plugin.INTEAM) {
					Plugin.blueTeam.getPlayers().add(p);
					p.sendMessage(Plugin.prefix + ChatColor.GREEN + "Вы вступили в команду синих");
				}
				p.closeInventory();
			break;
			case GREEN_SHULKER_BOX:
				if(Plugin.greenTeam.getAmount() < Plugin.INTEAM) {
					Plugin.greenTeam.getPlayers().add(p);
					p.sendMessage(Plugin.prefix + ChatColor.GREEN + "Вы вступили в команду зеленых");
				}
				p.closeInventory();
			break;
			case RED_SHULKER_BOX:
				if(Plugin.redTeam.getAmount() < Plugin.INTEAM) {
					Plugin.redTeam.getPlayers().add(p);
					p.sendMessage(Plugin.prefix + ChatColor.GREEN + "Вы вступили в команду красных");
				}
				p.closeInventory();
			break;
			case YELLOW_SHULKER_BOX:
				if(Plugin.yellowTeam.getAmount() < Plugin.INTEAM) {
					Plugin.yellowTeam.getPlayers().add(p);
					p.sendMessage(Plugin.prefix + ChatColor.GREEN + "Вы вступили в команду желтых");
				}
				p.closeInventory();
			break;
		}
	}
}
