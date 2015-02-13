package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import agenda.Agenda;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	public MainPanel(){
		this.setLayout(new BorderLayout());
		this.add(new InfoPane(), BorderLayout.WEST);
		
		Agenda agenda = new Agenda();
		AgendaPane agendapanel = new AgendaPane(agenda);
		
		JScrollPane scroll = new JScrollPane(agendapanel);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		
		
		this.add(scroll, BorderLayout.CENTER);
		
		
	}
}
