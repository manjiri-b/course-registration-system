import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class serialization {

	public static void main(String args[]) {
//instantiate an Employee object
		Employee serializedEmloyee = new Employee("Marc", "Benioff", "Larry Elison", 50000000, 123456789);
		Employee de = null;
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Employee.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(serializedEmloyee);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		//Now we will deserialize the same object
		
		 try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream("Employee.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      //Cast as Employee. readObject will take the object from ObjectInputStream
		      de = (Employee)ois.readObject();
		      ois.close();
		      fis.close();
		    }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       return;
		    }
		 catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       return;
		     }
		    System.out.println("Employee Name: "+de.getFirstName() + " "+de.getLastName());
		    System.out.println("Employee Team Leader: "+de.getTeamLeader());
		    System.out.println("Employee Salary:"+de.getSalary());
		    
		    //Notice the SSN difference. It's because SSN is Transient ;
		    System.out.println("Employee SSN:"+de.getSSN());

		 }
	}
	


class Employee implements java.io.Serializable{
	private String firstName;
	private String lastName;
	private String teamLeader;
	private int salary;
	//Transient will ensure the data cannot be serialized
	private transient int SSN;
	
	Employee(){
		/*Empty Constructor*/
	}
	Employee(String f, String l, String tl, int s, int ssn){
		firstName = f;
		lastName = l;
		teamLeader = tl;
		salary = s;
		SSN =ssn;
	}
	//Getters and Setters
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int ssn) {
		this.SSN = ssn;
	}
	
}