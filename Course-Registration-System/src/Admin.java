import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin extends User implements AdminTasks, java.io.Serializable{
	
	//Admin Constructor
	public Admin() {
		setUsername("Admin");
		setPassword("Admin001");
	}
	
	@Override
	public void addCourse() throws IOException{
		
		//Ask user for course details
		BufferedReader courseInfo = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the course name: ");
		String courseName = courseInfo.readLine();
		
		System.out.print("Enter the course ID: ");
		String courseId = courseInfo.readLine();
		
		System.out.print("Enter the maximum number of students that can register: ");
		int maxStudents = Integer.parseInt(courseInfo.readLine());
		
		System.out.print("Enter the current number of students registered in the class: ");
		int currentNoOfStudents = Integer.parseInt(courseInfo.readLine());

		System.out.print("Enter the course instructor: ");
		String instructorName = courseInfo.readLine();
		
		System.out.print("Enter the course section number: ");
		int courseSectionNumber = Integer.parseInt(courseInfo.readLine());

		System.out.print("Enter the course location: ");
		String courseLocation = courseInfo.readLine();
		
		//Creating new course out of information given
		
		Course newCourse = new Course(courseName, courseId, maxStudents, currentNoOfStudents,
				instructorName, courseSectionNumber, courseLocation);
		
		//Check if course is already in the course list
		//If no append to list, if yes, then dont append to list
		
		//Appending this course to Course Array List
		Course.allCoursesList.add(newCourse);
		
		//Informing user that the course has been added
		System.out.println(courseName + " has been added to the list of courses.");
		
	}
	
	@Override
	public void deleteCourse() throws IOException {
		
		//Ask user which course they want to delete
		BufferedReader courseInfo = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the course name you want to delete: ");
		String courseNameDelete = courseInfo.readLine();
		
		System.out.print("Enter the course section number you want to delete: ");
		int courseSection = Integer.parseInt(courseInfo.readLine());
		
		//Looking for this course name in the array list allCoursesList
		
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			
			//Get the coursename of each object
			String currentCourseName = Course.allCoursesList.get(i).getCourseName();
			int currentCourseSection = Course.allCoursesList.get(i).getCourseSectionNumber();
			
			//Compare course name
			if (currentCourseName.equals(courseNameDelete) && currentCourseSection == courseSection) {
				
				//Removing course from course list
				Course.allCoursesList.remove(i);
				
				System.out.println(courseNameDelete + " has been successfully removed!");
				break;
			} 
			else if (i == Course.allCoursesList.size()-1) {
				System.out.println("That course does not exist in the list!");
			}
		}
	}
	
	//Admin is allowed to change everything except course ID and course Name and is only allowed cannot
	//change the current number of students directly unless they register a student through its own method.
	
	@Override
	public void editCourse() throws IOException {
		
		int indexOfCourse = 0;
		boolean found = false;
		
		//Give menu of what all you need to change
		System.out.println("---Editing Options---");
		System.out.println("1. Course Instructor \n2. Course Location \n3. Maximum Number of Students"
				+ "\n4. Course Section Number");
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		//Ask which course you would like to edit
		System.out.print("Enter the name of the course you would like to edit: ");
		String courseName = input.readLine();
		
		System.out.print("Enter the number of the information you want to edit: ");
		int option = Integer.parseInt(input.readLine());
		
		//Get the index of the Course Object of desired course name
		
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			
			//Check the coursename of each object
			String currentCourseName = Course.allCoursesList.get(i).getCourseName();
			
			if (currentCourseName.equals(courseName)) {
				indexOfCourse = i;
				found = true;
				break;
			} 
		}
		
		if (found == true) {
			if (option == 1) { //Changing Course Instructor
				System.out.print("Enter the new course instructor: ");
				String newCourseInstructor = input.readLine();
				
				//Changing course instructor of object using setter
				Course.allCoursesList.get(indexOfCourse).setCourseInstructor(newCourseInstructor);
			}
			else if (option == 2) { //Changing Course Location
				System.out.print("Enter the new course location: ");
				String newCourseLocation = input.readLine();
				
				//Changing course location
				Course.allCoursesList.get(indexOfCourse).setCourseLocation(newCourseLocation);
			}
			else if (option == 3) { //Max Num of Students
				System.out.print("Enter the new maximum number of students: ");
				int newMaxStudents = Integer.parseInt(input.readLine());
				
				//Changing max students location
				Course.allCoursesList.get(indexOfCourse).setMaxStudents(newMaxStudents);
			}
			else if (option == 4) { //Course Section Number
				System.out.print("Enter the new course section number: ");
				int newSectionNumber = Integer.parseInt(input.readLine());
				
				//Changing course section number
				Course.allCoursesList.get(indexOfCourse).setCourseSectionNumber(newSectionNumber);
			}
		}
		else {
			System.out.println("The course you want to edit does not exist in the course list.");
		}
		System.out.println();
	}
	
	//Display information for a given course (by course ID)
	@Override
	public void displayCourse() throws IOException{
		//Ask user for course ID
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		//Ask which course you would like to edit
		System.out.println("Enter the course ID of the course you want to display the information of: ");
		String courseID = input.readLine();
		
		//Look up for the course ID in the course list and get the index of it using for loop
		
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			
			//Check the coursename of each object
			String currentCourseID = Course.allCoursesList.get(i).getCourseID();
			
			if (currentCourseID.equals(courseID)) {
				System.out.println(Course.allCoursesList.get(i)); //Overriden toString method will print out info
				break;
			} 
			else if (i == Course.allCoursesList.size() - 1) {
				System.out.println("Course of course ID " + courseID + " was not found.");
			}
		}
	}
	
	//Registering Student
	@Override
	public void registerStudent() throws IOException {
		
		//Ask admin for first name, last name, username, password of Student
		BufferedReader studentInfo = new BufferedReader(new InputStreamReader(System.in));
				
		System.out.print("Enter the student's first name: ");
		String stuFirstName = studentInfo.readLine();
				
		System.out.print("Enter the student's last name: ");
		String stuLastName = studentInfo.readLine();

		System.out.print("Enter the student's username: ");
		String stuUsername = studentInfo.readLine();

		System.out.print("Enter the student's password: ");
		String stuPassword = studentInfo.readLine();
				
		//Instantiate a new student out of information given

		Student newStudent = new Student(stuUsername, stuPassword, stuFirstName, stuLastName);

		//Check if student is already in the student list
		//If no append to list, if yes, then dont append to list

		//Appending new student to All Student Array List
		Student.allStudentsList.add(newStudent);                 //CHECKKKKKKKKK THISSSSSSSSSSS

		//Informing the admin that the student has been registered
		System.out.println(stuFirstName + " "+ stuLastName + " has been registered.");
		System.out.println();
		System.out.println(Student.allStudentsList.size());
	}
	
	//View all courses (Check if this throws what an IO Exception is or not) //DELETE
	@Override
	public void viewAllCourses() {
		
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			System.out.println((i+1)+". "+(Course.allCoursesList.get(i))); //Overriden toString method will print out info
		}
	}
	
	//View all courses that are FULL overriding view courses method of user class
	@Override
	public ArrayList<Course> viewFullCourses() {
		
		//Creating string that will hold full classes
		ArrayList<Course> fullCoursesList = new ArrayList<Course>();
		
		//Use a for loop to check for which Course objects currentNumStudents == maxNumStudents
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			
			int currentStud = Course.allCoursesList.get(i).getCurrentNoOfStudents();
			int maxStud = Course.allCoursesList.get(i).getMaxStudents();
			
			if (currentStud == maxStud) {
				fullCoursesList.add(Course.allCoursesList.get(i));
			}
		}
		
		//Print these courses out
		viewCourses(1);
		
		return fullCoursesList;
		
	}
	
	//Write to a file the list of course that are Full
	@Override
	public void createFileofFullCourses() throws IOException{
		String fileName = "fullCourses.txt";
		//Scanner scanner = new Scanner(System.in);

		try {
			
			//Creating a buffer
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			//Getting the array list of ffull courses
			ArrayList<Course> fullCoursesList = viewFullCourses();
			for (int i = 0; i < fullCoursesList.size(); i++) {
				String courseInfo = fullCoursesList.get(i).toString();
				bufferedWriter.write(courseInfo);
				bufferedWriter.newLine();
			}
			// Closing the writer
			bufferedWriter.close();
		}
		// Always close files

		catch (IOException error) {
			System.out.println("Error writing file '" + fileName + "'");
			error.printStackTrace();
		}
	}
	
	//View the names of the students being registered in a specific course
	@Override
	public void displayAllStudentsInCourse() throws IOException {
		
		int indexOfCourse = 0;
		boolean found = false;
		
		//Asking admin for name of course they want to view the registered students of
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the name of the course of which you want to view the registered students: ");
		String courseName = input.readLine();
		
		//Searching the courseName in AllCourses List, getting the array list off students in that course
		for (int i = 0; i < Course.allCoursesList.size(); i++) {

			//Check the coursename of each object
			String currentCourseName = Course.allCoursesList.get(i).getCourseName();

			if (currentCourseName.equals(courseName)) {
				indexOfCourse = i;
				found = true;
				break;
			} 
		}
		
		//Only if the course is found print out students in class using method defined in Course class
		if (found == true) {
			System.out.println(Course.allCoursesList.get(indexOfCourse).studentsInClass());
			System.out.println();
		}
		else {
			System.out.println("Error! "+courseName+" does not exist.");
		}
	
	}
	
	//View the list of courses that a given student is being registered in
	@Override
	public void displayAStudentsCourses() throws IOException {
		
		int indexOfStudent = 0;
		boolean found = false;
		
		//Asking admin for name of course they want to view the registered students of
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the first name of the student whose courses you want to view: ");
		String firstName = input.readLine();
		
		System.out.print("Enter the last name of the student whose courses you want to view: ");
		String lastName = input.readLine();
		
		//Searching the courseName in AllCourses List, getting the array list off students in that course
		for (int i = 0; i < Student.allStudentsList.size(); i++) {

			//Check the coursename of each object
			String currentStudentFirstName = Student.allStudentsList.get(i).getFirstName();
			String currentStudentLastName = Student.allStudentsList.get(i).getLastName();

			if (currentStudentFirstName.equals(firstName) && currentStudentLastName.equals(lastName)) {
				indexOfStudent = i;
				found = true;
				break;
			} 
		}
		
		//Only if the course is found print out students in class using method defined in Course class
		if (found == true) {
			Student.allStudentsList.get(indexOfStudent).viewAllRegisteredCourses();
		}
		else {
			System.out.println("Error! "+firstName+ " "+ lastName+ " does not exist.");
		}
	}
	
	//Sort courses based on the current number of student registers (sorts in ascending order)
	//Sorts in place
	@Override
	public void sortCourses() {
		
		//Searching from start to end
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			//Searching from end to start
			for (int j = Course.allCoursesList.size() - 1; j > i; j--) {
				
				//If the number on the right is smaller than number on the right, swap the two elements
				if (Course.allCoursesList.get(j).getCurrentNoOfStudents() < Course.allCoursesList.get(i).getCurrentNoOfStudents()) {
		
					//Creating a place holder for bigger number
					Course temporary = Course.allCoursesList.get(i);
					//Swapping
					Course.allCoursesList.set(i, Course.allCoursesList.get(j));
					Course.allCoursesList.set(j, temporary);
				}

			}

		}
		
		//Printing out the courses
		for (int i = 0; i < Course.allCoursesList.size(); i++) {
			// prints out the courses within the course list
			System.out.println((i+1)+". "+(Course.allCoursesList.get(i)));
		}
		System.out.println();
	}
	
	//Admin Course Manageme menu function
	public int AdminMenu() throws IOException{
		
		System.out.println("Courses Management");
		System.out.println("1.  Create a new course");
		System.out.println("2.  Delete a course");
		System.out.println("3.  Edit a course");
		System.out.println("4.  Display information");
		System.out.println("5.  Register a student \n");
		
		System.out.println("Reports");
		System.out.println("6.  View all courses");
		System.out.println("7.  View all courses that are full");
		System.out.println("8.  Write to a file the list of course that are full");
		System.out.println("9.  View the names of the students being registered in a specific course");
		System.out.println("10. View the list of courses that a given student is registered in");
		System.out.println("11. Sort courses based on the current number of students registered");
		System.out.println("12. Exit");
		System.out.println();
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("What would you like to do? Enter the number: ");
		int option = Integer.parseInt(input.readLine());
		
		return option;
	}

}
