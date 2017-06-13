package czat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Klient2 implements Runnable {

	private static Scanner scanner = null;
	private static Socket soketKlient = null;
	private static DataOutputStream output = null;
	private static DataInputStream input = null;
	private static BufferedReader inputZKlawiatury = null;
	private static boolean closed = false;

	public static void main(String[] args) {
		// wczytanie nr portu
		scanner = new Scanner(System.in);
		System.out.println("Podaj numer portu: ");
		Integer port = scanner.nextInt();
		System.out.println(port);

		// łączene z serwerem i tworzenie strumieni
		try {
			soketKlient = new Socket("localhost", port);
			inputZKlawiatury = new BufferedReader(new InputStreamReader(System.in));
			output = new DataOutputStream(soketKlient.getOutputStream());
			input = new DataInputStream(soketKlient.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Błąd połączenia z serwerem");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// wysyłanie wiadomości i tworzenie wątku nałuchu
		if (soketKlient != null && output != null && input != null) {
			try {
				new Thread(new Klient2()).start();
				while (!closed) {
					output.writeUTF(inputZKlawiatury.readLine().trim());
				}

				output.close();
				input.close();
				soketKlient.close();
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}

	public void run() {
		String inputText;
		try {
			while ((inputText = input.readUTF()) != null) {
				System.out.println(inputText);
				if (inputText.startsWith("EXIT"))
					break;
			}
			closed = true;
		} catch (IOException e) {
			System.err.println("Odłączono");
		}
	}
}