package panels;

import agenda.Agenda;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	public MainPanel(){
		new InfoPane();
		new AgendaPane();
        new Agenda();
	}
}