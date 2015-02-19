package gui.panels.agenda;

import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DaySelectorPane extends JPanel{
	private JButton buttonLeft, buttonRight;
	private JLabel dateLabel;
	
	public DaySelectorPane(){
		this.setLayout(new FlowLayout());
		addContent();
	}
	
	private void addContent(){
		buttonLeft = new JButton("<");
		dateLabel = new JLabel();
		buttonRight = new JButton(">");
		this.add(buttonLeft);
		this.add(dateLabel);
		this.add(buttonRight);
	}
	
	public void setDate(GregorianCalendar date){
		dateLabel.setText(date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH)+1) + "-" + date.get(Calendar.DATE));
	}

	public JButton getButtonLeft() {
		return buttonLeft;
	}

	public JButton getButtonRight() {
		return buttonRight;
	}	
}
