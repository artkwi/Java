package czat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Serwer {
	private static ServerSocket serverSocket = null;
	private static Socket socketKlient = null;
	private static final int maxKlient = 10;
	private static final WatkiKlienckie[] watkiKlient = new WatkiKlienckie[maxKlient];
	
	public static void main(String[] args) throws IOException {
		System.out.println("Serwer wystartował!");
		
		try {
			// soket serwera
			int port = 2013;
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
