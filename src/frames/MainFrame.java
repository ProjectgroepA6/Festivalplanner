package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
		public MainFrame(JPanel panel){
            
            if(System.getProperties().getProperty("os.name").equals("Mac OS X")) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
            }
            
		    this.add(panel);
		    this.setResizable(true);
		    this.setBounds(100,100,1440,900);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setUndecorated(false);
		    this.setJMenuBar(new MenuBar());
		    this.setVisible(true);
		}
}
