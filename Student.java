package lab3_part2;

import java.util.ArrayList;

public class Student {
	
	private String studentName;
	private int studentId;
	private ArrayList<Registration> studentRegList;
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n";
		return st;
	}

	public void addRegistration(Registration registration) {
		
		if(studentRegList.size() > 5) {
			System.out.println("\n" + studentName + " is already registered to 6 courses");
		}else if(checkCourseReg(registration)) {
			System.out.println("\n" + studentName + " is already registered to that course");
		}else
			studentRegList.add(registration);
			System.out.println("\n" + studentName + " has been added to that course");
		
	}
	
	private boolean checkCourseReg(Registration reg) {
		
		if(studentRegList.size() <= 0)
			return false;
		
		for(Registration regList : studentRegList) {
			
			String temp1 = reg.getTheOffering().getTheCourse().getCourseName();
			String temp2 = regList.getTheOffering().getTheCourse().getCourseName();
			int tem1 = reg.getTheOffering().getTheCourse().getCourseNum();
			int tem2 = regList.getTheOffering().getTheCourse().getCourseNum();
			
			if(temp1.equals(temp2) && tem1 == tem2) {
				return true;
			}
		}
		return false;
	}
	
	public boolean removeCourse(Course course) {
		
		for(Registration i : studentRegList) {
			
			if(i.getTheOffering().getTheCourse().equals(course)) {

				studentRegList.remove(i);
				return true;
			}
		}
		return false;
	}

	public String regListToString() {
		
		String str = "\n---------------------------------------\n";
		str += "Student Name: " + this + "\n";
		
		for(Registration reg : studentRegList) {
			str += reg.toString();
		}
		str += "\n---------------------------------------\n";
		return str;
	}
}
