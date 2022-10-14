import java.io.*;
import java.util.ArrayList;

public class Student extends User implements StudentTasks, java.io.Serializable {
	
	private ArrayList<Course> courseList = new ArrayList<Course>(); //Holds list of courses each student takes
	
	//Creating a static list of arrays as it is shared by all objects of the class
	//stores list of students
	static ArrayList<Student> allStudentsList = new ArrayList<Student>();
	
	public Student() {
		
	}
	
	public Student(String stuUsername, String stuPassword, String stuFirstName, String stuLastName) {
		super(stuUsername,stuPassword,stuFirstName,stuLastName);
	}
	
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	//Getter and setter for courselist
	public ArrayList<Course> getCourseList(){
		return courseList;
	}
	
	public void setCourseList(ArrayList<Course> arr) {
		this.courseList = arr;
	}
	
	//View all courses
	@Override
	public void viewAllCourses() {
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			System.out.println((i+1)+". "+(Course.allCoursesList.get(i))); //Overriden toString method will print out info
		}
	}
	
	//View all courses that are not FULL
	@Override
	public void viewAvailableCourses() {
		viewCourses(2);
	}

	//Register in a course
	@Override
	public void registerInACourse() throws IOException{
		//Ask user which course they want to register in
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter the course name you want to register in: ");
		String courseNameRegister = input.readLine();
		System.out.print("Enter the course section number you want to register in: ");
		int courseSection = Integer.parseInt(input.readLine());
		
		/*
		System.out.println("Enter your first name: ");
		String firstName = input.readLine();
		System.out.println("Enter your last name: ");
		String lastName = input.readLine();
		*/
		
		String firstName = this.getFirstName();
		String lastName = this.getLastName();
		String username = this.getUsername();
		String password = this.getPassword();
		
		//Creating a new Student object 
		Student newStudent = new Student(username,password,firstName, lastName);
		
		for (int i = 0; i < Course.allCoursesList.size(); i++) {

			//Get the coursename of each object
			String currentCourseName = Course.allCoursesList.get(i).getCourseName();

			//Check if the 
			if (currentCourseName.equals(courseNameRegister) && Course.allCoursesList.get(i).getMaxStudents() != Course.allCoursesList.get(i).getCurrentNoOfStudents()
					&& courseSection == Course.allCoursesList.get(i).getCourseSectionNumber()) {

				//Adding student to student list of course
				Course.allCoursesList.get(i).getStudentList().add(newStudent);
				
				//Increasing current number of students
				int noOfStudents = Course.allCoursesList.get(i).getCurrentNoOfStudents();
				
				Course.allCoursesList.get(i).setCurrentNoOfStudents(noOfStudents+1);
				
				//Adding course to course list of student
				this.courseList.add(Course.allCoursesList.get(i));

				System.out.println("You have successfully been registered!");
				System.out.println();
				break;
			} 
			else if (i == Course.allCoursesList.size()-1) {
				System.out.println("Error! That course does not exist.");
				System.out.println();
			}
			else if (Course.allCoursesList.get(i).getMaxStudents() == Course.allCoursesList.get(i).getCurrentNoOfStudents()) {
				System.out.println("Sorry the class is full.");
				System.out.println();
			}
		}
	}

	//Withdraw from a course
	//1. Removes student from course objects student list and reduces current students in class
	//2. Removes course from student's course list
	@Override
	public void withdrawFromACourse() throws IOException{
		//Ask user which course they want to withdraw from
		BufferedReader courseInfo = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter the course name you want to withdraw from: ");
		String courseNameWithdraw = courseInfo.readLine();
		System.out.print("Enter the course section number you want to withdraw from: ");
		int courseSection = Integer.parseInt(courseInfo.readLine());
		
		/*
		System.out.print("Enter your first name: ");
		String firstName = courseInfo.readLine();
		System.out.print("Enter your last name: ");
		String lastName = courseInfo.readLine();
		*/
		
		String firstName = this.getFirstName();
		String lastName = this.getLastName();
		String username = this.getUsername();
		String password = this.getPassword();

		//1.
		
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			if (Course.allCoursesList.get(i).getCourseName().equals(courseNameWithdraw) 
					&& courseSection == Course.allCoursesList.get(i).getCourseSectionNumber()) {
				Course.allCoursesList.get(i).removeStudent(firstName, lastName, username, password);
			}
		}
		
		//2.

		for (int i = 0; i < this.courseList.size(); i++) {

			//Get the coursename of each object
			String currentCourseName = this.courseList.get(i).getCourseName();

			//Check if the coursename matches any of the courses in courselist
			if (currentCourseName.equals(courseNameWithdraw)) {
				this.courseList.remove(i);				
				System.out.println("You have been withdrawn from "+ courseNameWithdraw);
				System.out.println();
				break;
			} 
			else if (i == Course.allCoursesList.size()-1) {
				System.out.println("Error! You were never registered in this course.");
				System.out.println();
			}
		}
	}
	
	//Method overloading (method that just removes course from student's courselist)
	public void withdrawFromACourse(String courseNameWithdraw) throws IOException{
		
		for (int i = 0; i < this.courseList.size(); i++) {

			//Get the coursename of each object
			String currentCourseName = this.courseList.get(i).getCourseName();

			//Check if the coursename matches any of the courses in courselist
			if (currentCourseName.equals(courseNameWithdraw)) {
				this.courseList.remove(i);				
				break;
			}
		}
	}

	//View all courses that the current student is being registered in
	@Override
	public void viewAllRegisteredCourses() {
		
		String studentsCourses = "";
		
		if (!this.courseList.isEmpty()) {
			for (int i = 0; i < this.courseList.size(); i++) {

				//Get name of registered course
				String courseName = this.courseList.get(i).getCourseName();
				
				//Append name of courses to string
				if (i == 0 && this.courseList.size() >= 2) {
					studentsCourses += courseName + ", ";
				}
				else if (i == 0 || i == this.courseList.size()-1) {
					studentsCourses += courseName + " ";
				}
				else {
					studentsCourses += courseName + ", ";
				}

			}
		}
		else {
			studentsCourses = "Student is not registered in any courses.";
		}
		System.out.println(studentsCourses);
		System.out.println();
	}
	
	//Admin menu function
	public int StudentMenu() throws IOException{

		System.out.println("Reports");
		System.out.println("1.  View all courses");
		System.out.println("2.  View all courses that are not full");
		System.out.println("3.  Register in a course");
		System.out.println("4.  Withdraw from a course");
		System.out.println("5.  View all the courses you are registered in");
		System.out.println("6.  Exit");
		System.out.println();

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("What would you like to do? Enter the number: ");
		int option = Integer.parseInt(input.readLine());

		return option;
	}

}
