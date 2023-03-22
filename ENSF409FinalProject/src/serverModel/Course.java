package serverModel;

import java.util.ArrayList;

/**
 * Contains all the courses in the catalogue
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 11, 2020
 * @version 1.8
 */
public class Course {

	/**
	 * String with the course name
	 */
	private String courseName;
	
	/**
	 * Number of the course
	 */
	private int courseNum;
	
	/**
	 * ArrayList containing all the offerings of each course
	 */
	private ArrayList <CourseOffering> offeringList;

	/**
	 * Initializes all the member variables and arrayList
	 * @param courseName name of the course
	 * @param courseNum number of the course
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);

		offeringList = new ArrayList<CourseOffering>();
	}

	/**
	 * Adds an offering to the Course
	 * @param offering to be added to the offering list
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			offeringList.add(offering);
		}
	}

	/**
	 * Returns the course name
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the courseName
	 * @param courseName name to be set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Returns the course number
	 * @return CourseNum
	 */
	public int getCourseNum() {
		return courseNum;
	}

	/**
	 * Sets the courseNum
	 * @param courseNum number to be set 
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/**
	 * Converts course and its offerings to a string
	 */
	@Override
	public String toString () {
		String st = getCourseName() + " " + getCourseNum();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c + "\n";
		return st;
	}

	/**
	 * Searches for an offering
	 * @param i section
	 * @return the offering information at given section
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i - 1);
	}
	
	/**
	 * Checks if a Course name and number equal this course name and number
	 * @param comp course to be compare
	 * @return true if equal, false if not
	 */
	public boolean equals(Course comp) {
		
		if(this.getCourseName().equals(comp.getCourseName())) {
			if(this.getCourseNum() == comp.getCourseNum())
				return true;
		}
		return false;
	}
	
	
}
