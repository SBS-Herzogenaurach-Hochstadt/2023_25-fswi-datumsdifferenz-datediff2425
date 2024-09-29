import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Application {

	static JFrame root;

	public static void main(String[] args) {
		root = new JFrame("DateDiff");
		root.setLayout(new FlowLayout());
		root.setResizable(false);

		Box box = new Box(BoxLayout.PAGE_AXIS);
		box.setBackground(Color.BLUE);

		JTextField txf1 = new JTextField(6);
		txf1.setName("Datum1");
		txf1.getDocument().addDocumentListener(new TextChangeEventHandler());
		txf1.getDocument().putProperty("owner", txf1);

		JTextField txf2 = new JTextField(6);
		txf2.setName("Datum2");
		txf2.setEnabled(false);

		//box.add(txf1);
		//box.add(txf2);

		JButton btnBerechnen = new JButton("berechne");
		btnBerechnen.setEnabled(false);
		btnBerechnen.addActionListener(new BerechnungEventHandler());
		JLabel lblErgebnis = new JLabel("???");

		root.getContentPane().add(box);
		//root.getContentPane().add(btnBerechnen);
		//root.getContentPane().add(lblErgebnis);

		root.pack();
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setVisible(true);
	}
}