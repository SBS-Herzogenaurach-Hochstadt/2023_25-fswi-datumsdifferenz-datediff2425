import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Datum1EventListener implements PropertyChangeListener {
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("we");
	}
}
