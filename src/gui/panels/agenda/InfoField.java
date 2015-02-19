package gui.panels.agenda;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class InfoField extends JPanel {
	public InfoField(String title, String text){
		this.add(new JLabel(text));
		this.setBorder(new TitledBorder(title));
	}
}