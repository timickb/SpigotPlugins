package ru.glowcraft.bedwars;

public class Bed {
	private int x,y,z;
	private boolean broken = false;
	
	public Bed(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void broke() { broken = true; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return z; }
	public boolean isBroken() { return broken; }
}
