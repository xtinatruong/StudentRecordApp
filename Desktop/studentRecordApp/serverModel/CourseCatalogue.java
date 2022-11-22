package serverModel;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */

public class CourseCatalogue implements IDBCredentials{
	private ArrayList <Course> courseList;
	private Connection conn;
	private ResultSet rs;
	/**
	 * Initializes connection and populate array list with sql 
	 */
	public CourseCatalogue () {
		courseList = new ArrayList <Course> ();
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

	public void insertUserPreparedStatement(int id, String name) {
		try {
			String query = "INSERT INTO course (ID, name) values(?,?)";
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
		String sql = "CREATE TABLE course " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
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
	
	private void readFile() {
		String sql="SELECT * FROM course";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			int i = 0;
			while(rs.next())
			{
			    String courseName = rs.getString("course name");
			    int courseNum = rs.getInt("course number");
			    courseList.add(new Course(courseName, courseNum));
				courseList.get(i).addOffering(new CourseOffering(1, 100));
				i++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		return null;
	}
	
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	@Override
	public String toString () {
		
		if(courseList.isEmpty()) {
			return "The course catalogue is empty";
		}
		
		String st = "";
		for (Course c : courseList) {
			st += c + "\n"; 
		}
		return st;
	}


//	public static void main (String [] args) {
//		CourseCatalogue catalogue = new CourseCatalogue();
//		System.out.println(catalogue.toString());
//	}
	
}