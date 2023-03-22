package serverModel;

import java.util.ArrayList;

/**
 * Contains information about a student with all the classes they are registered in
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 18, 2020
 * @version 1.8
 */
public class Student extends Account {

	/**
	 * ArrayList containing information about the students registration
	 */
	private ArrayList <Registration> studentRegList;
	
	/**
	 * Constructs a student and initializes their studentRegList
	 * @param studentName name of the student
	 * @param studentId ID of the student
	 */
	public Student (String studentName, int studentId) {
		super(studentName, studentId);
		
		studentRegList = new ArrayList <Registration> ();
	}

	/**
	 * Returns the ArrayList of student registration
	 * @return studentRegList
	 */
	public ArrayList <Registration> getRegList(){
		return studentRegList;
	}
	
	/**
	 * Returns a string with student information
	 */
	@Override
	public String toString () {
		String st = "Student Name: " + getName() + "\n" +
				"Student Id: " + getId() + "\n";
		return st;
	}

	/**
	 * Adds a registration to the studentRegList
	 * @param registration registration to be added to ArrayList
	 */
	public void addRegistration(Registration registration) {
		
		if(studentRegList.size() > 5) {
			System.out.println(name + " is already registered to 6 courses");
		}else if(checkCourseReg(registration)) {
			System.out.println(name + " is already registered to that course");
		}else
			studentRegList.add(registration);
	}
	
	/**
	 * Checks if a registration is in studentRegList
	 * @param reg registration being checked
	 * @return true if reg is already in studentRegList, false if not
	 */
	private boolean checkCourseReg(Registration reg) {
		
		if(studentRegList.size() <= 0)
			return false;
		
		for(Registration regList : studentRegList) {
			
			String temp1 = reg.getTheOffering().getTheCourse().getCourseName();
			String temp2 = regList.getTheOffering().getTheCourse().getCourseName();
			int x = reg.getTheOffering().getTheCourse().getCourseNum();
			int y = regList.getTheOffering().getTheCourse().getCourseNum();
			
			if(temp1.equals(temp2) && x == y) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes a registration matching a course from studentRegList
	 * @param course to be removed
	 * @return true if course is removed, false if not
	 */
	public boolean removeCourse(Course course) {
		
		for(Registration r : studentRegList) {
			
			if(r.getTheOffering().getTheCourse().equals(course)) {

				studentRegList.remove(r);
				return true;
			}
		}
		return false;
	}

	/**
	 * Converts registration info to a String
	 * @return String with registration info
	 */
	public String regListToString() {
		
		String str = "";
		for(Registration reg : studentRegList) {
			str += reg;
		}
		return str;
	}
}

