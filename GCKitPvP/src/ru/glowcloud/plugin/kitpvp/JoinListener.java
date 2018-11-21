package ru.glowcloud.plugin.kitpvp;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {
	private Plugin plugin;

	public JoinListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setGameMode(GameMode.SURVIVAL);
		p.sendTitle(ChatColor.RED+"KitPvP", ChatColor.YELLOW + "Вы вошли в игру");
		p.teleport(plugin.getSpawnLocation());
		e.setJoinMessage(ChatColor.GRAY+""+ChatColor.BOLD+"|"+ChatColor.YELLOW+" К нам присоединился "+ChatColor.RED+p.getDisplayName());
	}
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if(p.getLocation().getBlockY() < 31) e.setCancelled(true);
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		if(p.getLocation().getBlockY() < 31) e.setCancelled(true);
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player dead = e.getEntity();
		Player killer = e.getEntity().getKiller();
		String deadname = dead.getDisplayName();
		String killername = killer.getDisplayName();
		
		//Оповещаем в чате
		e.setDeathMessage(ChatColor.GRAY+""+ChatColor.BOLD+"|"+ChatColor.YELLOW+" Игрок "+ChatColor.RED+deadname+ChatColor.YELLOW+" убит игроком "+ChatColor.RED+killername);
		
		//Разбираемся с умершим игроком
		dead.teleport(plugin.getSpawnLocation());
		dead.sendTitle(ChatColor.RED+"Вы умерли", ChatColor.GOLD+"С вас снято 2 коина");
		int balance = dead.getMetadata("balance").get(0).asInt();
		int newbalance = balance-2;
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "updatebalance "+deadname+" "+newbalance);
		
		//Разбираемся с убийцей
		killer.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.YELLOW+"Вы убили игрока "+ChatColor.RED+deadname+ChatColor.YELLOW+" и получили 5 коинов");
		balance = killer.getMetadata("balance").get(0).asInt();
		int price = 5;
		if(killer.hasPermission("glowcloud.deluxe")) price = 10;
		if(killer.hasPermission("glowcloud.gold")) price = 15;
		if(killer.hasPermission("glowcloud.elite")) price = 20;
		if(killer.hasPermission("glowcloud.ultra")) price = 30;
		newbalance = balance+price;
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "updatebalance "+killername+" "+newbalance);
		
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!e.getPlayer().getDisplayName().equals("destator")) e.setCancelled(true);
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!e.getPlayer().getDisplayName().equals("destator")) e.setCancelled(true);
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(e.getPlayer().getLocation().getBlockY() < 19) 
			e.getPlayer().teleport(plugin.getSpawnLocation());
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if(!p.getDisplayName().equals("destator")) {
			if(e.getMessage().startsWith("/um") ||
					(e.getMessage().startsWith("/ultimatemenu") &&
					!e.getMessage().startsWith("/ultimatemenu open")) ||
					e.getMessage().startsWith("/gamemode") ||
					e.getMessage().startsWith("/op") ||
					e.getMessage().startsWith("/deop") ||
					e.getMessage().startsWith("/stop") ||
					e.getMessage().startsWith("/reload") ||
					e.getMessage().startsWith("/hd") ||
					e.getMessage().startsWith("/holographicdisplays") ||
					e.getMessage().startsWith("/npc") ||
					e.getMessage().startsWith("/citizens") ||
					e.getMessage().startsWith("/help") ||
					e.getMessage().startsWith("/pl") ||
					e.getMessage().startsWith("/pex") ||
					e.getMessage().contains(":")) {
				e.setCancelled(true);
			}
		}
	}
	

}
