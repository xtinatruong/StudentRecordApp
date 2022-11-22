package serverModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class StudentList implements IDBCredentials{
	// Attributes
	private Connection conn;
	private ResultSet rs;
	private ArrayList<Student> studentList;
	
	public StudentList() {
		studentList = new ArrayList <Student> ();
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
	
	public boolean checkLoginInfo(int id, String name) {
		boolean login = false; 
		try { 
			 String queryString = "SELECT * FROM student where id ='" +id+"' and name ='"+name+"'";
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
	
	public void createTable() {
		String sql = "CREATE TABLE student " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
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
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public void readFile() {

//		try {
//			FileReader input = new FileReader ("student.txt");
//			BufferedReader fileReader = new BufferedReader (input);
//			String line = "";			
//		while ((line = fileReader.readLine()) != null) {
//			String temp[] = line.split(" ");
//			int num = Integer.parseInt(temp[1]);
//			studentList.add(new Student(temp[0], num));
//		}		
//			fileReader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
		String sql="SELECT * FROM student";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			
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
	
	public void setStudentList(ArrayList <Student> studentList) {
		this.studentList = studentList;
	}
	
	
	public Student findStudent(int id) {
		
		for(Student s : studentList) {
			if(s.getId() == id) {
				return s;
			}
		}
		System.out.println("Student with id " + id + " could not be found");
		return null;
	}
	
	
	public void registerStudent(int id, CourseOffering offer) {
		
		Student target = findStudent(id);
		
		if(target != null) {
			
			new Registration(target, offer);
			
		}
	}
	
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
	
	@Override
	public String toString() {
		
		if(studentList.isEmpty()) {
			return "The directory is empty";
		}
		
		String st = "";
		for(Student i : studentList) {
			st += i.getId() + "\t" + i.getName() + "\n";
		}
		
		return st;
	}
	
//	public static void main(String args[]) {
//		StudentList sList = new StudentList();
//		System.out.println(sList.toString());
//	}
}
