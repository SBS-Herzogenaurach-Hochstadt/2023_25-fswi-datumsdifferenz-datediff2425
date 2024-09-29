import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BerechnungEventHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Container parent = ((JButton) e.getSource()).getParent();
		for (Component item : parent.getComponents()) {
			if (item instanceof JTextField jtf) {
				String regex = "^([0-2][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$";
				String text = jtf.getText();  // Textinhalt des JTextFields

				if (text.matches(regex)) {
					JOptionPane.showMessageDialog(null, jtf.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Falsche Eingabe in Textfeld " + jtf.getName());
				}
			}
		}
	}
}

