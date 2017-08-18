package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakePanelStartGame extends JPanel implements ActionListener{
	private JButton Bstart = new JButton("Start");
	private JLabel LSpeed = new JLabel("Speed");
	private JComboBox<String> CBspeed;
	private Boolean game_start;
	private String[] speed_list;
	private Integer speed = 1;
	
	public SnakePanelStartGame() {
		setPreferredSize(new Dimension(1000, 200));
		setVisible(true);
		add(Bstart);
		Bstart.addActionListener(this);
		
		game_start = false;
		speed_list = new String [] {"1","2","3"};
		CBspeed = new JComboBox<>(speed_list);
		CBspeed.addActionListener(this);
		
		add(CBspeed);
		add(LSpeed);
		
		ImageIcon icon = new ImageIcon("snake_start.jpg"); 
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == Bstart) {
			setBackground(Color.BLUE);
			game_start = true;
			this.speed = this.CBspeed.getSelectedIndex()+1;
		}
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public JButton getBstart() {
		return Bstart;
	}

	public void setBstart(JButton bstart) {
		Bstart = bstart;
	}

	public Boolean getGame_start() {
		return game_start;
	}

	public void setGame_start(Boolean game_start) {
		this.game_start = game_start;
	}
}
