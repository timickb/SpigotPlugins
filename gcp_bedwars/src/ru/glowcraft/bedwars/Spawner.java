package ru.glowcraft.bedwars;

public class Spawner {
	private int type;
	private int x,y,z;
	
	public Spawner(int type, int x, int y, int z) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getType() { return type; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return z; }

}
