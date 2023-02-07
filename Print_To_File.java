import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Print_To_File {
	
    public Print_To_File(String s, String fileName) {
    	filePrinter(s, fileName);
    }
    
	public static void filePrinter(String s, String fileName) {
		try {
			PrintWriter printWriter = new PrintWriter(fileName);
    		printWriter.println(s);
    		printWriter.close();
    		
		} catch (IOException e) {
			System.out.println("An error occurred while writing to file");
		}
	}
	
	/**
	 * This will output everything into a .txt File (not appending)
	 * @param s
	 */
	public static void filePrinter(String s) {
		try {
			FileWriter writer = new FileWriter("output.txt", true);
			writer.write(s);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("An error occurred while writing to file");
			
		}
	}
	
	public static void filePrinterAppend(String s) {
		try {
			FileWriter writer = new FileWriter("output.txt", true);
			writer.write(s);
			writer.close();
			
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
