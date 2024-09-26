import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Application {

	static JFrame root;

	public static void main(String[] args) {
		root = new JFrame("DateDiff");
		root.setLayout(new FlowLayout());

		JTextField txf1 = new JTextField(5);
		JTextField txf2 = new JTextField(5);
		JButton btnBerechnen = new JButton("berechne");
		btnBerechnen.addVetoableChangeListener();
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