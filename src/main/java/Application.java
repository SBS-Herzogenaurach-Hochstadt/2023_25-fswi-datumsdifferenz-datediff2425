import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Application {

	private static JFrame root;
	private static JTextField textField1;
	private static JTextField textField2;
	private static JLabel jLabel;

	public static void main(String[] args) {
		root = new JFrame("DateDiff");
		root.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

		textField1 = new JTextField(6);
		textField2 = new JTextField(6);
		root.getContentPane().add(textField1);
		root.getContentPane().add(textField2);

		JButton jButton = new JButton("berechne");
		jButton.addActionListener(new ButtonEventHandler());
		root.getContentPane().add(jButton);

		jLabel = new JLabel("???");
		root.getContentPane().add(jLabel);

		root.pack();
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setVisible(true);
	}

	private static class ButtonEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			try {
				Date date1 = formatter.parse(textField1.getText());
				Date date2 = formatter.parse(textField2.getText());
				long diffInMillies = date2.getTime() - date1.getTime();
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				jLabel.setText(String.format("%d Tage", diffInDays));
				root.pack();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}