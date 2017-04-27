import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.concurrent.Callable;

class NowyWatek implements Callable<Integer> {
	private int punkty;
	private Graphics2D g2d;

	public NowyWatek() {
		punkty = 200;
	};

	public NowyWatek(int punkty, Graphics2D g2d) {
		this.punkty = punkty;
		this.g2d = g2d;
	};

	public Integer call() throws InterruptedException {
		int PROMIEN = 400;
		Random generator = new Random();
		int x, y, punkty_w_kole = 0;
		double dlugosc;
		for (int i = 0; i < punkty; i++) {
			x = generator.nextInt(2 * PROMIEN);
			y = generator.nextInt(2 * PROMIEN);
			dlugosc = Math.sqrt(Math.pow(x - PROMIEN, 2) + Math.pow(y - PROMIEN, 2));
			if (dlugosc <= PROMIEN) {
				punkty_w_kole++;
				g2d.setColor(Color.RED);
			}
			Ellipse2D punkt = new Ellipse2D.Double(x, y, 3, 3);
			g2d.fill(punkt);
			g2d.setColor(Color.BLACK);
		}
		return punkty_w_kole;
	}
}