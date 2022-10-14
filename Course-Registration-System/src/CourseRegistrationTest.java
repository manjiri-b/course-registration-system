import java.io.*;
import java.util.ArrayList;

public class CourseRegistrationTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		//Create Admin
		Admin admin1 = new Admin();
		File fileName1 = new File("CourseData.ser");
		File fileName2 = new File("StudentData.ser");
		
		//If neither of the two files exist read from University file to populate relevant data fields 
		if (!fileName1.exists() && !fileName2.exists()) {
			String fileName = "MyUniversityCourses.csv";
			String line = null;
	
			try {
				FileReader fileReader = new FileReader(fileName);

				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				String headerLine = bufferedReader.readLine();
						
				while((line = bufferedReader.readLine()) != null) {
					
					String [] data = line.split(",");
					
					String courseName = data[0]; 
					String courseID = data[1]; 
					int maxStudents = Integer.parseInt(data[2]); 
					int currentStudents = Integer.parseInt(data[3]);
					String courseInstructor = data[5];
					int courseSectionNumber = Integer.parseInt(data[6]);
					String courseLocation = data[7];
					
					// creates a course list from the elements found
					Course individualCourse = new Course(courseName, courseID, maxStudents, currentStudents, 
							courseInstructor, courseSectionNumber, courseLocation);
					Course.allCoursesList.add(individualCourse);
					
				}
				bufferedReader.close();
			}
			catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + fileName + "'");
				ex.printStackTrace();
			}

			catch (IOException ex) {
				System.out.println("Error reading file '" + fileName + "'");
				ex.printStackTrace();
			}
		}
		//If the files exist deserialize it and use it to populate the data fields
		else {
			//Deserialization 
			deserialize();
		}
		
		boolean exit = false;
		
		while (exit == false) {
			//Displaying the main menu page
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("---Welcome to the Course Registration System!---");
			System.out.println("Enter 1 if you are a Admin");
			System.out.println("Enter 2 if you are a Student");
			System.out.println("Enter 3 to Exit");
			System.out.print("You entered: ");
			
			int option = Integer.parseInt(userInput.readLine());
			
			//Validation
			while (option != 1 && option != 2 && option != 3) {
				System.out.println("Enter 1 if you are a Admin");
				System.out.println("Enter 2 if you are a Student");
				System.out.println("Enter 3 to Exit");
				System.out.print("You entered: ");
				option = Integer.parseInt(userInput.readLine());
			}
			
			System.out.println();
			
			//Admin Login
			if (option == 1) {
				System.out.println("---Admin Login Page---");
				System.out.print("Enter username: ");
				String usernameInput = userInput.readLine();
				System.out.print("Enter password: ");
				String passwordInput = userInput.readLine();
				System.out.println();
				
				//Validating username and password (try making them go back to login page
				while (!usernameInput.equals("Admin") || !passwordInput.equals("Admin001")) {
	
					if (!usernameInput.equals("Admin")) {
						System.out.println("Username entered is invalid. Try again!");
						System.out.print("Enter username: ");
						usernameInput = userInput.readLine();
						System.out.print("Enter password: ");
						passwordInput = userInput.readLine();
						System.out.println();
					} 
					else if (!passwordInput.equals("Admin001")) {
						System.out.println("Username entered is invalid. Try again!");
						System.out.print("Enter username: ");
						usernameInput = userInput.readLine();
						System.out.print("Enter password: ");
						passwordInput = userInput.readLine();
						System.out.println();
					}
				}
				
				//Displaying the admin menu 
				boolean moreChanges = true; //Condition for while loop to run
				
				while (moreChanges == true) {
					int adminChooses = admin1.AdminMenu();

					//Creating new course
					if (adminChooses == 1) {
						admin1.addCourse();
					}
					
					//Delete course
					else if (adminChooses == 2) {
						admin1.deleteCourse();
					}

					//Edit course
					else if (adminChooses == 3) {
						admin1.editCourse();
					}

					//Display Info for a Specific Course using Course ID
					else if (adminChooses == 4) {
						admin1.displayCourse();
					}

					//Register student
					else if (adminChooses == 5) {
						admin1.registerStudent();
					}

					//View all courses
					else if (adminChooses == 6) {
						admin1.viewAllCourses();
					}

					//View full courses
					else if (adminChooses == 7) {
						admin1.viewFullCourses();
					}

					//Write to a file the list of course that are full
					else if (adminChooses == 8) {
						admin1.createFileofFullCourses();
					}

					//display students in a course
					else if (adminChooses == 9) {
						admin1.displayAllStudentsInCourse();
					}

					//display a student's course
					else if (adminChooses == 10) {
						admin1.displayAStudentsCourses();;
					}
					
					//sort courses
					else if (adminChooses == 11) {
						admin1.sortCourses();
					}
					
					//admin exit
					else if (adminChooses == 12) {
						moreChanges = false;
						System.out.println("Thank you! You are logged out!");
						System.out.println();
					}	
				}
			}
			
			//Student Login
			else if (option == 2) {
				
				//Asking the user for their full name
				System.out.print("Please enter your first name: ");
				String firstName = userInput.readLine();
				System.out.print("Please enter your last name: ");
				String lastName = userInput.readLine();
				System.out.println();
				
				//Checking if the student exists
				if (Student.allStudentsList.size() > 0) {
					for (int i = 0; i < Student.allStudentsList.size(); i++) {
						if (!(Student.allStudentsList.get(i).getFirstName().equals(firstName)) && !(Student.allStudentsList.get(i).getLastName().equals(lastName)) 
								&& i == Student.allStudentsList.size() - 1) {
							System.out.println("Error! You are not in the student list. Ask the admin to register you!");
							System.out.println("Error! Admin will give you your username and password!");
							System.exit(0);
						} 
						else {
							break;
						}
					}
				}
				else {
					System.out.println("Error! You are not in the student list. Ask the admin to register you!");
					System.out.println("Error! Admin will give you your username and password!");
					System.exit(0);
				}
				
				/*
				//Allowing the students to create their own desired username and password or not
				if ((student1.getUsername() == null) || (student1.getPassword() == null)) {
					System.out.println("Please create a username and password!");
					System.out.println("Enter your desired username:");
					String studUsername = userInput.readLine();
					System.out.println("Enter your desired password:");
					String studPassword = userInput.readLine();
					
					for (int i = 0; i < Student.allStudentsList.size(); i++) {
						if ((Student.allStudentsList.get(i).getFirstName().equals(firstName))
								&& (Student.allStudentsList.get(i).getLastName().equals(lastName))) {
							Student.allStudentsList.get(i).setUsername(studUsername);
							Student.allStudentsList.get(i).setPassword(studPassword);
							System.out.println("Successful creation of username and password!");
						}
					}
				}
				*/
				
				//Directing them to the login page
				/*
				System.out.println("Student Login Page");
				System.out.println("Enter your Student username:");
				String studentUsername = userInput.readLine();
				System.out.println("Enter your Student password:");
				String studentPassword = userInput.readLine();
				*/
				
				System.out.println();

				//Validation
				boolean found = false;
				while (found == false) {

					System.out.println("---Student Login Page---");
					System.out.println("Enter your username:");
					String studentUsername = userInput.readLine();
					System.out.println("Enter your password:");
					String studentPassword = userInput.readLine();
					
					//Finding student in student list					
					for (int i = 0; i < Student.allStudentsList.size(); i++) {
						Student StudentObject = Student.allStudentsList.get(i);
												
						if(StudentObject.getUsername().equals(studentUsername) && StudentObject.getPassword().equals(studentPassword)
								&& StudentObject.getFirstName().equals(firstName) && StudentObject.getLastName().equals(lastName)) {
							
							found = true;
							System.out.println();
							System.out.println("Welcome " + firstName + " " + lastName + "!");
							System.out.println();

							boolean cont = true;

							while(cont == true) {

								int studentOption = StudentObject.StudentMenu();

								//View All Courses
								if (studentOption == 1) {
									StudentObject.viewAllCourses();
								} 

								//View Available Courses
								else if (studentOption == 2) {
									StudentObject.viewAvailableCourses();
								} 

								//Register in a course
								else if (studentOption == 3) {
									StudentObject.registerInACourse();
								} 

								//Withdraw from a course
								else if (studentOption == 4) {
									StudentObject.withdrawFromACourse();
								} 

								//Viewing own courses
								else if (studentOption == 5) {
									StudentObject.viewAllRegisteredCourses();
								} 
								else if (studentOption == 6){
									cont = false;
									System.out.println("Thank you! You are logged out!");
									System.out.println();
								}
							}
						} 
						else if (found == false){
							continue;
						}
					}
				}
			}
			//When everyone is logged out, serialize the data
			else if (option == 3) {
				exit = true;
				serialize();
				System.out.println("---End of Course Registration System---");
			}
		}
				
	} 		
	
	//Serialization
	public static void serialize() {
		try {
			FileOutputStream fos1 = new FileOutputStream("CourseData.ser");
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

			oos1.writeObject(Course.allCoursesList);
			oos1.close();
			fos1.close();
			
			FileOutputStream fos2 = new FileOutputStream("StudentData.ser");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

			oos2.writeObject(Student.allStudentsList);
			oos2.close();
			fos2.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	//Deserialization
	public static void deserialize() {
		try {
			FileInputStream fis1 = new FileInputStream("CourseData.ser");
			ObjectInputStream ois1 = new ObjectInputStream(fis1);

			Course.allCoursesList = (ArrayList<Course>) ois1.readObject();
			ois1.close();
			fis1.close();
			
			FileInputStream fis2 = new FileInputStream("StudentData.ser");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);

			Student.allStudentsList = (ArrayList<Student>) ois2.readObject();
			ois2.close();
			fis2.close();
	
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
	}
}
