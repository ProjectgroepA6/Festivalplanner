package gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AgendaPane extends JPanel {
	ArrayList<AgendaItemShape> agendaItems;
	private int clickedItem[];
	private int xdifference, ydifference;
	int vakjes = 5;
	
	public AgendaPane(){
		agendaItems = new ArrayList<AgendaItemShape>();
		agendaItems.add(new AgendaItemShape(0, 100, Color.BLUE));
		agendaItems.add(new AgendaItemShape(2,100, Color.BLACK));
		
		this.setLayout(new BorderLayout());
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
					ydifference = -1*((int)agendaItems.get(clickedItem[0]).getY() - e.getY());	
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
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;		
		int xspacing = getWidth()/vakjes;	
		
		for(int i = 0; i <= vakjes; i++){
			g2.drawLine(i*xspacing, getHeight(), i*xspacing, 0); //draws the vertical separation lines
		}
		for(AgendaItemShape agendaItem: agendaItems){				
			if(clickedItem[0] == -1){ //Window is resized, only resize the items
				agendaItem.setSize(xspacing, (int) agendaItem.getHeight());
				agendaItem.setLocation(agendaItem.getRow()*xspacing, (int) agendaItem.getY());
			}else if(agendaItems.get(clickedItem[0]) == agendaItem){ // The element is moved
				if(agendaItem.getX() / xspacing > vakjes-1){ // outside the field on the right
					agendaItem.setLocation((int)xspacing*(vakjes-1), (int)agendaItem.getY());
				}else if(agendaItem.getX() / xspacing < 0){ // outside the field on the left
					agendaItem.setLocation((int)0, (int)agendaItem.getY());
				}else{ //inside the field, stick it to a row
					agendaItem.setRow((int) Math.round(agendaItem.getX() / xspacing));
					agendaItem.setLocation((int) Math.round(agendaItem.getX() / xspacing)*xspacing, (int)agendaItem.getY());
				}
				
				if(agendaItem.getHeight() < 30){ //check the height of the item
					agendaItem.setSize((int) agendaItem.getWidth(), 30);
				}
				
				for(AgendaItemShape agendaItem2: agendaItems){ //check if there are any collisions with other items
					if(agendaItem2 != agendaItem){
						if(agendaItem.intersects(agendaItem2)){	//intersection found
							if( agendaItem.getHeight()+agendaItem.getY() > agendaItem2.getY() && agendaItem.getY() > agendaItem2.getY()){
								agendaItem.setLocation((int)agendaItem.getX(), (int)(agendaItem2.getY()+agendaItem2.getHeight()));
							}else if(agendaItem.getY() < agendaItem2.getY()){
								if(clickedItem[1] == 0){
									agendaItem.setLocation((int)agendaItem.getX(), (int)(agendaItem2.getY()-agendaItem.getHeight()));
								}else if(clickedItem[1] == 2){
									agendaItem.setSize((int)agendaItem.getWidth(), (int)(agendaItem2.getY()-agendaItem.getY()));
								}
							}
						}
					}
				}	
			}
			//Fills the item with a color 
			g2.setColor(agendaItem.getColor());
			g2.fill(agendaItem);
		}
	}	
}
