package gui.frames;

import agenda.Agenda;
import gui.menubar.MenuBar;
import gui.panels.MainPanel;
import gui.panels.edit.EditPane;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
    private Agenda agenda;
    
    public MainFrame(){
        this.agenda = new Agenda();
        this.add(new EditPane(this.agenda));
		this.setResizable(true);
		this.setBounds(100,100,1440,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setJMenuBar(new MenuBar());
		this.setVisible(true);
	}
}
