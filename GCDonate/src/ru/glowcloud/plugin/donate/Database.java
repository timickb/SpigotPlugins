package ru.glowcloud.plugin.donate;

import java.sql.*;

public class Database extends Thread {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private final String URL = "jdbc:mysql://localhost:3306/minecraft";
	private final String USER = "root";
	private final String PWD = "Y23bc666777";
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
	public String getStatus(String login) {
		String status = "";
		try {
			rs = st.executeQuery("SELECT * FROM users WHERE login='"+login+"'");
			status = rs.getString("status");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
