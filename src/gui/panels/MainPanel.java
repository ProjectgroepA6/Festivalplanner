package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	public MainPanel(){
		this.setLayout(new BorderLayout());
		this.add(new InfoPane(), BorderLayout.WEST);
		this.add(new AgendaPane(), BorderLayout.CENTER);;
		this.add(new DaySelectorPane(), BorderLayout.SOUTH);
		
	}
}
