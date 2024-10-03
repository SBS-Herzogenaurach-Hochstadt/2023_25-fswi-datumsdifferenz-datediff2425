import javax.swing.*;
import java.awt.*;

public class Application {

	private static JLabel ergebnis;

	public static void main(String[] args) {
		JFrame root = new JFrame("DateDiff");
		root.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

		root.getContentPane().add(new JTextField(6));
		root.getContentPane().add(new JTextField(6));

		JButton button = new JButton("berechne");
		button.addActionListener(e -> ergebnis.setText("!!!"));
		root.getContentPane().add(button);

		ergebnis = new JLabel("???");
		root.getContentPane().add(ergebnis);

		root.pack();
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setVisible(true);
	}
}