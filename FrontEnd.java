

import java.util.Scanner;

public class FrontEnd {
	
	private Scanner scan;
	private CourseCatalogue catalogue;
	private StudentList studentList;
	
	public FrontEnd() {
		scan = new Scanner(System.in);
		catalogue = new CourseCatalogue();
		studentList = new StudentList();
	}
	
	public void menu() {
		boolean toggle = true;
		while(toggle) {
			System.out.println("\nPress Enter to Continue");
			scan.nextLine();

			printMenuChoice();
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case 1:
				searchForCourse();
				break;
			case 2: 
				addCourseToStudent();
				break;
			case 3:
				removeCourseFromStudent();
				break;
			case 4:
				viewCourseCatalogue();
				break;
			case 5:
				viewStudentCourse();
				break;
			case 6:
				listStudents();
				break;
			case 7:
				System.out.println("\nGood Bye!");
				toggle = false;
				break;
			default:
				System.out.println("\nInvalid selection Please try again!");
				break;
			}
		}
	}
	
	private void printMenuChoice() {
		System.out.println();
		System.out.println("Please choose one from the following options:  ");
		System.out.println();
		System.out.println("1. Search Catalogue Courses ");
		System.out.println("2. Add Course to Student Courses ");
		System.out.println("3. Remove Course from Student Courses ");
		System.out.println("4. View All Courses in Catalogue ");
		System.out.println("5. View All Student Courses ");
		System.out.println("6. View All Students");
		System.out.println("7. Quit");
		System.out.println();
		System.out.print("Please enter your selection: ");
	}
	
	
	public void searchForCourse(){//1
		System.out.println("\n---------------------------------------\n" 
					+ catalogue.searchCat(inputCourseName(), inputCourseNum()));
	}
	
	public void addCourseToStudent() {//2
		
		String name = inputCourseName();
		int num = inputCourseNum();
		Course course = catalogue.searchCat(name, num);
		if(course != null) {
			int sec = inputSecNum();
			CourseOffering cOff = course.getCourseOfferingAt(sec - 1);
			
			if(cOff != null) {
				int id = inputStudentId();
				studentList.registerStudent(id, cOff);
			}
		}
	}
	
	public void removeCourseFromStudent() {//3
		

		String name = inputCourseName();
		int num = inputCourseNum();
		Course course = catalogue.searchCat(name, num);
		if(course != null) {
			int id = inputStudentId();
			studentList.removeCourseReg(id, course);
		}
	}
	
	public void viewCourseCatalogue() {//4
		System.out.println(catalogue);
	}
	
	public void viewStudentCourse() {//5
		int id = inputStudentId();
		System.out.println(studentList.viewStudentCourse(id));
	}
	
	public void listStudents() {//6
		System.out.println(studentList);
	}
	
	
	
	private String inputCourseName() {
		System.out.print("Please Enter Course Name: ");
		String name = scan.next();
		return name;
	}
	
	private String inputCourse() {
		System.out.print("Please Enter Full Course Name: ");
		String name = scan.next();
		return name;
	}
	
	private int inputCourseNum() {
		System.out.print("Please Enter Course Number: ");
		return scan.nextInt();
	}
	
	private String inputStudentName() {
		System.out.print("Please Enter Student Name: ");
		String name = scan.next();
		return name;
	}
	
	private int inputStudentId() {
		System.out.print("Please Enter Student ID Number: ");
		return scan.nextInt();
	}
	
	private int inputSecNum() {
		System.out.print("Please Enter Section Number: ");
		return scan.nextInt();
	}
	
}
