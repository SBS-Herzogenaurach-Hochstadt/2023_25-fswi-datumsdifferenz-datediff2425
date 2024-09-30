import javax.swing.*;
import java.awt.*;

public class Application {

	public static void main(String[] args) {
		JFrame root = new JFrame("DateDiff");
		root.setLayout(new FlowLayout());

		JTextField txf1 = new JTextField(6);
		JTextField txf2 = new JTextField(6);
		JButton btnBerechnen = new JButton("berechne");
		JLabel lblErgebnis = new JLabel("???");

		root.getContentPane().add(txf1);
		root.getContentPane().add(txf2);
		root.getContentPane().add(btnBerechnen);
		root.getContentPane().add(lblErgebnis);

		root.pack();
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setVisible(true);
	}
}