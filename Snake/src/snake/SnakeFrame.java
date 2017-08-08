package snake;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class SnakeFrame extends JFrame {
	public SnakePanel snakePanel = new SnakePanel();
	public JButton button =  new JButton("Kot");
	public SnakeFrame() {
		super("Snake same");
		setLayout(new FlowLayout(1,200,50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		snakePanel.setPreferredSize(500,500);
		add(snakePanel);
		add(button);
		
		
	}


}
