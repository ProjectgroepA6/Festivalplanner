package panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class InfoPane extends JPanel{
	public InfoPane(){
		this.setLayout(new GridLayout(6,3,30,60));
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		label();
		textField();
	}
	
	public void label(){
		JLabel info = new JLabel("Information");
		this.add(info);
		
	}
	
	public void textField(){
		JTextPane infopane = new JTextPane();
		infopane.setEditable(false);
		infopane.setText("aaaaaa\r\nbbbbb\r\nccccc\r\nddddd\r\n");
		this.add(infopane);
		
	}
}
