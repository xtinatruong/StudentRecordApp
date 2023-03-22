package serverModel;


import java.util.ArrayList;

import java.sql.*;

/**
 * Retrieves data from the data base and puts it into an ArrayList as Admin information.
 * @author Rohit Yeast, Desiree Leal, Hoang Truong
 * @since April 18, 2020
 * @version 1.8
 */
public class AdminList implements IDBCredentials {
	
	/**
	 * ArrayList that stores all the administration information.
	 */
	private ArrayList <Administrator> adminList;

	/**
	 * Connection to the database
	 */
	private Connection conn;
	
	/**
	 * Table of information from the database.
	 */
	private ResultSet rs;
	
	/**
	 * Initializes a new ArrayList of Administrator and calls methods to initialize connection to database
	 * and read information from ResultSet.
	 */
	public AdminList() {			
		adminList = new ArrayList <Administrator> ();
		initializeConnection();
		readFile();
	}

	/**
	 * Initializes a connection to the database with a Driver.
	 */
	public void initializeConnection() {
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the connection
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the file of admin from the database.
	 */
	public void readFile() {	
		String sql="SELECT * FROM admin";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
				
			while(rs.next())
			{
				String name = rs.getString("name");
			    int num = rs.getInt("id");
			    adminList.add(new Administrator(name, num));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	/**
	 * sets the adminList to a given one
	 * @param adminList
	 */
	public void setAdminList(ArrayList<Administrator> adminList) {
		this.adminList = adminList;
	}

	/**
	 * Searches for an admin based on their ID
	 * @param id the ID of an admin
	 * @return the admin if found, null if not
	 */
	public Administrator findAdmin(int id) {
	
		for(Administrator s : adminList) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Converts adminList to a string
	 */
	@Override
	public String toString() {
	
		if(adminList.isEmpty()) {
			return "The directory is empty";
		}
	
		String st = "";
		for(Administrator i : adminList) {
			
			if(i.getId() > 999 && i.getId() < 10000) {
				st += i.getId() + "\t" + i.getName() + "\n";
			}
		}
		return st;
	}

}
