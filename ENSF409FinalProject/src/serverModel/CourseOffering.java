package serverModel;

import java.util.ArrayList;

/**
 * Contains information about the offering of a course
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 6, 2020
 * @version 1.8
 */
public class CourseOffering {

	/**
	 * Number with the section of a course
	 */
	private int secNum;
	
	/**
	 * Section capacity
	 */
	private int secCap;
	
	/**
	 * Specific course
	 */
	private Course theCourse;
	
	/**
	 * ArrayList with registration information
	 */
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * Sets section number and capacity and initializes ArrayList
	 * @param secNum section number
	 * @param secCap section capacity
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	/**
	 * Returns the section number
	 * @return secNum
	 */
	public int getSecNum() {
		return secNum;
	}
	
	/**
	 * Sets the section number
	 * @param num the number to be set
	 */
	public void setSecNum(int num) {
		this.secNum = num;
	}
	
	/**
	 * Returns the course
	 * @return theCourse
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	
	/**
	 * Sets the course to a given argument
	 * @param course the Course to be set
	 */
	public void setTheCourse(Course course) {
		this.theCourse = course;
	}
	
	/**
	 * Returns the section capacity
	 * @return secCap
	 */
	public int getSecCap() {
		return secCap;
	}
	
	/**
	 * Sets the section capacity
	 * @param cap the capacity to be set
	 */
	public void setSecCap(int cap) {
		this.secCap = cap;
	}
	
	/**
	 * Converts courseOffering to a string
	 */
	@Override
	public String toString () {

		String st = "Section Num: " + getSecNum() + ", section cap: "+ getSecCap();
		return st;
	}
	
	/**
	 * Adds a registration to offeringList
	 * @param reg Registration to be added
	 */
	public void addRegistration(Registration reg) {
		offeringRegList.add(reg);
	}
}