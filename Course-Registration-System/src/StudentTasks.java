import java.io.IOException;

public interface StudentTasks {
	
	//View all courses
	public void viewAllCourses();
	
	//View all courses that are not FULL
	public void viewAvailableCourses();
	
	//Register in a course
	public void registerInACourse() throws IOException;
	
	//Withdraw from a course
	public void withdrawFromACourse() throws IOException;
	
	//View all courses that the current student is being registered in
	public void viewAllRegisteredCourses();

}
