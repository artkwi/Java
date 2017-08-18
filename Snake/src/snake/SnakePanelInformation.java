package snake;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.ParseConversionEvent;

public class SnakePanelInformation extends JPanel{
	JLabel Lgreeting;
	private JLabel Lscore;
	
	public SnakePanelInformation() {
		setPreferredSize(new Dimension(1000, 200));
		setVisible(true);
		Lgreeting = new JLabel("Hello");
		Lscore = new JLabel("Score: 0");
		//add(Lgreeting);
		add(Lscore);
	}

	public JLabel getLscore() {
		return Lscore;
	}

	public void setLscore(int score) {
		Lscore.setText("Score: " + Integer.toString(score));
	}

}
