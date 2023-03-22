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
 * Retrieves data from the data base and puts it into an ArrayList as Student information.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 18, 2020
 * @version 1.8
 */
public class StudentList implements IDBCredentials{
	
	/**
	 * Connection to the database
	 */
	private Connection conn;
	
	/**
	 * Gets results from the database
	 */
	private ResultSet rs;
	
	/**
	 * ArrayList storing student information from database
	 */
	private ArrayList<Student> studentList;
	
	/**
	 * Initializes connection and populates ArrayList from database
	 */
	public StudentList() {
		studentList = new ArrayList <Student> ();
		initializeConnection();
		readFile();
	}
	
	/**
	 * Initializes connection to database
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
	 * Closes the connection to database
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Validates login information of student
	 * @param id student ID
	 * @param name student name
	 * @return true if student found, false if not
	 */
	public boolean checkLoginInfo(int id, String name) {
		boolean login = false; 
		try { 
			 String queryString = "SELECT * FROM student where id ='" +id+"' and name ='"+name+"'";
			 	PreparedStatement ps = conn.prepareStatement(queryString);
			 	rs = ps.executeQuery(queryString); 
			 	
				 if(rs.next()) {
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
	
	/**
	 * Adds to SQL database
	 * @param id student ID to be added
	 * @param name student name to be added
	 */
	public void insertUserPreparedStatement(int id, String name) {
			try {
				String query = "INSERT INTO student (ID, name) values(?,?)";
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
	
	/**
	 * Creates a table for the database
	 */
	public void createTable() {
		String sql = "CREATE TABLE student " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				 + " PRIMARY KEY ( id ))";

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
	 * Returns the student list
	 * @return studentList
	 */
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	/**
	 * Reads from database and populates studentList
	 */
	public void readFile() {
		
		String sql="SELECT * FROM student";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
			
			while(rs.next())
			{
			    String sName = rs.getString("name");
			    int sNum = rs.getInt("id");
			    studentList.add(new Student(sName, sNum));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets stutdentList to given argument
	 * @param studentList list to be set
	 */
	public void setStudentList(ArrayList <Student> studentList) {
		this.studentList = studentList;
	}
	
	/**
	 * Searches for a student by their ID number
	 * @param id student ID
	 * @return Student if found, null if not
	 */
	public Student findStudent(int id) {
		
		for(Student s : studentList) {
			if(s.getId() == id) {
				return s;
			}
		}
		System.out.println("Student with id " + id + " could not be found");
		return null;
	}
	
	/**
	 * Registers a given student with a given CourseOffering
	 * @param id ID of the student
	 * @param offer course offering to be registered
	 */
	public void registerStudent(int id, CourseOffering offer) {
		
		Student target = findStudent(id);
		
		if(target != null) {
			
			new Registration(target, offer);
			
		}
	}
	
	/**
	 * Removes the registration of a student
	 * @param id ID of the student
	 * @param course course to be removed
	 */
	public void removeCourseReg(int id, Course course) {
		
		Student target = findStudent(id);
		
		if(target != null) {
			if(target.removeCourse(course)) {
				System.out.println("The Course Was Successfully Removed");
				return;
			}
		}
		System.out.println("The Course Could Not Be Removed");
	}
	
	/**
	 * Creates a string with all the courses student is registered in
	 * @param id student ID
	 * @return string listing student courses
	 */
	public String viewStudentCourse(int id) {
		
		Student target = findStudent(id);
		String str = "";
		if(target != null && !target.getRegList().isEmpty()) {
			str += target.regListToString();
		}else {
			str += target.getName() + " is not registered to any courses";
		}
		
		return str;
	}
	
	/**
	 * converts studentList to a string with each students information
	 */
	@Override
	public String toString() {
		
		if(studentList.isEmpty()) {
			return "The directory is empty";
		}
		
		String st = "";
		for(Student i : studentList) {
			if(i.getId() > 999 && i.getId() < 10000) {
				st += i.getId() + "\t" + i.getName() + "\n";
			}
		}
		
		return st;
	}
	
}
