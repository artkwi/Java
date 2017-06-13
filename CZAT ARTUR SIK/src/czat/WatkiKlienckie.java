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
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
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
			output.writeUTF("Witaj " + nazwa + ", wpisz EXIT aby wyjść.\nOdgadnij liczbę z zakresu 1 - 100: ");
			
			// wysłanie wiadmości do pozostałych użytkowników
			for (int i=0 ; i< maxKlient ; i++) {
				if (watkiKlient[i] != null && watkiKlient[i] != this) {
					watkiKlient[i].output.writeUTF(nazwa + " dołączył do serwera");
				}
			}
			
			
			// czytanie wiadomości
			while (true) {
				String odpowiedz="";
				String tekst;
				tekst = input.readUTF().trim();
				if (tekst.startsWith("EXIT")) {
					break;
				}
				if (Serwer.czyGra) {
					if (isNumeric(tekst)) {
						int ile = Integer.parseInt(tekst);
						if (ile > Serwer.random) {
							System.out.println("Za dużo");
							odpowiedz = " - za dużo";
						}
						if (ile < Serwer.random) {
							System.out.println("Za mało");
							odpowiedz = " - za mało";
						}
						if (ile == Serwer.random) {
							System.out.println(
									"Dobrze! " + nazwa + " jest zwycięzcą!\nMożecie teraz swobodnie poczatować ;)");
							odpowiedz = " - dobrze! " + nazwa + " jest zwycięzcą!\nMożecie teraz swobodnie poczatować ;)";
							Serwer.czyGra = false;
						}
					} 
				}
				// wysyłanie wiadomości do pozostałych użytkowników
				for (int i=0 ; i < maxKlient ; i++) {
					if (watkiKlient[i] != null) {
						watkiKlient[i].output.writeUTF("<" + nazwa + ">: " + tekst + odpowiedz);
					}
				}
			}
			
			// pożegnanie
			
			for ( int i=0; i <maxKlient ; i++) {
			if (watkiKlient[i] != null) {
				watkiKlient[i].output.writeUTF(nazwa + " wyszedł");
			}
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
