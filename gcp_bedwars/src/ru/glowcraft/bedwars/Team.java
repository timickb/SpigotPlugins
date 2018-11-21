package ru.glowcraft.bedwars;

import java.util.Collection;
import java.util.List;

import org.bukkit.entity.Player;

public class Team {
	private int color;
	private Collection<Player> players;
	private Bed bed;
	
	public Team(int color, Bed bed) {
		this.color = color;
		this.bed = bed;
	}
	public int getColor() { return color; }
	public Bed getBed() { return bed; }
	public int getAmount() {
		try {
			return players.size(); 
		} catch(NullPointerException e) {
			return 0;
		}
	}
	public Collection<Player> getPlayers() { return players; }

}
