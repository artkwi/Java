package stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamTwoWay {

	public static void main(String[] args) {
		try {
			File file_read = new File("read.txt");
			File file_write = new File("write.txt");
			FileInputStream read_strem = new FileInputStream(file_read);
			FileOutputStream write_strem = new FileOutputStream(file_write);
			
			int sentence;
			while ((sentence= read_strem.read()) != -1) {
			System.out.print((char)sentence);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
