package files;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FilesOperations {

	public static void main(String[] args) {
		try {
			File file = new File("test.txt");
			Scanner scanner = new Scanner(file);
			PrintWriter output = new PrintWriter("test2.txt");

			String sentence = scanner.nextLine();
			System.out.println(sentence);
			output.println(sentence);

			scanner.close();
			output.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Nie znaleziono pliku");
			e.printStackTrace();

		}

	}

}
