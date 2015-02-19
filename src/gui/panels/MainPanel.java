package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import agenda.Agenda;
import gui.panels.agenda.AgendaPane;
import gui.panels.agenda.AgendaScrollPane;
import gui.panels.agenda.InfoPane;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
    
    private Agenda agenda;
    
	public MainPanel(Agenda agenda){
        this.agenda = agenda;
		this.setLayout(new BorderLayout());
		this.add(new InfoPane(), BorderLayout.WEST);
		this.add(new AgendaPane(agenda), BorderLayout.CENTER);
	}
}
