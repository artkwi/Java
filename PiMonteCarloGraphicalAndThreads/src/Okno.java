import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Okno extends JFrame implements ActionListener {

	PanelXY panel;
	JTextField ile_punktowTextField = new JTextField("1000");
	JButton potwierdzButton = new JButton("Potwierdź");
	JLabel ile_punktowLabel = new JLabel("Podaj liczbę punktów, dla których obliczysz liczbę Pi metodą Monte-Carlo: ");
	boolean czy_panel_istnieje = false;

	public Okno() {
		super("Obliczanie liczby Pi metoda Monte-Carlo");
		setLayout(new FlowLayout(1, 200, 50));

		add(ile_punktowLabel);
		add(ile_punktowTextField);
		add(potwierdzButton);
		ile_punktowTextField.setPreferredSize(new Dimension(100, 20));
		potwierdzButton.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (czy_panel_istnieje)
			remove(panel);
		if (source == potwierdzButton) {
			ile_punktowTextField.validate();
			int punkty = Integer.parseInt(ile_punktowTextField.getText());
			panel = new PanelXY(punkty);
			add(panel);
			czy_panel_istnieje = true;
			validate();
		}

	}

}
