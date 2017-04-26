import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.xml.bind.ValidationEvent;

public class Test {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Okno();
			}
		});
	}
}
