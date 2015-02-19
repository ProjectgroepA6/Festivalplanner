package gui.panels.agenda;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class InfoPane extends JPanel{
	public InfoPane(){
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setLayout(new GridBagLayout());
		
		upperText();
		middleText();
	}
	
	public void upperText(){
		InfoField upperPanel = new InfoField("Info upper","Blablablabla blablabla");
		GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0,20,0,20);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;   
		this.add(upperPanel,c);
	}
	
	public void middleText(){
		InfoField middlePanel = new InfoField("Info mid","Blablablabla");
		GridBagConstraints c = new GridBagConstraints();
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,20,0,20);
		c.gridx = 2;
        c.gridy = 1;    
        c.weightx = 1.0;
        c.weighty = 1.0;        

		this.add(middlePanel,c);
	}
}
