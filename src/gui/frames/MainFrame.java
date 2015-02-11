package gui.frames;

import gui.menubar.MenuBar;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	public MainFrame(JPanel panel){
		this.add(panel);
		this.setResizable(true);
		this.setBounds(100,100,1440,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setJMenuBar(new MenuBar());
		this.setVisible(true);
	}
}
