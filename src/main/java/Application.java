import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Application {

	private static JFrame root;
	private static JTextField jtfDatum1;
	private static JTextField jtfDatum2;
	private static JLabel lblErgebnis;
	private static Label lastTen;
	private static String[] listLastTen = new String [10];

	public static void main(String[] args) {
		root = new JFrame("DateDiff");
		root.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

		Box mainBox = Box.createVerticalBox();

		Box firstRow = Box.createHorizontalBox();
		mainBox.add(firstRow);
		firstRow.add(new Label("1. Datum:"));

		Box secondRow = Box.createHorizontalBox();
		mainBox.add(secondRow);
		secondRow.add(new Label("2. Datum:"));

		root.getContentPane().add(mainBox);

		jtfDatum1 = new JTextField(6);
		jtfDatum1.setName("Datum1");
		jtfDatum1.getDocument().putProperty("owner", jtfDatum1);
		jtfDatum1.getDocument().addDocumentListener(new DateCheckEventHandler());
		firstRow.add(jtfDatum1);

		jtfDatum2 = new JTextField(6);
		jtfDatum2.setName("Datum2");
		jtfDatum2.setEnabled(false);
		jtfDatum2.getDocument().putProperty("owner", jtfDatum2);
		jtfDatum2.getDocument().addDocumentListener(new DateCheckEventHandler());
		secondRow.add(jtfDatum2);

		JButton jButton = new JButton("berechne");
		jButton.setName("Button");
		jButton.addActionListener(new ButtonEventHandler());
		jButton.setEnabled(false);
		root.getContentPane().add(jButton);

		lblErgebnis = new JLabel("???");
		root.getContentPane().add(lblErgebnis);

		lastTen = new Label("Sammlung");
		//lastTen.setEditable(false);
		lastTen.addMouseWheelListener(new ChangeLastTenEventHandler());
		root.getContentPane().add(lastTen);

		root.pack();
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setVisible(true);
	}

	private static class ButtonEventHandler implements ActionListener {

		private int counter = 0;
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
			try {
				Date date1 = formatter.parse(jtfDatum1.getText());
				Date date2 = formatter.parse(jtfDatum2.getText());
				long diffInMillies = date2.getTime() - date1.getTime();
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				if (diffInDays == 1 || diffInDays == -1) {
					lblErgebnis.setText(String.format("%d Tag", diffInDays));
					if (counter < 10) {
						listLastTen[counter++] = String.format("%s - %s = %d Tag", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[counter - 1]);
					} else {
						String[] buf = new String[10];
						System.arraycopy(listLastTen,1, buf, 0, 9);
						listLastTen = buf;
						listLastTen[9] = String.format("%s - %s = %d Tag", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[9]);
					}
				} else {
					lblErgebnis.setText(String.format("%d Tage", diffInDays));
					if (counter < 10) {
						listLastTen[counter++] = String.format("%s - %s = %d Tage", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[counter - 1]);
					} else {
						String[] buf = new String[10];
						System.arraycopy(listLastTen,1, buf, 0, 9);
						listLastTen = buf;
						listLastTen[9] = String.format("%s - %s = %d Tage", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[9]);
					}
				}
				root.pack();
				System.out.println(Arrays.deepToString(listLastTen));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private static class ChangeLastTenEventHandler implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int index;
			for (index = 0; index < listLastTen.length; index++) {
				if (listLastTen[index] != null && listLastTen[index].equals(lastTen.getText())) break;
			}
			index = index + e.getWheelRotation();
			if (index >= 0 && index < 10) {
				if (listLastTen[index] != null)
					lastTen.setText(listLastTen[index]);
			}
		}
	}
}