package ru.glowcloud.plugin.creative;

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
	private ResultSet rs, rs1, rs2;
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
			rs1 = st.getResultSet();
			rs2 = st.getResultSet();
			p.getServer().getLogger().info("Connected to the MySQL!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Plot[] importPlots() {
		Plot[] plots = null;
		try {
			rs = st.executeQuery("SELECT * FROM plots");
			plots = new Plot[rs.getFetchSize()];
			for(int i = 0; i < plots.length; i++) {
				rs.next();
				plots[i] = new Plot(rs.getInt("x1"),rs.getInt("z1"),rs.getInt("x2"),rs.getInt("z2"));
				int plotID = rs.getInt("id");
				rs1 = st.executeQuery("SELECT * FROM plots_members WHERE id_plot="+plotID);
				while(rs1.next()) {
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plots;
	}
	
}
