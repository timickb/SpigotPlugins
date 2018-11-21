package ru.glowcraft.bedwars;

import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Plugin extends JavaPlugin implements Listener {
	private static Player[] players;
	public static Spawner[] spawner;
	public static Team blueTeam,greenTeam,redTeam,yellowTeam;
	public static Logger logger;
	public static World wait, game;
	public static boolean process = false;
	public static boolean end = false;
	public static final int TEAMS = 4;
	public static final int INTEAM = 2;
	public static Location waitSpawn, gameSpawn;
	public static Server server;
	public static FileConfiguration config;
	public static final String prefix=ChatColor.DARK_AQUA + "Bed" + ChatColor.AQUA + "Wars" + ChatColor.GRAY + " | ";
	public static final String title=ChatColor.DARK_AQUA + "Bed" + ChatColor.AQUA + "Wars";
	public static ItemStack teamChoose = new ItemStack(Material.BED, 1);
	public static ItemMeta teamChooseMeta;

	public void onEnable() {
		server = this.getServer();
		config = this.getConfig();
		logger = server.getLogger();
		logger.info("BedWars plugin was enabled");
		getServer().getPluginManager().registerEvents(new MainListener(), this);
		server.createWorld(WorldCreator.name("game"));
		wait = server.getWorld("world");
		game = server.getWorld("game");
		
		//Configure wait world
		wait.setAnimalSpawnLimit(0);
		wait.setAmbientSpawnLimit(0);
		wait.setKeepSpawnInMemory(true);
		wait.setPVP(false);
		wait.setSpawnLocation(0, 0, 0);
		wait.setMonsterSpawnLimit(0);
		wait.setGameRuleValue("doDaylightCycle", "false");
		wait.setGameRuleValue("doTileDrops", "false");
		wait.setGameRuleValue("naturalRegeneration", "false");
		wait.setGameRuleValue("doWeatherCycle", "false");
		
		//Configure game world
		game.setAmbientSpawnLimit(0);
		game.setAnimalSpawnLimit(0);
		game.setMonsterSpawnLimit(0);
		game.setPVP(true);
		
		//Set spawn points
		waitSpawn = new Location(wait, -9, 38, 16);
		
		//Configure items
		teamChooseMeta = teamChoose.getItemMeta();
		teamChooseMeta.setDisplayName("Выбор команды");
		teamChoose.setItemMeta(teamChooseMeta);
		
		//Configure teams
		blueTeam = new Team(0,new Bed(0,0,0));
		greenTeam = new Team(1,new Bed(0,0,0));
		redTeam = new Team(2,new Bed(0,0,0));
		yellowTeam = new Team(3,new Bed(0,0,0));
		
	}
	
	
	public void onDisable() {
		getServer().getLogger().info("disabled");
	}
	
	public static void sendGlobalChatMsg(String msg) {
		players = server.getOnlinePlayers().toArray(new Player[server.getOnlinePlayers().size()]);
		for(int i = 0; i < players.length; i++) {
			players[i].sendMessage(prefix + msg);
		}
	}
	
	public static void openTeamChooser(Player player) {
		Inventory teamChooser = Bukkit.createInventory(null, 9, "Выбор команды");
		
		ItemStack blue = new ItemStack(Material.BLUE_SHULKER_BOX);
		ItemMeta blueMeta = blue.getItemMeta();
		blueMeta.setDisplayName(ChatColor.BLUE + "Синие" + " ("+blueTeam.getAmount()+"/"+INTEAM+")");
		blue.setItemMeta(blueMeta);
		
		ItemStack green = new ItemStack(Material.GREEN_SHULKER_BOX);
		ItemMeta greenMeta = green.getItemMeta();
		greenMeta.setDisplayName(ChatColor.GREEN + "Зеленые" + " ("+greenTeam.getAmount()+"/"+INTEAM+")");
		green.setItemMeta(greenMeta);
		
		ItemStack red = new ItemStack(Material.RED_SHULKER_BOX);
		ItemMeta redMeta = red.getItemMeta();
		redMeta.setDisplayName(ChatColor.RED + "Красные" + " ("+redTeam.getAmount()+"/"+INTEAM+")");
		red.setItemMeta(redMeta);
		
		ItemStack yellow = new ItemStack(Material.YELLOW_SHULKER_BOX);
		ItemMeta yellowMeta = yellow.getItemMeta();
		yellowMeta.setDisplayName(ChatColor.YELLOW + "Желтые" + " ("+yellowTeam.getAmount()+"/"+INTEAM+")");
		yellow.setItemMeta(yellowMeta);
		
		teamChooser.setItem(1, blue);
		teamChooser.setItem(3, green);
		teamChooser.setItem(5, red);
		teamChooser.setItem(7, yellow);
		
		player.openInventory(teamChooser);
		
		
	}
	
	public static int getPlayersAmount() { return server.getOnlinePlayers().size(); }
}
