package serverModel;

import java.util.ArrayList;

/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public class Student extends Account {

	private ArrayList <Registration> studentRegList;
	
	public Student (String studentName, int studentId) {
		super(studentName, studentId);
		
		studentRegList = new ArrayList <Registration> ();
	}

	public ArrayList <Registration> getRegList(){
		return studentRegList;
	}
	
	@Override
	public String toString () {
		String st = "Student Name: " + getName() + "\n" +
				"Student Id: " + getId() + "\n";
		return st;
	}

	public void addRegistration(Registration registration) {
		
		if(studentRegList.size() > 5) {
			System.out.println(name + " is already registered to 6 courses");
		}else if(checkCourseReg(registration)) {
			System.out.println(name + " is already registered to that course");
		}else
			studentRegList.add(registration);

		
	}
	
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
	
	public boolean removeCourse(Course course) {
		
		for(Registration r : studentRegList) {
			
			if(r.getTheOffering().getTheCourse().equals(course)) {

				studentRegList.remove(r);
				return true;
			}
		}
		return false;
	}

	public String regListToString() {
		
		String str = "";
		for(Registration reg : studentRegList) {
			str += reg;
		}
		return str;
	}
}

