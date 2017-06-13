package czat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class Serwer {
	private static ServerSocket serverSocket = null;
	private static Socket socketKlient = null;
	private static final int maxKlient = 10;
	private static final WatkiKlienckie[] watkiKlient = new WatkiKlienckie[maxKlient];
	private static Random generator = new Random();
	public static int random = generator.nextInt(100);
	public static Boolean czyGra = true;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Serwer wystartował!");
		
		try {
			// soket serwera
			int port = 2014;
			serverSocket = new ServerSocket(port);
			serverSocket.setReuseAddress(true);

			while (true) {
				// przypisanie klienta do soket
				socketKlient = serverSocket.accept();
				
				int i=0;
				for (i = 0; i < maxKlient ; i++) {
					if (watkiKlient[i] == null) {
						(watkiKlient[i] = new WatkiKlienckie(socketKlient, watkiKlient)).start();
						break;
					}
				}
				if (i == maxKlient) {
					System.out.println("Brak miejsca na serwerze");
					socketKlient.close();
				}
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}

	}

}
