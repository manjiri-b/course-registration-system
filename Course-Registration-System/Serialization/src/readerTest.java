import java.io.*;

public class readerTest {

	public static void main(String[] args) {
	
		String fileName = "text.txt";
		//References one line at a time
		String line = null;
		try{
			//FileReader reads text files as characters as opposed to bytes (like FileInputStream)
			//First, we instantiate the file reader class
			//It's parameter would be the name of the file to read (in this case, the string variable which represents the file name);
			
			FileReader fileReader = new FileReader(fileName);
			
			//The BufferedReader class can wrap around Readers, like FileReader, to buffer the input and improve efficiency.
			//ALWAYS wrap FileReader in BufferedReader
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//readLine() reads a line of text.
			//A line is considered to be terminated by a new line ('\n'). So Buffered reader would literally read line by line.
			//While there are still lines to read, our program will print the file
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

			//Always close files
			bufferedReader.close();
		}
		//The catch block performs a specific action depending on the exception
		catch(FileNotFoundException ex){
			System.out.println( "Unable to open file '" + fileName + "'");
			//the printStackTrace method will print out an error output stream ("What went wrong?" report);
			
			ex.printStackTrace();
		}

		catch (IOException ex) {
			System.out.println( "Error reading file '" + fileName + "'");
			ex.printStackTrace();
		}
	}

}
//