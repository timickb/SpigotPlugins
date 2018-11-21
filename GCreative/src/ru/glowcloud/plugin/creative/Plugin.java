package ru.glowcloud.plugin.creative;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {
	private Database db;
	public void onEnable() {
		/*db = new Database(this);
		db.start();*/
		getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		if(p.hasPermission("glowcloud.elite")) {
			p.sendTitle(ChatColor.GREEN+""+ChatColor.BOLD+"Creative",ChatColor.YELLOW+"Добро пожаловать");
		} else {
			p.kickPlayer("Доступ к свободному миру креатива есть только со статусом [Elite]. Приобретите его здесь: glowcloud.ru/store. Успей, пока скидка 90%!!");
		}
	}
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage();
		Player p = e.getPlayer();
		if(cmd.equals("/sethome")) p.sendTitle(ChatColor.GREEN+"Дом успешно установлен!", "");
		if(cmd.equals("/rtp")) p.sendTitle(ChatColor.GREEN+"Вы были телепортированы", "");
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

}
