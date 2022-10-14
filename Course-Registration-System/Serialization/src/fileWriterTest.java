import java.io.*;
import java.util.Scanner;
public class fileWriterTest {

	public static void main(String[] args) {
		String fileName = "text.txt";
		Scanner scan = new Scanner(System.in);
		
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String text = scan.nextLine();
			bufferedWriter.write(text);

			bufferedWriter.write(".write or .append will also write to the file");
			bufferedWriter.newLine();

			
//Always close writer
			bufferedWriter.close();
		}

		//Always close files

		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
}
//