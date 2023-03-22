package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serverModel.*;

/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public class Controller implements Runnable{

	/**
	 * Boolean that is true while the thread is running
	 */
	private boolean running;			
	
	/**
	 * The account holder logged in
	 */
	private Account account;
	
	/**
	 * A reference to the course catalogue from the database
	 */
	private CourseCatalogue catalogue;
	
	/**
	 * A reference to the list of students from the database
	 */
	private StudentList studentList;
	
	/**
	 * A reference to the list of administration from the database
	 */
	private AdminList adminList;
	
	/**
	 * Printwriter to communicate with the client
	 */
	private PrintWriter socketOut;
	
	/**
	 * Reader for incoming strings from the client
	 */
	private BufferedReader socketIn;
	
	/**
	 * Constructor of the controller to initialize all the member variables and calls method login that will 
	 * prompt the client to login
	 * @param socket the socket that communicates with client
	 * @param studentList list of students
	 * @param catalogue list of courses
	 * @param adminList list of admin
	 */
	public Controller(Socket socket, StudentList studentList, CourseCatalogue catalogue, AdminList adminList) {
		
		running = true;
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
			
			this.studentList = studentList;	
			this.catalogue = catalogue;		
			this.adminList = adminList;
			login();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Calls method readUserInput that communicates with the client
	 */
	@Override
	public void run() {
		
		readUserInput();
	}

	/**
	 * Gets input from client and calls method menu.
	 * @throws IOException
	 */
	private void readUserInput () {
		
		String line = "";
		String response = "";
			
		try {
			while (running) {  
				
				line = socketIn.readLine();
				if(line == null) {
					running = false;
					break;
				}
				
				char c = line.charAt(0);
				if (c < '0' || c > '9') {
		            running = false;
		            break;
		        }
				
				int choice = Integer.parseInt(String.valueOf(line.charAt(0)));
				response = menu(choice, line);
				socketOut.println(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			terminate();
		}
	}
	
	/**
	 * Validates the login information from the client
	 */
	private  void login() {
		
		String line = "";
		String response = "";
			
		try {
			do {
				line = socketIn.readLine();
				if(line == null) {
					running = false;
				}else {
					String [] split = line.split("\t");
					
					int code = Integer.parseInt(split[0]);
					
					if(code == 0) {
						response = validateStudent(split[1], split[2]) + "\0";
					}else if(code == 1) {
						
						response = validateAdmin(split[1], split[2]) + "\0"; 
					}else {
						response = "Error logging in";
						running = false;
					}
					
					socketOut.println(response);
				}
			} while (account == null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Attempts to close streams.
	 */
	private void terminate() {
		
		running = false;
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Goes through a switch statement depending on what the client's choice was.
	 * @param choice indicates what their choice is.
	 * @param line a line of inputs from the client.
	 * @return a string to be passed to the server to be communicated back to client.
	 */
	private String menu(int choice, String line) {
		boolean toggle = true;
		String [] split = line.split("\t");
		while(toggle) {
			
			switch(choice) {
			case 0:
				return getStudentList() + "\0";
			case 1:
				return searchForCourse(split[1], split[2]) + "\0";
			case 2: 
				return addCourseToStudent(split[1], split[2]) + "\0";
			case 3:
				return removeCourseFromStudent(split[1], split[2]) + "\0";
			case 4:
				return getCourseCatalogue() + "\0";
			case 5:
				return getStudentCourse() + "\0";
			case 6:
				return addCourseToCatalogue(split[1], split[2]) + "\0";
			case 7:
				return removeCourseCatalague(split[1], split[2]) + "\0";
			case 8:		
				return addStudent(split[1], split[2]) + "\0";
			case 9:
				return removeStudent(split[1], split[2]) + "\0";
			default:
				return "\0";
			}
		}
		return null;
	}
	
	/**
	 * This method searches for a course by the number and name input by the client.
	 * @param courseNum the desired course number
	 * @param courseName the desired course name
	 * @return string verifying whether the course was found or not.
	 */
	private  String searchForCourse (String courseName, String courseNum) {
		
		if(!testCourseInput(courseName, courseNum)) {			
			return "Invalid Input";
		}
		
		Course c = catalogue.searchCat(courseName, Integer.parseInt(courseNum));
		if (c == null)
			return "Course " + courseName + " " + courseNum + " not found";
		else
			return "Course was found\n" + c.toString();
	}
	
	/**
	 * This method first searches to see if the input course exists . 
	 * @param courseNum the number of the course the student wants to add
	 * @param courseName the course the student wants to add
	 * @param secNum the section number the student wants to enroll in
	 * @param id the id of the student
	 * @return string verifying if the course was successfully added or not
	 */
	private String addCourseToStudent (String courseName, String courseNum) {
		
		if(!testCourseInput(courseName, courseNum)) {			
			return "Invalid Input";
		}
		
		Course c = catalogue.searchCat(courseName, Integer.parseInt(courseNum));
		
		if (c == null)
				return courseName + " " + courseNum + " was not found - unable to add course";
		else {
			CourseOffering cOff = c.getCourseOfferingAt(0);
			if (cOff != null)
				studentList.registerStudent(account.getId(), cOff);
		}
		return courseName + " " + courseNum + " was added to courses";
	}
	
	/**
	 * Removes a given course from the student
	 * @param courseName the desired course
	 * @param courseNum the number of course
	 * @param id student id
	 * @return a string verifying if the course was removed or not
	 */
	private String removeCourseFromStudent (String courseName, String courseNum) {
		
		if(!testCourseInput(courseName, courseNum)) {
			return "Invalid Input";
		}
		
		Course c = catalogue.searchCat(courseName, Integer.parseInt(courseNum));
		if (c == null)
			return courseName + " " + courseNum + " was not found - unable to remove it";
		else
			studentList.removeCourseReg(account.getId(), c);
			return courseName + " " + courseNum + " was removed successfully";
	}
	
	/**
	 * Returns a string with a list of all courses
	 * @return string listing all courses
	 */
	private String getCourseCatalogue() {

		return catalogue.toString();
	}
	
	/**
	 * Calls method viewStudentCourse with the student id to list all of that specific students courses.
	 * @param id
	 * @return a string with all the courses that student is enrolled in
	 */
	private String getStudentCourse() {
		String str = studentList.findStudent(account.getId()).toString();
		str += studentList.viewStudentCourse(account.getId());
		return str;
	}
	
	/**
	 * Attempts to add a new course to the database.
	 * @param courseName The name of the course.
	 * @param courseNum The course number.
	 * @return A message indicating if the course was added or not.
	 */
	private String addCourseToCatalogue(String courseName, String courseNum) { 
		
		if(!testCourseInput(courseName, courseNum)) {
			return "Invalid Input";
		}
			
		int num = Integer.parseInt(courseNum);
		Course c = catalogue.searchCat(courseName, num);
		if(c != null) {
			return courseName + " " + courseNum + " is already in the Catalogue";
		}
		Course course = new Course(courseName, num);
		catalogue.getCourseList().add(course);		
		course.addOffering(new CourseOffering(1, 100));
		catalogue.insertUserPreparedStatement(num, courseName);
		return courseName + " " + courseNum + " has been added to the Catalogue";
	}
	
	/**
	 * Attempts to remove a course from the database.
	 * @param courseName The course name.
	 * @param courseNum The course number.
	 * @return A message indicating if the course was removed or not.
	 */
	private String removeCourseCatalague(String courseName, String courseNum) {
		
		if(!testCourseInput(courseName, courseNum)) {
			return "Invalid Input";
		}
		
		int num = Integer.parseInt(courseNum);
		Course c = catalogue.searchCat(courseName, num);
		
		if(catalogue.getCourseList().remove(c)) {
			return courseName + " " + courseNum + " has been removed from the Catalogue";
		} else {
			return courseName + " " + courseNum + " is not in the Catalogue";
		}
	}
	
	/**
	 * Attempts to add a new student to the database.
	 * @param name The name of the student.
	 * @param id The id number of the student.
	 * @return A message indicating if the student was added or not.
	 */
	private String addStudent(String name, String id) {
		
		if(!testStudentId(id)) {
			return "Invalid student ID";
		}
		int num = Integer.parseInt(id);
		
		if(studentList.findStudent(num) != null) {
			return "The ID " + id + " is already in use";
		}
		studentList.getStudentList().add(new Student(name, num));
		studentList.insertUserPreparedStatement(num, name);
		return name + " was added with the ID " + id;
	}
	
	/**
	 * Attempts to remove a student from the database. Requires the ID to correspond to
	 * the student in order for the data to be deleted.
	 * @param name The name of the student.
	 * @param id Then ID of the student.
	 * @return A message indicating if the student was removed or not.
	 */
	private String removeStudent(String name, String id) {
		
		if(!testStudentId(id)) {
			return "Invalid student ID";
		}
		
		Student s = studentList.findStudent(Integer.parseInt(id));
		
		if(s == null) {
			return "Student with the ID " + id + " could not be found";
		}
		if(!s.getName().equals(name)) {
			return "The name entered does not match the ID: " + id + "\nThe student was not removed";
		}
		studentList.getStudentList().remove(s);
		return name + ", with the ID: " + id + " was removed";
	}
	
	/**
	 * Returns the names and IDs of all students in the directory.
	 * @return The students as a string.
	 */
	private String getStudentList() {
		
		return studentList.toString();
	}
	
	/**
	 * Method checks if the student ID exist in the database and the name matches it. 
	 * @param id The id of the student.
	 * @param name The name of the student.
	 * @return A message indicating if the info entered is valid.
	 */
	private String validateStudent(String id, String name) {
		
		if(isInteger(id)) {
			account = studentList.findStudent(Integer.parseInt(id));
		}
		if (account != null && name.equals(account.getName())) {
			return "Login Sucessful. ";
		}else {
			account = null;
			return "Invalid student ID. Please try another one. ";
		}
	}
	
	/**
	 * Method checks if the admin ID exists in the database and if the name matches it. 
	 * @param id The id of the admin.
	 * @param name The name of the admin.
	 * @return A message indicating if the info entered is valid.
	 */
	private String validateAdmin(String id, String name) {
		
		if(isInteger(id)) {
			account = adminList.findAdmin(Integer.parseInt(id));
		}
		if (account != null && name.equals(account.getName())) {
			return "Login Sucessful. ";
		}else {
			account = null;
			return "Invalid admin ID. Please try another one. ";
		}
	}
	
	/**
	 * Helper method that checks if a String represents an integer.
	 * @param str The String tested.
	 * @return True if the string represents an integer and false otherwise.
	 */
	private boolean isInteger(String str) {
		
	    if (str == null) {
	        return false;
	    }
	    
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    
	    for (int i = 0; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * Returns true if the course info added is valid.
	 * @param courseName The name of the course.
	 * @param courseNum The number of the course.
	 * @return True if the inputs are valid and false otherwise.
	 */
	private boolean testCourseInput(String courseName, String courseNum) {
		
		return isInteger(courseNum) && courseName.length() == 4 && courseNum.length() == 3;
	}
	
	/**
	 * Tests if a String is a valid ID for this program. 
	 * @param id The ID tested
	 * @return True if it is valid and false otherwise.
	 */
	private boolean testStudentId(String id) {
		
		return isInteger(id) && id.length() == 4;
	}
	
}
