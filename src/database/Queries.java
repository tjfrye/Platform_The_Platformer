package database;

import java.sql.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Queries {
	
	static Connection conn;
	
	public static void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/primary", "root", "password");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static int login(String username, String password) {
		int id = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select id from `primary`.login where username='" + username + "' and password='" + password + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return id;
	}
	
	public static int createUser(String username, String password) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into `primary`.login (username, password, highscore) values ('" + username + "', '" + password + "', '0')";
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e);
		}
		return result;
	}
	
	public static int getHighscore(String username) {
		int highscore = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select highscore from `primary`.login where username='" + username + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				highscore = rs.getInt("highscore");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return highscore;
	}
	
	public static int updateHighscore(String username, int newHighscore) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "update `primary`.login set highscore='" + newHighscore + "' where username='" + username + "'";
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e);
		}
		return result;
	}
	
	public static ObservableList<Entry> listHighscores() {
		int i = 1;
		ObservableList<Entry> data = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from `primary`.login order by highscore desc limit 10";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				data.add(new Entry(Integer.toString(i), rs.getString("username"), Integer.toString(rs.getInt("highscore"))));
				i++;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return data;
	}
	
	public static class Entry {
		private SimpleStringProperty place;
		private SimpleStringProperty username;
		private SimpleStringProperty highscore;
		
		private Entry(String p, String uName, String hScore) {
			this.place = new SimpleStringProperty(p);
			this.username = new SimpleStringProperty(uName);
			this.highscore = new SimpleStringProperty(hScore);
		}
		
		public String getPlace() {
			return place.get();
		}
		
		public void setPlace(String p) {
			place.set(p);
		}
		
		public String getUsername() {
			return username.get();
		}
		
		public void setString(String uName) {
			username.set(uName);
		}
		
		public String getHighscore() {
			return highscore.get();
		}
		
		public void setHighscore(String hScore) {
			highscore.set(hScore);
		}
	}
}
