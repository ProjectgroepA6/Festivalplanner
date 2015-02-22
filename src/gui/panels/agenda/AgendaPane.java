package gui.panels.agenda;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import agenda.Agenda;

@SuppressWarnings("serial")
public class AgendaPane extends JPanel {
	private GregorianCalendar currentdate;
	private	DaySelectorPane dayselector;
	private InfoPane infopanel;
	private final AgendaScrollPane agendapanel;
	
	public AgendaPane(Agenda agenda){
		setLayout(new BorderLayout());

		agendapanel = new AgendaScrollPane(agenda);
		dayselector = new DaySelectorPane();
		infopanel = new InfoPane();
		
		JScrollPane scroll = new JScrollPane(agendapanel);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		
		this.add(scroll, BorderLayout.CENTER);
		this.add(dayselector, BorderLayout.SOUTH);
		this.add(infopanel, BorderLayout.WEST);
		
		if(agenda.getActs().size()==0){
			setCurrentdate(new GregorianCalendar());
		}else{
			GregorianCalendar date = agenda.getActs().get(0).getActTime().getBeginTime();
			setCurrentdate(new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)));
		}


		dayselector.getButtonLeft().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentdate.add(Calendar.DAY_OF_MONTH, -1);
				setCurrentdate(currentdate);
			}
		});
		dayselector.getButtonRight().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentdate.add(Calendar.DAY_OF_MONTH, 1);
				setCurrentdate(currentdate);
			}
		});
	}
	public void setCurrentdate(GregorianCalendar date){
		this.currentdate = date;
		dayselector.setDate(date);
		agendapanel.setDate(currentdate);
	}
	
}
