package ru.glowcloud.plugin.survival;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class MainListener implements Listener {
	private Plugin pl;
	
	public MainListener(Plugin pl) { this.pl = pl; }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.sendTitle(ChatColor.AQUA +"⚔"+ChatColor.YELLOW+" Survival "+ChatColor.AQUA + "⚔", 
				ChatColor.DARK_AQUA+"▶"+ChatColor.RED+" Добро пожаловать "+ChatColor.DARK_AQUA+"◀");
		p.setGameMode(GameMode.SURVIVAL);
		if(!p.getDisplayName().equals("destator")) p.setOp(false);
		else {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendTitle(ChatColor.RED+"Введите ключ","");
		}
	}
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Entity en = e.getEntity();
		if(en instanceof Player) {
			Player p = (Player) en;
		}
	}
	@EventHandler
	public void onPotion(PotionSplashEvent e) {
		Entity en = e.getEntity();
		if(en instanceof Player) {
			Player p = (Player) en;
			if(p.getGameMode().equals(GameMode.CREATIVE)) {
				p.sendTitle(ChatColor.DARK_RED+"Запрещенное действие", ChatColor.RED+"Перейдите в режим выживания");
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e) {
		if((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player)) {
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			if(d.getGameMode().equals(GameMode.CREATIVE)) {
				d.sendMessage(ChatColor.RED + "Запрещено!! " + ChatColor.YELLOW + "Переключитесь в режим выживания, чтобы бить игроков");
				e.setCancelled(true);
			} else if(d.isFlying()) {
				d.sendMessage(ChatColor.RED + "Запрещено!! " + ChatColor.YELLOW + "Отключите полет, чтобы бить игроков");
				e.setCancelled(true);
			} else {
				p.sendMessage(ChatColor.RED + "|| " + ChatColor.YELLOW + "Вы вступили в PvP с игроком " + ChatColor.RED + d.getDisplayName());
				d.sendMessage(ChatColor.RED + "|| " + ChatColor.YELLOW + "Вы вступили в PvP с игроком " + ChatColor.RED + p.getDisplayName());
			}
			
		}
	}
	@EventHandler
	public void onFood(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(e.getItem().equals(new ItemStack(Material.GOLDEN_APPLE))) {
				e.setCancelled(true);
				e.getPlayer().sendTitle(ChatColor.RED+"Нельзя", ChatColor.YELLOW + "яблоки жрать");
			}
		}
	}
	@EventHandler
	public void onMainMoves(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if((x==-16 && y < 70 && z==279) || (x==-16 && y < 70 && z==271)) {
			p.sendTitle(ChatColor.RED+"Не вздумай!", ChatColor.YELLOW+"Не стоит себя выбрасывать. Жизнь еще наладится!");
			p.teleport(new Location(p.getWorld(), -16, 72, 275));
		}
		//Когда выходит со спавна
		if((x==-6 && y == 72) && (z > 214) && (z < 336)) {
			p.sendTitle(ChatColor.RED+"Внизу начинается PvP", "");
		}
		//Когда над спавном
		if(y >= 179) {
			p.teleport(pl.getSpawnLocation());
		}
		if(p.getGameMode().equals(GameMode.SPECTATOR)) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage();
		Player p = e.getPlayer();
		if(p.getGameMode().equals(GameMode.SPECTATOR)) e.setCancelled(true);
		if(cmd.equals("/warp shop")) p.sendTitle(ChatColor.GREEN+"Магазин предметов", ChatColor.YELLOW+"Здесь вы найдете все, что вам нужно");
		if(cmd.equals("/sethome")) p.sendTitle(ChatColor.GREEN+"Дом успешно установлен!", "");
		if(cmd.equals("/rtp")) p.sendTitle(ChatColor.GREEN+"Вы были телепортированы", "");
		if(cmd.equals("/warp mine")) p.sendTitle(ChatColor.AQUA+"Автошахта", "");
		//Запрещенные команды (в принципе)
		if(cmd.startsWith("/pex") ||
				cmd.startsWith("/op") ||
				cmd.startsWith("/help") ||
				cmd.startsWith("/stop") ||
				cmd.startsWith("/powertool") ||
				cmd.startsWith("/pt") ||
				cmd.startsWith("/automessage") ||
				cmd.startsWith("/am") ||
				cmd.startsWith("//limit") ||
				cmd.startsWith("//rotate") ||
				cmd.startsWith("/sudo") ||
				cmd.startsWith("/nuke") ||
				cmd.startsWith("/save-all") ||
				(cmd.startsWith("/me") && !cmd.equals("/menu")) ||
				cmd.startsWith("/reload") ||
				cmd.startsWith("/ban") ||
				cmd.startsWith("/rg remove spawn") ||
				cmd.startsWith("/rg remove spawn-area")||
				cmd.startsWith("/rg remove mine")||
				cmd.startsWith("/rg remove mob-arena")||
				cmd.startsWith("/rg remove shop")||
				cmd.startsWith("/rg addowner spawn-area")||
				cmd.startsWith("/rg addowner mine")||
				cmd.startsWith("/rg addowner mob-arena")||
				cmd.startsWith("/rg addowner shop")||
				cmd.startsWith("/rg addmember spawn-area")||
				cmd.startsWith("/rg addmember mine")||
				cmd.startsWith("/rg addmember mob-arena")||
				cmd.startsWith("/rg addmember shop")||
				cmd.startsWith("/rg removeowner spawn-area")||
				cmd.startsWith("/rg removeowner mine")||
				cmd.startsWith("/rg removeowner mob-arena")||
				cmd.startsWith("/rg removeowner shop")||
				cmd.startsWith("/rg flag spawn-area")||
				cmd.startsWith("/rg flag mine")||
				cmd.startsWith("/rg flag mob-arena")||
				cmd.startsWith("/rg flag shop")||
				cmd.contains(":")) {
			e.setCancelled(true);
		}
		//Запрещенные для всех, кроме админа
		if(!p.getDisplayName().equals("destator")) {
			if(cmd.startsWith("/hd") ||
					cmd.startsWith("/holographicdisplays") ||
					cmd.startsWith("//replacenear") ||
					cmd.startsWith("/chatmanager")) {
				e.setCancelled(true);
			}
		}
	}
	//Защита спавна
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if(!p.getDisplayName().equals("destator")) {
			if(x >= 134 && x <= 2 && z >= 208 && z <= 345) {
				e.setCancelled(true);
			}
		}
	}
	//Запрет на транспорт на спавне
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		e.get
	}
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		Player p =e.getPlayer();
		if(p.getGameMode().equals(GameMode.SPECTATOR) && p.getDisplayName().equals("destator")) {
			if(e.getMessage().equals("glow1337cloud322")) {
				p.setGameMode(GameMode.CREATIVE);
				p.sendTitle(ChatColor.YELLOW+"Вы ввели ключ","");
				e.setCancelled(true);
			} else {
				p.setGameMode(GameMode.SPECTATOR);
				p.kickPlayer("Неправильный ключ!");
			}
		}
		
	}
}
