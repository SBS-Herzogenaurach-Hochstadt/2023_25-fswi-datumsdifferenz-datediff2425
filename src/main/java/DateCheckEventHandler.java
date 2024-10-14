import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class DateCheckEventHandler implements DocumentListener {
	@Override
	public void insertUpdate(DocumentEvent e) {
		String regex = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
		try {
			if (e.getDocument().getProperty("owner") instanceof JTextField jtf) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jtf);
				switch (jtf.getName()) {
					case "Datum1":
						for (Component comp : frame.getContentPane().getComponents()) {
							if (comp instanceof Box box1) {
								for (Component boxComp1 : box1.getComponents()) {
									if (boxComp1 instanceof Box box2) {
										for (Component boxComp2 : box2.getComponents()) {
											if ("Datum2".equals(boxComp2.getName())) {
												boxComp2.setEnabled(e.getDocument().getText(0, e.getDocument().getLength()).matches(regex));
											}
										}
									}
								}
							}
						}
						break;
					case "Datum2":
						for (Component comp : frame.getContentPane().getComponents()) {
							if (comp instanceof JButton btn) {
								if ("Button".equals(btn.getName()))
									btn.setEnabled(e.getDocument().getText(0, e.getDocument().getLength()).matches(regex));
							}
						}
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
