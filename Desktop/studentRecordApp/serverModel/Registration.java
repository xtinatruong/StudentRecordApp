package serverModel;

/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public class Registration {

	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	public Registration(Student student, CourseOffering offer) {
		completeRegistration(student, offer);
		grade = '-';
	}
	
	public void completeRegistration (Student student, CourseOffering offer) {
		theStudent = student;
		theOffering = offer;
		addRegistration ();
	}
	
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	
	public Student getTheStudent() {
		return theStudent;
	}
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString () {
		
		String st = theOffering.getTheCourse().getCourseName() + " " +theOffering.getTheCourse().getCourseNum();
		st += "\nThe Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade() + "\n";
		return st;
		
	}
}
