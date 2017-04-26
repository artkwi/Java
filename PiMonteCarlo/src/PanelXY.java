import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelXY extends JPanel {
	public int punkty;
	JLabel piLabel = new JLabel("");

	public PanelXY() {
	};

	public PanelXY(int punkty) {
		setPreferredSize(new Dimension(1000, 1000));
		piLabel.setBounds(820, 50, 150, 50);
		add(piLabel);
		setLayout(null);
		this.punkty = punkty;
		repaint(); // bo null w setLayout(null);
	}

	protected void paintComponent(Graphics g) {
		final int PROMIEN = 400;
		super.paintComponent(g);
		Random generator = new Random();
		int x, y, punkty_w_kole = 0;
		double dlugosc, Pi;
		Graphics2D g2d = (Graphics2D) g;

		// prostokat
		Rectangle2D rectangle = new Rectangle2D.Double(0, 0, 2 * PROMIEN, 2 * PROMIEN);
		// kolo
		Ellipse2D circle = new Ellipse2D.Double(0, 0, 2 * PROMIEN, 2 * PROMIEN);

		// rysowanie
		g2d.draw(rectangle);
		g2d.draw(circle);

		// punkty
		for (int i = 0; i < punkty; i++) {
			x = generator.nextInt(2 * PROMIEN);
			y = generator.nextInt(2 * PROMIEN);
			dlugosc = Math.sqrt(Math.pow(x - PROMIEN, 2) + Math.pow(y - PROMIEN, 2));
			Ellipse2D punkt = new Ellipse2D.Double(x, y, 3, 3);
			if (dlugosc <= PROMIEN) {
				punkty_w_kole++;
				g2d.setColor(Color.RED);
			}
			g2d.fill(punkt);
			g2d.setColor(Color.BLACK);
		}

		Pi = (4.0 * punkty_w_kole) / punkty;
		piLabel.setText("Pi = " + Pi);
		//System.out.println(Pi);
	}
}
