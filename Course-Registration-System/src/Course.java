import java.io.*;
import java.util.ArrayList;

public class Course implements java.io.Serializable {
	
	/*Course name, course id, maximum number of students registered in the course, 
	current number of registered students, a list of names of students being registered 
	in the given course, course instructor, course section number, course location.*/
	
	private String courseName;  //Ask what the appropriate modifier would be
	private String courseID;
	private int maxStudents;
	private int currentNoOfStudents;
	private ArrayList<Student> studentList;
	private String courseInstructor;
	private int courseSectionNumber;
	private String courseLocation;
	
	//Creating a static list of arrays as it is shared by all objects of the class
	static ArrayList<Course> allCoursesList = new ArrayList<Course>();
	
	//Creating the constructors
	public Course() {
		
	}
	
	public Course(String courseName, String courseID, int maxStudents, int currentNoOfStudents, 
			String courseInstructor, int courseSectionNumber, String courseLocation) {
		
		this.courseName = courseName;
		this.courseID = courseID;
		this.maxStudents = maxStudents;
		this.currentNoOfStudents = currentNoOfStudents;
		this.courseInstructor = courseInstructor;
		this.courseSectionNumber = courseSectionNumber;
		this.courseLocation = courseLocation;
		
		//Creating array of students
		this.studentList = new ArrayList<Student>();
		
	}
	
	//Create the getters and setters
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getCourseID() {
		return this.courseID;
	}
	
	public int getMaxStudents() {
		return this.maxStudents;
	}
	
	public int getCurrentNoOfStudents() {
		return this.currentNoOfStudents;
	}
	
	public ArrayList<Student> getStudentList() {
		return this.studentList;
	}
	
	public String getCourseInstructor() {
		return this.courseInstructor;
	}
	
	public int getCourseSectionNumber() {
		return courseSectionNumber;
	}
	
	public String getCourseLocation() {
		return this.courseLocation;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	
	public void setCurrentNoOfStudents(int currentNoOfStudents) {
		this.currentNoOfStudents = currentNoOfStudents;
	}
	
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	
	public void setCourseSectionNumber(int courseSectionNumber) {
		this.courseSectionNumber = courseSectionNumber;
	}
	
	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}
	
	//Override to String Method of Object class to Display Course Information
	/*Whenever Course Object is Printed using System.out.print(), it will print the Course information
	 rather than the memory address of the course object*/
	@Override
	public String toString() {
		
		//Names of Students in Class
		String studentNames = "";
		
		//If there are no students registered
		if (this.currentNoOfStudents == 0) {
			
			studentNames = "None";
		}
		
		else {
			
			//Individually extracting the first and last names of students from the array
			for (int i = 0; i < studentList.size(); i++) {
				String studentFirstName = studentList.get(i).getFirstName();
				String studentLastName = studentList.get(i).getLastName();
				
				if (i == 0 || i == studentList.size() - 1) {
					studentNames += studentFirstName + " " + studentLastName + " ";
				}
				else {
					studentNames += studentFirstName + " " + studentLastName + ", ";
				}
			}
		}
		
		String displayText = "Course Name: " + this.courseName + "\n" + "Course ID: " + this.courseID + "\n" + "Maximum Number of Students: "
		+ maxStudents + "\n" + "Current Number of Students: " + this.currentNoOfStudents + "\n" + "Registered Students: "+ 
		studentNames + "\n" + "Course Instructor: " + this.courseInstructor + "\n" + "Course Section Number: " 
		+ this.courseSectionNumber + "\n" + "Location: " + courseLocation + "\n";
		
		//Return course information
        return displayText;
    }
	
	public String studentsInClass() {
		
		String studentNames = "";
		
		//If there are no students registered
		if (this.currentNoOfStudents == 0) {
			
			studentNames = "None";
		}
		
		else {
			
			//Individually extracting the first and last names of students from the array
			for (int i = 0; i < studentList.size(); i++) {
				String studentFirstName = studentList.get(i).getFirstName();
				String studentLastName = studentList.get(i).getLastName();
				
				if (i == 0 || i == studentList.size() - 1) {
					
					if (i == 0 && studentList.size() == 2) {
						studentNames += studentFirstName + " " + studentLastName + ", ";
					}
					else {
						studentNames += studentFirstName + " " + studentLastName + " ";
					}
				}
				else{
					studentNames += studentFirstName + " " + studentLastName + ", ";
				}
			}
		}
		return studentNames;
	}
	
	//Removing student from student list of course
	public void removeStudent(String firstName, String lastName, String username, String password) {
		
		//Removing student from course's student list
		for(int i= 0; i < this.studentList.size(); i++) {
			
			String currentFirstName = studentList.get(i).getFirstName();
			String currentLastName = studentList.get(i).getLastName();
			String currentUsername = studentList.get(i).getUsername();
			String currentPass = studentList.get(i).getPassword();
			
			//Making comparison
			if (firstName.equals(currentFirstName) && lastName.equals(currentLastName) 
					&& username.equals(currentUsername) && password.equals(currentPass)) {
				this.studentList.remove(i);
				this.currentNoOfStudents -= 1;
				break;
			}
			else {
				System.out.println("You were never registered in this course!");
				System.out.println();
			}
		}
		
	}
	


}
