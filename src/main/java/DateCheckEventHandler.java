import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class DateCheckEventHandler implements DocumentListener {
	@Override
	public void insertUpdate(DocumentEvent e) {
		String regex = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
		try {
			if (e.getDocument().getProperty("owner") instanceof JTextField jtf) {
				switch (jtf.getName()) {
					case "Datum1":
						jtf.getParent()
								.getComponent(1)
								.setEnabled(e.getDocument().getText(0, e.getDocument().getLength()).matches(regex));
						break;
					case "Datum2":
						jtf.getParent()
								.getComponent(2)
								.setEnabled(e.getDocument().getText(0, e.getDocument().getLength()).matches(regex));
						break;
				}
			}
		} catch (BadLocationException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		insertUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}
}
