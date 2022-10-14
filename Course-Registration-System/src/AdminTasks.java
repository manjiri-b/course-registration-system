import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public interface AdminTasks {
		
		//Adds New Course to Course List
		public void addCourse() throws IOException;
		
		//Deletes Course from Course List
		public void deleteCourse() throws IOException;
		
		//Edit Course method
		public void editCourse() throws IOException;
		
		//Displaying information for Course using CourseID
		public void displayCourse() throws IOException;
		
		//Registering Student
		public void registerStudent() throws IOException;
		
		//View all courses
		public void viewAllCourses();
		
		//View all courses that are FULL
		public ArrayList<Course> viewFullCourses();
		
		//Write to a file the list of course that are Full
		public void createFileofFullCourses() throws IOException;
		
		//View the names of the students being registered in a specific course
		public void displayAllStudentsInCourse() throws IOException;
		
		//View the list of courses that a given student is being registered in
		public void displayAStudentsCourses() throws IOException;
		
		//Sort courses based on the current number of students registered
		public void sortCourses();
		
}