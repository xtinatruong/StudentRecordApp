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
 * Fetch's data from the database and loads it into an ArrayList to be used.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 17, 2020
 * @version 1.8
 */
public class CourseCatalogue implements IDBCredentials{
	
	/**
	 * An ArrayList of students
	 */
	private ArrayList <Course> courseList;
	
	/**
	 * The connection to the data base.
	 */
	private Connection conn;
	
	/**
	 * Table of course data from the database. 
	 */
	private ResultSet rs;
	
	/**
	 * Initializes connection and populate array list with sql 
	 */
	public CourseCatalogue () {
		courseList = new ArrayList <Course> ();
		initializeConnection();
		readFile();
	}
	
	/**
	 * Connects the CourseCatalogue to the database.
	 */
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

	/**
	 * Closes the connection to the database.
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds new course to the database.
	 * @param id The id of the course
	 * @param name The name of the course.
	 */
	public void insertUserPreparedStatement(int id, String name) {
		
		try {
			String query = "INSERT INTO course (name, ID) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setString(1, name);
			pStat.setInt(2, id);
			
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting course");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new table to be uploaded to the database.
	 */
	public void createTable() {
		String sql = "CREATE TABLE course " + "(id INTEGER not NULL, " + " name VARCHAR(255)) ";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created table in given database...");
	}
	
	/**
	 * Reads data from the database and places it into an ArrayList to be used.
	 */
	private void readFile() {
		String sql = "SELECT * FROM course";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
			int i = 0;
			while(rs.next())
			{
			    String courseName = rs.getString("name");
			    int courseNum = rs.getInt("ID");
			    courseList.add(new Course(courseName, courseNum));
				courseList.get(i).addOffering(new CourseOffering(1, 100));
				i++;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new course offering and adds it to the course
	 * @param c The course that the offering is added to.
	 * @param secNum The section number of the course.
	 * @param secCap The maximum capacity of the course.
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	/**
	 * Searches the catalogue for a course and returns that course.
	 * @param courseName The name of the course
	 * @param courseNum The course number
	 * @return The course if it is found and null otherwise.
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		return null;
	}
	
	/**
	 * Gets the array list of the course.
	 * @return The array list of course.
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	/**
	 * Sets the array list of course.
	 * @param courseList The new arraylist of course.
	 */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Converts the list to a string.
	 */
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
}