package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SnakePanel extends JPanel {
	JButton [][] buttons_array = new JButton[10][10];
	public SnakePanel() {
		setSize(500, 500);
		setVisible(true);
		setBackground(Color.blue);
		setPreferredSize(new Dimension(500, 500));
		// 10 x 10 blocks
		setLayout(new GridLayout(10, 10));
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++){
				buttons_array[i][j] = new JButton("");
				add(buttons_array[i][j]);
				buttons_array[i][j].setBorder(null);
			}
		}
	}

}
