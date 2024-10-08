import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Application {

	private static JFrame root;
	private static JTextField jtfDatum1;
	private static JTextField jtfDatum2;
	private static JLabel lblErgebnis;

	public static void main(String[] args) {
		root = new JFrame("DateDiff");
		root.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

		jtfDatum1 = new JTextField(6);
		jtfDatum2 = new JTextField(6);
		root.getContentPane().add(jtfDatum1);
		root.getContentPane().add(jtfDatum2);

		JButton jButton = new JButton("berechne");
		jButton.addActionListener(new ButtonEventHandler());
		root.getContentPane().add(jButton);

		lblErgebnis = new JLabel("???");
		root.getContentPane().add(lblErgebnis);

		root.pack();
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setVisible(true);
	}

	private static class ButtonEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
			try {
				Date date1 = formatter.parse(jtfDatum1.getText());
				Date date2 = formatter.parse(jtfDatum2.getText());
				long diffInMillies = date2.getTime() - date1.getTime();
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				if (diffInDays == 1 || diffInDays == -1)
					lblErgebnis.setText(String.format("%d Tag", diffInDays));
				else
					lblErgebnis.setText(String.format("%d Tage", diffInDays));
				root.pack();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}