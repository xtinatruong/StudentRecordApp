package lab3_part2;

import java.util.ArrayList;

public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}

	public ArrayList readCourseFromDataBase() {
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("MATH", 249));
		courseList.add(new Course ("MATH", 213));
		courseList.add(new Course ("ENGG", 225));
		courseList.add(new Course ("ENGG", 200));
		courseList.add(new Course ("HTST", 201));
		courseList.add(new Course ("HTST", 200));
		courseList.add(new Course ("MATH", 211));
		courseList.add(new Course ("MATH", 275));
		courseList.add(new Course ("ECON", 201));
		courseList.add(new Course ("ENGG", 201));
		courseList.add(new Course ("CHEM", 201));
		courseList.add(new Course ("ENGG", 202));
		courseList.add(new Course ("CPSC", 231));
		
		addOfferings();
		
		return courseList;
	}
	
	public ArrayList readStudentFromDataBase() {
		studentList.add(new Student("Rohit", 1000));
		studentList.add(new Student("Chakrapani", 1001));
		studentList.add(new Student("Ahmed", 1002));
		studentList.add(new Student("Omar", 1003));
		studentList.add(new Student("Lamarcus", 1004));
		studentList.add(new Student("Giannis", 1005));
		studentList.add(new Student("Abendigubububua", 1006));
		studentList.add(new Student("Lorris", 1007));
		studentList.add(new Student("Vladimir", 1008));
		studentList.add(new Student("Emilio", 1009));
		studentList.add(new Student("Pedro", 1010));
		studentList.add(new Student("Rabindronath", 1011));
		studentList.add(new Student("Kyle", 1012));
		studentList.add(new Student("Ben", 1013));
		studentList.add(new Student("Bert", 1014));
		studentList.add(new Student("Anjali", 1015));
		studentList.add(new Student("Hanna", 1016));
		studentList.add(new Student("Desiree", 1017));
		studentList.add(new Student("Enzo", 1018));
		studentList.add(new Student("Pradeep", 1019));
		studentList.add(new Student("Renuka", 1020));
		studentList.add(new Student("Sofia", 1021));
		studentList.add(new Student("Jared", 1022));
		studentList.add(new Student("Kieran", 1023));
		studentList.add(new Student("Leslie", 1024));
		studentList.add(new Student("Bobby", 1025));
		studentList.add(new Student("Bongo", 1026));
		studentList.add(new Student("Deyu", 1027));
		studentList.add(new Student("Penny", 1028));
		studentList.add(new Student("Nyugn", 1029));
		studentList.add(new Student("Samoan", 1030));
		studentList.add(new Student("Henri", 1031));
		studentList.add(new Student("Ossama", 1032));
		studentList.add(new Student("Niple", 1033));
		studentList.add(new Student("Nandita", 1034));
		studentList.add(new Student("Arpun", 1035));
		studentList.add(new Student("Ethan", 1036));
		studentList.add(new Student("Perry", 1037));
		studentList.add(new Student("Kitty", 1038));
		studentList.add(new Student("Kaahir", 1039));
		studentList.add(new Student("Yassin", 1040));
		studentList.add(new Student("Dilshod", 1041));
		studentList.add(new Student("Shayan", 1042));
		studentList.add(new Student("Aayush", 1043));
		studentList.add(new Student("Tobi", 1044));
		studentList.add(new Student("Carlos", 1045));
		studentList.add(new Student("Arjun", 1046));
		studentList.add(new Student("Jusso", 1047));
		studentList.add(new Student("Fart", 1048));
		studentList.add(new Student("Jin", 1049));
		studentList.add(new Student("Winglu", 1050));
		
		return studentList;
	}
	
	private void addOfferings() {
		int index = 0;
		for(Course i : courseList) {
			
			int numberOfOfferings = setSec(index); 

			for(int sec = 1; sec <= numberOfOfferings; sec++) {
				
				int capacity = setCapacity(index, sec);
				CourseOffering off = new CourseOffering(sec, capacity);
				i.addOffering(off);
			}
			index++;
		}
	}
	
	private int setSec(int index) {
		int numberOfOfferings = 1;
		if(index % 3 == 0)
			numberOfOfferings = 2;
		else if(index % 4 == 0)
			numberOfOfferings = 3;
		else if(index % 5 == 0)
			numberOfOfferings = 4;
		else if(index % 7 == 0)
			numberOfOfferings = 5;
		return numberOfOfferings;
	}
	
	private int setCapacity(int index, int sec) {
		
		int capacity = 100;
		if(index % 3 == 0 && sec == 2)
			capacity = 80;
		else if(index % 3 == 0)
			capacity = 50;
		else if(index % 4 == 0 && sec == 3)
			capacity = 120;
		else if(index % 4 == 0)
			capacity = 200;
		else if(index % 5 == 0 && sec == 2)
			capacity = 150;
		else if(index % 5 == 0 && sec == 1)
			capacity = 180;
		else if(index % 5 == 0 && sec == 4)
			capacity = 30;
		else if(index % 5 == 0)
			capacity = 70;
		else if(index % 7 == 0 && (sec == 1 || sec == 2))
			capacity = 300;
		else if(index % 7 == 0 && (sec == 3 || sec == 4))
			capacity = 250;
		
		return capacity;
	}

	
}
