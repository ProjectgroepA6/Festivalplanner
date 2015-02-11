package gui.panels;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DaySelectorPane extends JPanel{
	public DaySelectorPane(){
		this.setLayout(new FlowLayout());
		addContent();
	}
	
	private void addContent(){
		JButton buttonLeft = new JButton("<");
		JLabel dateLabel = new JLabel("11-09-2001");
		JButton buttonRight = new JButton(">");
		this.add(buttonLeft);
		this.add(dateLabel);
		this.add(buttonRight);
	}
}
