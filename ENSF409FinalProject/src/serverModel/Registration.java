package serverModel;

/**
 * This class containg information about the registration of a student in their course.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public class Registration {

	/**
	 * Student being registered
	 */
	private Student theStudent;
	
	/**
	 * CourseOffering registered with student
	 */
	private CourseOffering theOffering;
	
	/**
	 * Grade of student in their course offering
	 */
	private char grade;
	
	/**
	 * Completes registration of a student calling method completeRegistration
	 * @param student Student
	 * @param offer CourseOffering
	 */
	public Registration(Student student, CourseOffering offer) {
		completeRegistration(student, offer);
		grade = '-';
	}
	
	/**
	 * Registers the student in specific offering
	 * @param student Student being registered
	 * @param offer Course offering student is being registered in
	 */
	public void completeRegistration (Student student, CourseOffering offer) {
		theStudent = student;
		theOffering = offer;
		addRegistration ();
	}
	
	/**
	 * Adds this registration to the student and offering
	 */
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	/**
	 * Returns the Student
	 * @return theStudent
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	
	/**
	 * Sets the student to given argument
	 * @param theStudent to be set
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	
	/**
	 * Returns the course offering
	 * @return theOffering
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/**
	 * Sets the offering to given argument
	 * @param theOffering to be set
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	/**
	 * Returns the grade
	 * @return grade
	 */
	public char getGrade() {
		return grade;
	}
	
	/**
	 * Sets the grade to a given argument
	 * @param grade to be set
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/**
	 * Returns a String with information about the offering, student and their grade
	 */
	@Override
	public String toString () {
		
		String st = theOffering.getTheCourse().getCourseName() + " " +theOffering.getTheCourse().getCourseNum();
		st += "\nThe Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade() + "\n";
		return st;
		
	}
}
