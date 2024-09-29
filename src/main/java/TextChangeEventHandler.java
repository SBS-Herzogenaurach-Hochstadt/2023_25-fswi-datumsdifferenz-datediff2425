import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class TextChangeEventHandler implements DocumentListener {
	@Override
	public void insertUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		JTextField sourceField = (JTextField) e.getDocument().getProperty("owner");
		try {
			System.out.println(doc.getText(0, doc.getLength()));
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
