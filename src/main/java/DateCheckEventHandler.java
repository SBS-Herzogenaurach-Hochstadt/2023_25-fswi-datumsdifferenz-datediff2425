import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.Arrays;

public class DateCheckEventHandler implements DocumentListener {
	@Override
	public void insertUpdate(DocumentEvent e) {
		String regex = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
		try {
			if (e.getDocument().getText(0, e.getDocument().getLength()).matches(regex)) {
				if (e.getDocument().getProperty("owner") instanceof JTextField jtf) {
					Component [] comps = jtf.getParent().getComponents();
					if (comps[1] instanceof JTextField jtf2 && jtf.getName().equals("Datum1")) {
						jtf2.setEnabled(true);
					}
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
