import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
		try {
			super.paintComponent(g);
			double Pi;
			final int PROMIEN = 400;

			Graphics2D g2d = (Graphics2D) g;
			Rectangle2D rectangle = new Rectangle2D.Double(0, 0, 2 * PROMIEN, 2 * PROMIEN);
			Ellipse2D circle = new Ellipse2D.Double(0, 0, 2 * PROMIEN, 2 * PROMIEN);

			// rysowanie
			g2d.draw(rectangle);
			g2d.draw(circle);

			// wątki
			int ileWatkow = 8;
			int punktyWKole = 0;
			NowyWatek tablicaWatkow[] = new NowyWatek[ileWatkow];
			Future<Integer> tablicaUruchomienWatkow[] = new Future[ileWatkow];
			ExecutorService service = Executors.newSingleThreadExecutor();
			for (int i = 0; i < ileWatkow; i++) {
				if ((i + 1) == ileWatkow) { // ostatni wątek obsługuje pozostałe punkty
					tablicaWatkow[i] = new NowyWatek((punkty / ileWatkow) + (punktyWKole % ileWatkow), g2d);
					tablicaUruchomienWatkow[i] = service.submit(tablicaWatkow[i]);
				} else {
				tablicaWatkow[i] = new NowyWatek(punkty / ileWatkow, g2d);
				tablicaUruchomienWatkow[i] = service.submit(tablicaWatkow[i]);
				}
			}
			Integer wynik;
			for (int i = 0; i < ileWatkow; i++) {
				wynik = tablicaUruchomienWatkow[i].get();
				punktyWKole += wynik;
			}
			Pi = (4.0 * punktyWKole) / punkty;
//			System.out.println("Pi = " + Pi);
			piLabel.setText("Pi = " + Pi);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
