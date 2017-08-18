package snake;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.prism.Graphics;

public class SnakeFrame extends JFrame implements KeyListener{
	public SnakePanel snakePanel = new SnakePanel();
	public SnakePanelInformation snakePanelInformation = new SnakePanelInformation();
	public SnakePanelStartGame snakePanelStartGame = new SnakePanelStartGame();
	public char key_pressed;
	BufferedImage img;
	
	public SnakeFrame() {
		super("Snake game");
		setLayout(new FlowLayout(1,500,50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
		img = ImageIO.read(new File("snake.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		add(snakePanelStartGame);
		//add(snakePanelInformation);
		//add(snakePanel);
		addKeyListener(this);	
	}
	
	public void startGame() {
		add(snakePanelInformation);
		add(snakePanel);
		remove(snakePanelStartGame);
		// fix keylistener
		setFocusable(true);
		requestFocusInWindow(true);
		
		ImageIcon icon = new ImageIcon("snake_start.jpg"); 
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
	}
	

	
	@Override
	public void keyPressed(KeyEvent e) {
		key_pressed = e.getKeyChar();
		System.out.println("elo" + key_pressed);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
;
		
	}


}
