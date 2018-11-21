package ru.glowcloud.plugin.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	private Location spawn, warzone;
	private ItemStack[] swordkit, bowkit, potionkit, deluxekit, goldkit, elitekit, ultrakit;
	private ItemMeta[] swordkitMeta, bowkitMeta, potionkitMeta, deluxekitMeta, goldkitMeta, elitekitMeta, ultrakitMeta;
	private final int SWORDKIT_COST = 50;
	private final int BOWKIT_COST = 50;
	private final int POTIONKIT_COST = 60;
	private final int DELUXEKIT_COST = 100;
	private final int GOLDKIT_COST = 120;
	private final int ELITEKIT_COST = 140;
	private final int ULTRAKIT_COST = 160;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		
		spawn = new Location(getServer().getWorld("world"), 17, 21, 15);
		warzone = new Location(getServer().getWorld("world"), 17, 32, 15);
		
		//Мечник
		swordkit = new ItemStack[6];
		swordkit[0] = new ItemStack(Material.IRON_HELMET);
		swordkit[1] = new ItemStack(Material.IRON_CHESTPLATE);
		swordkit[2] = new ItemStack(Material.IRON_LEGGINGS);
		swordkit[3] = new ItemStack(Material.IRON_BOOTS);
		swordkit[4] = new ItemStack(Material.STONE_SWORD);
		swordkit[5] = new ItemStack(Material.COOKED_BEEF);
		swordkit[5].setAmount(32);
		
		//Лучник
		bowkit = new ItemStack[7];
		bowkit[0] = new ItemStack(Material.LEATHER_HELMET);
		bowkit[1] = new ItemStack(Material.LEATHER_CHESTPLATE);
		bowkit[2] = new ItemStack(Material.LEATHER_LEGGINGS);
		bowkit[3] = new ItemStack(Material.LEATHER_BOOTS);
		bowkit[4] = new ItemStack(Material.BOW);
		bowkit[5] = new ItemStack(Material.ARROW);
		bowkit[6] = new ItemStack(Material.COOKED_BEEF);
		bowkit[6].setAmount(32);
		
		//Алхимик
		potionkit = new ItemStack[13];
		potionkit[0] = new ItemStack(Material.LEATHER_HELMET);
		potionkit[1] = new ItemStack(Material.LEATHER_CHESTPLATE);
		potionkit[2] = new ItemStack(Material.LEATHER_LEGGINGS);
		potionkit[3] = new ItemStack(Material.LEATHER_BOOTS);
		potionkit[4] = new ItemStack(Material.STONE_SWORD);
		potionkit[5] = new ItemStack(Material.COOKED_BEEF);
		potionkit[6] = new ItemStack(Material.SPLASH_POTION);
		potionkit[7] = new ItemStack(Material.SPLASH_POTION);
		potionkit[8] = new ItemStack(Material.SPLASH_POTION);
		potionkit[9] = new ItemStack(Material.SPLASH_POTION);
		potionkit[10] = new ItemStack(Material.SPLASH_POTION);
		potionkit[11] = new ItemStack(Material.SPLASH_POTION);
		potionkit[12] = new ItemStack(Material.SPLASH_POTION);
		potionkit[5].setAmount(16);
		
		//Deluxe
		deluxekit = new ItemStack[8];
		deluxekit[0] = new ItemStack(Material.DIAMOND_HELMET);
		deluxekit[1] = new ItemStack(Material.DIAMOND_CHESTPLATE);
		deluxekit[2] = new ItemStack(Material.DIAMOND_LEGGINGS);
		deluxekit[3] = new ItemStack(Material.DIAMOND_BOOTS);
		deluxekit[4] = new ItemStack(Material.DIAMOND_SWORD);
		deluxekit[5] = new ItemStack(Material.BOW);
		deluxekit[6] = new ItemStack(Material.ARROW);
		deluxekit[7] = new ItemStack(Material.COOKED_BEEF);
		deluxekit[6].setAmount(64);
		deluxekit[7].setAmount(32);
		
		//Gold
		goldkit = new ItemStack[8];
		goldkit[0] = new ItemStack(Material.DIAMOND_HELMET);
		goldkit[1] = new ItemStack(Material.DIAMOND_CHESTPLATE);
		goldkit[2] = new ItemStack(Material.DIAMOND_LEGGINGS);
		goldkit[3] = new ItemStack(Material.DIAMOND_BOOTS);
		goldkit[4] = new ItemStack(Material.DIAMOND_SWORD);
		goldkit[5] = new ItemStack(Material.BOW);
		goldkit[6] = new ItemStack(Material.ARROW);
		goldkit[7] = new ItemStack(Material.COOKED_BEEF);
		goldkit[7].setAmount(32);
		goldkitMeta = new ItemMeta[8];
		goldkitMeta[0] = goldkit[0].getItemMeta();
		goldkitMeta[1] = goldkit[1].getItemMeta();
		goldkitMeta[2] = goldkit[2].getItemMeta();
		goldkitMeta[3] = goldkit[3].getItemMeta();
		goldkitMeta[4] = goldkit[4].getItemMeta();
		goldkitMeta[5] = goldkit[5].getItemMeta();
		goldkitMeta[0].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		goldkitMeta[1].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		goldkitMeta[2].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		goldkitMeta[3].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		goldkitMeta[4].addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		goldkitMeta[5].addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		goldkitMeta[5].addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
		//Elite
		elitekit = new ItemStack[8];
		elitekit[0] = new ItemStack(Material.DIAMOND_HELMET);
		elitekit[1] = new ItemStack(Material.DIAMOND_CHESTPLATE);
		elitekit[2] = new ItemStack(Material.DIAMOND_LEGGINGS);
		elitekit[3] = new ItemStack(Material.DIAMOND_BOOTS);
		elitekit[4] = new ItemStack(Material.DIAMOND_SWORD);
		elitekit[5] = new ItemStack(Material.BOW);
		elitekit[6] = new ItemStack(Material.ARROW);
		elitekit[7] = new ItemStack(Material.COOKED_BEEF);
		elitekit[7].setAmount(32);
		elitekitMeta = new ItemMeta[8];
		elitekitMeta[0] = elitekit[0].getItemMeta();
		elitekitMeta[1] = elitekit[1].getItemMeta();
		elitekitMeta[2] = elitekit[2].getItemMeta();
		elitekitMeta[3] = elitekit[3].getItemMeta();
		elitekitMeta[4] = elitekit[4].getItemMeta();
		elitekitMeta[5] = elitekit[5].getItemMeta();
		elitekitMeta[0].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		elitekitMeta[1].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		elitekitMeta[2].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		elitekitMeta[3].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		elitekitMeta[4].addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		elitekitMeta[5].addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
		elitekitMeta[5].addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
		//Ultra
		ultrakit = new ItemStack[8];
		ultrakit[0] = new ItemStack(Material.DIAMOND_HELMET);
		ultrakit[1] = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ultrakit[2] = new ItemStack(Material.DIAMOND_LEGGINGS);
		ultrakit[3] = new ItemStack(Material.DIAMOND_BOOTS);
		ultrakit[4] = new ItemStack(Material.DIAMOND_SWORD);
		ultrakit[5] = new ItemStack(Material.BOW);
		ultrakit[6] = new ItemStack(Material.ARROW);
		ultrakit[7] = new ItemStack(Material.COOKED_BEEF);
		ultrakit[7].setAmount(32);
		ultrakitMeta = new ItemMeta[8];
		ultrakitMeta[0] = ultrakit[0].getItemMeta();
		ultrakitMeta[1] = ultrakit[1].getItemMeta();
		ultrakitMeta[2] = ultrakit[2].getItemMeta();
		ultrakitMeta[3] = ultrakit[3].getItemMeta();
		ultrakitMeta[4] = ultrakit[4].getItemMeta();
		ultrakitMeta[5] = ultrakit[5].getItemMeta();
		ultrakitMeta[0].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		ultrakitMeta[1].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		ultrakitMeta[2].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		ultrakitMeta[3].addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		ultrakitMeta[4].addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		ultrakitMeta[5].addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
		elitekitMeta[5].addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
		
		
	}
	
	public Location getSpawnLocation() { return spawn; }
	public Location getWarzoneLocation() { return warzone; }
	public ItemStack[] getSwordKit() { return swordkit; }
	public ItemStack[] getBowKit() { return bowkit; }
	public ItemStack[] getPotionKit() { return potionkit; }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("coords")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				int x = p.getLocation().getBlockX();
				int y = p.getLocation().getBlockY();
				int z = p.getLocation().getBlockZ();
				p.sendMessage("X: "+x+"; Y: "+y+"; Z: "+z);
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("warzone")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.teleport(warzone);
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.teleport(spawn);
			}
			return true;
		}
		
		//Sword kit
		if(cmd.getName().equalsIgnoreCase("swordkit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
						
				if(balance >= SWORDKIT_COST) {
					p.getInventory().addItem(swordkit);
					int newbalance = balance-SWORDKIT_COST;
					p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
					getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
				}
			}
			return true;
		}
		
		//Bow kit
		if(cmd.getName().equalsIgnoreCase("bowkit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
				
				if(balance >= BOWKIT_COST) {
					p.getInventory().addItem(bowkit);
					int newbalance = balance-BOWKIT_COST;
					p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
					getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
				}
			}
			return true;
		}
		
		//Potion kit
		if(cmd.getName().equalsIgnoreCase("potionkit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
				
				if(balance >= POTIONKIT_COST) {
					p.getInventory().addItem(potionkit);
					int newbalance = balance-POTIONKIT_COST;
					p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
					getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
				}
			}
			return true;
		}
		
		//Deluxe kit
		if(cmd.getName().equalsIgnoreCase("deluxekit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
				if(p.hasPermission("glowcloud.deluxe")) {
					if(balance >= DELUXEKIT_COST) {
						p.getInventory().addItem(deluxekit);
						int newbalance = balance-DELUXEKIT_COST;
						p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
						getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
					} else {
						p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
					}
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"Вам необходимо приобрести статус Deluxe. Сделать это можно на glowcloud.ru/store");
				}
			}
			return true;
		}
		
		//Gold kit
		if(cmd.getName().equalsIgnoreCase("goldkit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
				if(p.hasPermission("glowcloud.gold")) {
					if(balance >= GOLDKIT_COST) {
						p.getInventory().addItem(goldkit);
						int newbalance = balance-GOLDKIT_COST;
						p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
						getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
					} else {
						p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
					}
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"Вам необходимо приобрести статус Gold. Сделать это можно на glowcloud.ru/store");
				}
			}
			return true;
		}
		
		//Elite kit
		if(cmd.getName().equalsIgnoreCase("elitekit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
				if(p.hasPermission("glowcloud.elite")) {
					if(balance >= ELITEKIT_COST) {
						p.getInventory().addItem(elitekit);
						int newbalance = balance-ELITEKIT_COST;
						p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
						getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
					} else {
						p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
					}
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"Вам необходимо приобрести статус Elite. Сделать это можно на glowcloud.ru/store");
				}
			}
			return true;
		}
		
		//Ultra kit
		if(cmd.getName().equalsIgnoreCase("ultrakit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				String nickname = p.getDisplayName();
				int balance = p.getMetadata("balance").get(0).asInt();
				if(p.hasPermission("glowcloud.ultra")) {
					if(balance >= ULTRAKIT_COST) {
						p.getInventory().addItem(ultrakit);
						int newbalance = balance-ULTRAKIT_COST;
						p.setMetadata("balance", new FixedMetadataValue(this, newbalance));
						getServer().dispatchCommand(getServer().getConsoleSender(), "updatebalance "+nickname+" "+newbalance);
					} else {
						p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"У вас недостаточно средств");
					}
				} else {
					p.sendMessage(ChatColor.GRAY+""+ChatColor.BOLD+"| "+ChatColor.RED+"Вам необходимо приобрести статус Ultra. Сделать это можно на glowcloud.ru/store");
				}
			}
			return true;
		}
		
		return false; 
	}
	
	public void onDisable() {
		
	}

}
