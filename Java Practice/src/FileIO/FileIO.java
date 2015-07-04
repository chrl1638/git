package FileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Sum integers from a file with one integer per line
 */
public class FileIO {

	public static void main(String[] args) {
		System.out.println("The sum is " + sum("datafileforFileIO.txt"));
	}
	
	private static int sum(String fName) {
		int sm = 0;
		String s;
		try {
			BufferedReader isr = new BufferedReader(new FileReader(fName));
			while((s = isr.readLine()) != null) {
				sm += Integer.parseInt(s);
			}
			isr.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return sm;
	}
}
