package ru.glowcloud.plugin.proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import net.md_5.bungee.api.plugin.Plugin;

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
			p.getProxy().getLogger().info("Connected to the MySQL!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getOnJoin(String login) {
		int status = 0;
		login = login.toLowerCase();
		try {
			rs = st.executeQuery("SELECT * FROM users WHERE login="+login);
			rs.next();
			status = rs.getInt("onjoin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	
}
