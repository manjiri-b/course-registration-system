
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;

public class Tokenizer {
	public static void main(String[]args) throws FileNotFoundException{
		
		//Array list of type person
		ArrayList<person> list = new ArrayList<person>();
		
		
		//This is the file name
		String file = "IDLIST";
		
		//input will repreent the file as a string so that we may manipulate the file 
		String input = new Scanner(new File(file)).useDelimiter("\\A").next();
		
		//We remove any information we don't neccessarily need in order to get the neccessary tokens
		input=input.replace("NAME:", "\n").replace("ID:", "\n");
		//The tokenizer will look at each string token within the input
		StringTokenizer strTokens = new StringTokenizer(input);
		
	
		
		//While there is still text to parse through within the file
		while (strTokens.hasMoreTokens()){
			
			
			
			String first =  strTokens.nextToken();
			String last =  strTokens.nextToken();
			int id =  Integer.parseInt(strTokens.nextToken());
			//creates a person from the elements found
			person p = new person(first,last,id);
			list.add(p);
		}
		for(int i=0;i<list.size();i++){
			//prints out the people within the list
		list.get(i).print();
		}
	}

}

class person{
	private String firstName;
	private String lastName;
	private int ID;
	person(){
		//this is an empty constructor
	}
	public person(String fn, String ln, int id){
		this.firstName=fn;
		this.lastName=ln;
		this.ID=id;
	}
	public void print(){
		System.out.println("Name: " + firstName + " "+lastName);
		System.out.println("ID: " + ID + "\n");
	}
}
