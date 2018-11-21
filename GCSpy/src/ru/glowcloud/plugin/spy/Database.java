package ru.glowcloud.plugin.spy;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class Database extends Thread {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private final String URL = "jdbc:mysql://localhost:3306/minecraft";
	private final String USER = "root";
	private final String PWD = "5r7UC12GM0";
	private Plugin p;
	
	public Database(Plugin p) {
		this.p = p;
	}
	@Override
	public void run() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
			st = conn.createStatement();
			rs = st.getResultSet();
			p.getServer().getLogger().info("Connected to the MySQL!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addTime(String login, long time) {
		login = login.toLowerCase();
		try {
			st.executeUpdate("UPDATE users SET played = played + "+time+" WHERE login='"+login+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
