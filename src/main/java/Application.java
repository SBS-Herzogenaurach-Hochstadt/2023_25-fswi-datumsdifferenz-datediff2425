import de.sbs.fswi.services.DataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Application {

	private static JFrame root;
	private static JTextField jtfDatum1;
	private static JTextField jtfDatum2;
	private static JLabel lblErgebnis;
	private static Label lastTen;
	private static String[] listLastTen = new String [10];
	private static DataAccessObject dao;

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

		lastTen = new Label();
		dao = new DataAccessObject("C:/Users/cgg/Documents/data/datediff.txt");
		String[][] bufLastTen = Arrays.stream(dao.findAll()).map(date -> date.split("#")).toArray(String[][]::new);
		int countDown = 9;
		for (int i = bufLastTen.length - 1 ; i >= 0; i--) {
			if (i == bufLastTen.length - 1) {
				lastTen.setText(String.format("%s - %s = %s", bufLastTen[i][0], bufLastTen[i][1], bufLastTen[i][2]));
			}
			if (countDown >= 0)
				listLastTen[countDown--] = String.format("%s - %s = %s", bufLastTen[i][0], bufLastTen[i][1], bufLastTen[i][2]);
			else
				break;
		}
		lastTen.addMouseWheelListener(new ChangeLastTenEventHandler());
		root.getContentPane().add(lastTen);

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
				int counter = IntStream.range(0, listLastTen.length - 1)
						.filter(i -> listLastTen[i] == null) // Filter nach null oder leeren Arrays
						.findFirst()
						.orElse(10);
				if (diffInDays == 1 || diffInDays == -1) {
					lblErgebnis.setText(String.format("%d Tag", diffInDays));
					if (counter < 10) {
						listLastTen[counter++] = String.format("%s - %s = %d Tag", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[counter - 1]);
						dao.save(new String[]{jtfDatum1.getText(), jtfDatum2.getText(), diffInDays + " Tag", new Timestamp(System.currentTimeMillis()).toString()});
					} else {
						String[] buf = new String[10];
						System.arraycopy(listLastTen,1, buf, 0, 9);
						listLastTen = buf;
						listLastTen[9] = String.format("%s - %s = %d Tag", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[9]);
						dao.save(new String[]{jtfDatum1.getText(), jtfDatum2.getText(), diffInDays + " Tag", new Timestamp(System.currentTimeMillis()).toString()});
					}
				} else {
					lblErgebnis.setText(String.format("%d Tage", diffInDays));
					if (counter < 10) {
						listLastTen[counter++] = String.format("%s - %s = %d Tage", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[counter - 1]);
						dao.save(new String[]{jtfDatum1.getText(), jtfDatum2.getText(), diffInDays + " Tage", new Timestamp(System.currentTimeMillis()).toString()});
					} else {
						String[] buf = new String[10];
						System.arraycopy(listLastTen,1, buf, 0, 9);
						listLastTen = buf;
						listLastTen[9] = String.format("%s - %s = %d Tage", jtfDatum1.getText(), jtfDatum2.getText(), diffInDays);
						lastTen.setText(listLastTen[9]);
						dao.save(new String[]{jtfDatum1.getText(), jtfDatum2.getText(), diffInDays + " Tage", new Timestamp(System.currentTimeMillis()).toString()});
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
				root.pack();
			}
		}
	}
}