package serverModel;

import java.util.ArrayList;

/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public class CourseOffering {

	private int secNum;
	private int secCap;
	private Course theCourse;
	private ArrayList <Registration> offeringRegList;
	
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	public int getSecNum() {
		return secNum;
	}
	
	public void setSecNum(int num) {
		this.secNum = num;
	}
	
	public Course getTheCourse() {
		return theCourse;
	}
	
	public void setTheCourse(Course course) {
		this.theCourse = course;
	}
	
	public int getSecCap() {
		return secCap;
	}
	
	public void setSecCap(int cap) {
		this.secCap = cap;
	}
	
	@Override
	public String toString () {

		String st = "Section Num: " + getSecNum() + ", section cap: "+ getSecCap();
		return st;
	}
	public void addRegistration(Registration reg) {
		offeringRegList.add(reg);
	}
}