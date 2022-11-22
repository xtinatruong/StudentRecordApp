package serverModel;


import java.util.ArrayList;

import serverModel.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class AdminList implements IDBCredentials {
	
	private ArrayList <Administrator> adminList;
	// Attributes
	private Connection conn;
	private ResultSet rs;
		public AdminList() {
			adminList = new ArrayList <Administrator> ();
			initializeConnection();
			readFile();
		}

		public void initializeConnection() {
			try {
				// Register JDBC driver
				Driver driver = new com.mysql.cj.jdbc.Driver();
				DriverManager.registerDriver(driver);
				// Open a connection
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				System.out.println("Problem");
				e.printStackTrace();
			}
	
		}
	
		public void close() {
			try {
				// rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void readFile() {	
			String sql="SELECT * FROM admin";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next())
				{
				    String name = rs.getString("name");
				    int num = rs.getInt("id");
				    adminList.add(new Administrator(name, num));				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

/**
 * Checks id and name
 * @param id
 * @param name
 * @return true if id and name match with database otherwise returns false
 */
	public boolean checkLoginInfo(int id, String name) {
		boolean login = false; 
		try { 
			 String queryString = "SELECT * FROM admin where id ='" +id+"' and name ='"+name+"'";
		        //set this values using PreparedStatement
			 	PreparedStatement ps = conn.prepareStatement(queryString);
			 	ResultSet results = ps.executeQuery(queryString); //where ps is Object of PreparedStatement
			 	
				 if(results.next()) {
		             login = true;
		        }else {
		        	login = false;
		        }
				 ps.close();
		    } catch (SQLException err) {
		        System.out.println(err);
		    }
		return login;
	}

public void insertUserPreparedStatement(int id, String name) {
		try {
			String query = "INSERT INTO admin (ID, name) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}

public void createTable() {
		String sql = "CREATE TABLE admin " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				 + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created table in given database...");
	}

public void setAdminList(ArrayList<Administrator> adminList) {
	this.adminList = adminList;
}


public Administrator findAdmin(int id) {
	
	for(Administrator s : adminList) {
		if(s.getId() == id) {
			return s;
		}
	}
	System.out.println("Admin with id " + id + " could not be found");
	return null;
}

@Override
public String toString() {
	
	if(adminList.isEmpty()) {
		return "The directory is empty";
	}
	
	String st = "";
	for(Administrator i : adminList) {
		st += i.getId() + "\t" + i.getName() + "\n";
	}
	return st;
}

//	public static void main(String[] args0) {
//		AdminList adminList = new AdminList();
//		System.out.println(adminList.toString());
//	}

}
