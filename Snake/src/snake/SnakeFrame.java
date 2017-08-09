package snake;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame implements KeyListener{
	public SnakePanel snakePanel = new SnakePanel();
	public JButton button =  new JButton("Kot");
	public char key_pressed;
	
	public SnakeFrame() {
		super("Snake same");
		setLayout(new FlowLayout(1,200,50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		snakePanel.setPreferredSize(500,500);
		add(snakePanel);
		add(button);
		addKeyListener(this);
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		key_pressed = e.getKeyChar();
		System.out.println("elo" + key_pressed);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
;
		
	}


}
