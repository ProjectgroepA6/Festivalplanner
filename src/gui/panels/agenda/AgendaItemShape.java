package gui.panels.agenda;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Calendar;

import agenda.Act;
import agenda.ActTime;
import agenda.Artist;
import agenda.Stage;

public class AgendaItemShape extends Rectangle{
	private int row;
	private Color color;
	private Act act;
	
	public AgendaItemShape(Act act, int r, Color color){
		this.act=act;
		setRow(r);
		setColor(color);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setStage(Stage stage, int row){
		setRow(row);
		act.setStage(stage);
	}
	public Act getAct(){
		return act;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public ActTime getTime() {
		return act.getActTime();
	}
	public String getName() {
        String string = "";
		for(Artist artist: act.getArtists()){
            string += artist.getName() + "\n";
        }
		return string;
	}
	public int getBeginTime(){
		return getTime().getBeginTime().get(Calendar.MINUTE) + getTime().getBeginTime().get(Calendar.HOUR_OF_DAY)*60;
	}
	public int getLength(){
		return getTime().getLength();
	}
	
}
