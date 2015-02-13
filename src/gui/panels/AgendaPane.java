package gui.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;

import agenda.Act;
import agenda.Agenda;
import agenda.Stage;

@SuppressWarnings("serial")
public class AgendaPane extends JPanel {
	ArrayList<AgendaItemShape> agendaItems;
	private int clickedItem[];
	private int xdifference, ydifference;
	private final int yspacing = 45;
	private final int itempadding = 10;
	private final int heightoffset = 100;
	Agenda agenda;
	
	public AgendaPane(Agenda agenda){
		this.agenda = agenda;
		agendaItems = new ArrayList<AgendaItemShape>();
		for(Act act: agenda.getActs()){
			agendaItems.add(new AgendaItemShape(act, stageToInt(act.getStage()), Color.red));
		}		
		this.setPreferredSize(new Dimension(agenda.getStages().size()*100, yspacing*48+heightoffset));
		clickedItem = new int[]{-1};
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				clickedItem = new int[]{-1};			
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				clickedItem = getClickedItem(e);
				if(clickedItem[0] != -1){
					xdifference = -1*((int)agendaItems.get(clickedItem[0]).getX() - e.getX());
					ydifference = -1*((int)agendaItems.get(clickedItem[0]).getY() - e.getY())+heightoffset;	
				}				
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {	
			@Override
			public void mouseDragged(MouseEvent e) {
				if(clickedItem[0] != -1){
					Rectangle item = agendaItems.get(clickedItem[0]);
					if(clickedItem[1] == 2){
						item.setSize((int)item.getWidth(), (int)(-1*(item.getY()-e.getY())));
					}else{
						item.setLocation(e.getX()-xdifference, e.getY()-ydifference);
					}
					repaint();
				}
			}
		});
	}
	
	public int[] getClickedItem(MouseEvent e){
		if(e.getButton() == 1){
			for(int i = 0; i < agendaItems.size(); i++){
				if(agendaItems.get(i).intersects(e.getX(), e.getY(), 1, 1)){
					if(	agendaItems.get(i).getY()+agendaItems.get(i).getHeight()+10 > e.getY() &&
						agendaItems.get(i).getY()+agendaItems.get(i).getHeight()-10 < e.getY()){
						setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
						return new int[]{i, 2}; //Clicked on the bottom border of the item
					}
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
					return new int[]{i, 0}; // Clicked inside the item
				}
			}
		}
		return new int[]{-1}; //Clicked outside all the agenda-items so return a -1
	}
	
	public int stageToInt(Stage stage){
		return agenda.getStages().indexOf(stage);
	}
	public Stage intToStage(int i){
		return agenda.getStages().get(i);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;		
		int xspacing = getWidth()/agenda.getStages().size()+20;
		for(int i = 0; i < agenda.getStages().size(); i++){
			g2.setColor(Color.black);
			g2.drawString(agenda.getStages().get(i).getName(), i*xspacing+itempadding, heightoffset/2);
			g2.setColor(new Color (100,100,100, 70));
			g2.drawLine(i*xspacing, getHeight(), i*xspacing, 0); //draws the vertical separation lines
		}
		for(int i = 0; i <= 48; i++){
			if(i%2==0){
				g2.setStroke(new BasicStroke(1)); //full at normal hours
				g2.setColor(Color.BLACK);
				g2.drawString(i/2+":00", 0, i*yspacing+heightoffset);
				g2.setColor(new Color (100,100,100, 50));
			}else{
				g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0)); //dashed at half hours
			}
			g2.drawLine(0, i*yspacing+heightoffset, getWidth(), i*yspacing+heightoffset); //draws the horizontal time lines
		}
		
		for(AgendaItemShape agendaItem: agendaItems){				
			if(clickedItem[0] == -1){ //Window is resized, only resize & place the items 
				agendaItem.setSize(xspacing-itempadding*2, (int) (agendaItem.getLength()* (yspacing/30.0)));
				agendaItem.setLocation(agendaItem.getRow()*xspacing+itempadding, (int) (agendaItem.getBeginTime() * (yspacing/30.0)+heightoffset));
			}else if(agendaItems.get(clickedItem[0]) == agendaItem){ // The element is moved
				if(clickedItem[1] == 0){ //if the item is moved, not resized
					if(agendaItem.getX() / xspacing > agenda.getStages().size()-1){ // outside the window on the right
						agendaItem.setLocation((int)xspacing*(agenda.getStages().size()-1)+itempadding, (int)agendaItem.getY()+heightoffset);
					}else if(agendaItem.getX() / xspacing < 0){ // outside the window on the left
						agendaItem.setLocation((int)itempadding, (int)agendaItem.getY()+heightoffset);
					}else{ //inside the window, stick it to a row
						agendaItem.setLocation((int) Math.round(agendaItem.getX() / xspacing)*xspacing+itempadding, (int)agendaItem.getY()+heightoffset);
					}
					if(agendaItem.getY() < heightoffset){ // outside the window on the top
						agendaItem.setLocation((int)agendaItem.getX(), heightoffset);
					}else if(agendaItem.getHeight() > 48*yspacing+heightoffset){ // outside the window on the bottom
						agendaItem.setLocation((int)agendaItem.getX(),48*yspacing+heightoffset);
					}	
				}
				
				if(agendaItem.getHeight() < 30){ //check the height of the item
					agendaItem.setSize((int) agendaItem.getWidth(), 30);
				}
				
				for(AgendaItemShape agendaItem2: agendaItems){ //check if there are any collisions with other items
					if(agendaItem2 != agendaItem){
						if(agendaItem.intersects(agendaItem2)){	//intersection found
							if( agendaItem.getHeight()+agendaItem.getY() > agendaItem2.getY() && agendaItem.getY() > agendaItem2.getY()){
								agendaItem.setLocation((int)agendaItem.getX(), (int)(agendaItem2.getY()+agendaItem2.getHeight()));
							}else if(agendaItem.getY() <= agendaItem2.getY()){
								if(clickedItem[1] == 0){
									agendaItem.setLocation((int)agendaItem.getX(), (int)(agendaItem2.getY()-agendaItem.getHeight()));
								}else if(clickedItem[1] == 2){
									agendaItem.setSize((int)agendaItem.getWidth(), (int)(agendaItem2.getY()-agendaItem.getY()));
								}
							}
						}
					}
				}	 
				//update the new data to the Act
				agendaItem.setStage(intToStage((int) Math.round(agendaItem.getX() / xspacing)), (int) Math.round(agendaItem.getX() / xspacing));
				agendaItem.getTime().getBeginTime().set((GregorianCalendar.MINUTE), (int) Math.round((agendaItem.getY()-heightoffset)/45*30)%60);
				agendaItem.getTime().getBeginTime().set((GregorianCalendar.HOUR_OF_DAY), (int) Math.round((agendaItem.getY()-heightoffset)/45*30)/60);
				agendaItem.getTime().getEndTime().set(GregorianCalendar.MINUTE, (int) Math.round((agendaItem.getY()+agendaItem.getHeight()-heightoffset)/45*30)%60);
				agendaItem.getTime().getEndTime().set(GregorianCalendar.HOUR_OF_DAY, (int) Math.round((agendaItem.getY()+agendaItem.getHeight()-heightoffset)/45*30)/60);

			}
			
			g2.setColor(agendaItem.getColor());			//Fills the item with a color 
			g2.fill(agendaItem);
			g2.setColor(Color.WHITE);
			g2.drawString(agendaItem.getName(), (int)agendaItem.getX() +5, (int)agendaItem.getY() +15);
			g2.drawString(agendaItem.getTime().getBeginTimeString(), (int)agendaItem.getX() +5, (int)agendaItem.getY() +30);
			g2.drawString(agendaItem.getTime().getEndTimeString(), (int)agendaItem.getX() +5, (int)agendaItem.getY() +45);		
		}
	}	
}
