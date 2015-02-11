package panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class AgendaPane extends JPanel {
	public AgendaPane(){
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.add(new JTable());
	}
}
