package snake;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakePanelInformation extends JPanel{
	JLabel Lgreeting;
	
	public SnakePanelInformation() {
		setPreferredSize(new Dimension(1000, 200));
		setVisible(true);
		Lgreeting = new JLabel("Hello");
		add(Lgreeting);
	}

}
