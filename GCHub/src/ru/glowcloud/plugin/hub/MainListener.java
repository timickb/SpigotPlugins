package ru.glowcloud.plugin.hub;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class MainListener implements Listener {
	Plugin plugin;

	public MainListener(Plugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		if(p.getDisplayName().equals("destator")) {
			p.setGameMode(GameMode.CREATIVE);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 2, false, false));
		} else {
			p.setGameMode(GameMode.ADVENTURE);
			p.sendTitle(ChatColor.AQUA+"Glow"+ChatColor.GRAY+"Cloud"+ChatColor.WHITE+".ru", 
					ChatColor.YELLOW+"Добро пожаловать!");
			p.teleport(plugin.getSpawnLocation());
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		e.getPlayer().removeMetadata("koh", plugin);
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(!p.getDisplayName().equals("destator")) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(!p.getDisplayName().equals("destator")) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if(!p.hasMetadata("koh")) e.setCancelled(true);
	}
	public void onDamageBy(EntityDamageByEntityEvent e) {
		Player d = (Player) e.getDamager();
		if(!d.hasMetadata("koh")) e.setCancelled(true);
	}
	@EventHandler
	public void onFLChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
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
