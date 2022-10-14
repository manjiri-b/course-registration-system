import java.io.*;

//Making the parent class User
public class User implements java.io.Serializable{
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public User() {

	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//Getters and setters for the variables
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void viewCourses(int n) {

		String fullClasses = "";
		String availableClasses = "";
		String allClasses = "";

		//Use a for loop to check for which Course objects currentNumStudents == maxNumStudents
		for (int i = 0; i < Course.allCoursesList.size(); i++) {

			int currentStud = Course.allCoursesList.get(i).getCurrentNoOfStudents();
			int maxStud = Course.allCoursesList.get(i).getMaxStudents();

			if (currentStud == maxStud) {
				fullClasses += Course.allCoursesList.get(i).toString()+ "\n";
			}

			else if (currentStud != maxStud) {
				availableClasses += Course.allCoursesList.get(i).toString() + "\n";
			}

			allClasses += Course.allCoursesList.get(i).toString() + "\n";

			if (n == 0) { //View all courses
				System.out.println(allClasses);
			}
			else if (n == 1) { //View full classes
				System.out.println(fullClasses);
			}
			else if (n == 2) { //View available classes
				System.out.println(availableClasses);
			}
		}
	}
}
