package ru.glowcloud.plugin.creative;

import java.util.List;

import org.bukkit.entity.Player;

public class Plot {
	private int x1, z1, x2, z2;
	private Player owner;
	private List<String> members;
	
	public Plot(int x1, int z1, int x2, int z2) {
		this.x1 = x1;
		this.z1 = z1;
		this.x2 = x2;
		this.z2 = z2;
	}
	public List<String> getMembers() { return members; }
}
