package czat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class WatkiKlienckie extends Thread {
	private DataInputStream input = null;
	private DataOutputStream output = null;
	private Socket socketKlient = null;
	private final WatkiKlienckie[] watkiKlient;
	private int maxKlient;
	String nazwaPokoju;
	
	public WatkiKlienckie(Socket socketKlient, WatkiKlienckie[] watkiKlient) {
		this.socketKlient = socketKlient;
		this.watkiKlient = watkiKlient;
		maxKlient = watkiKlient.length;
	}

	@Override
	public void run() {
		int maxKlient = this.maxKlient;
		WatkiKlienckie[] watkiKlienckies = this.watkiKlient;
		try {
			// podłączenie nowego użytkownika
			input = new DataInputStream(socketKlient.getInputStream());
			output = new DataOutputStream(socketKlient.getOutputStream());
			output.writeUTF("Podaj swoją nazwę: ");
			String nazwa = input.readUTF().trim();
			System.out.println(nazwa);
			output.writeUTF("Witaj " + nazwa + ", wpisz EXIT aby wyjść");
			
			// wysłanie wiadmości do pozostałych użytkowników
			for (int i=0 ; i< maxKlient ; i++) {
				if (watkiKlient[i] != null && watkiKlient[i] != this) {
					watkiKlient[i].output.writeUTF(nazwa + " dołączył do serwera");
				}
			}
			
			// czytanie wiadomości
			while (true) {
				String tekst;
				tekst = input.readUTF().trim();
				if (tekst.startsWith("EXIT")) {
					break;
				}
				// wysyłanie wiadomości do pozostałych użytkowników
				for (int i=0 ; i < maxKlient ; i++) {
					if (watkiKlient[i] != null) {
						watkiKlient[i].output.writeUTF("<" + nazwa + ">: " + tekst);
					}
				}
			}
			
			// pożegnanie
			
			for ( int i=0; i <maxKlient ; i++) {
				if (watkiKlient[i] == this) {
					watkiKlient[i] = null;
				}
			}
			
			// zamykanie strumieni i soketów
			input.close();
			output.close();
			socketKlient.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
