package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import agenda.Agenda;
import gui.panels.agenda.AgendaPane;
import gui.panels.agenda.InfoPane;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
    
    private Agenda agenda;
    
	public MainPanel(Agenda agenda){
        this.agenda = agenda;
		this.setLayout(new BorderLayout());
		this.add(new InfoPane(), BorderLayout.WEST);
		
		AgendaPane agendapanel = new AgendaPane(this.agenda);
		
		JScrollPane scroll = new JScrollPane(agendapanel);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		
		this.add(scroll, BorderLayout.CENTER);
	}
}
