package gui.panels;

import java.awt.Color;
import java.awt.Rectangle;

public class AgendaItemShape extends Rectangle{
	private int row;
	private Color color;
	public AgendaItemShape(int r, int h, Color color) {
		super.setSize(0, h);
		setRow(r);
		setColor(color);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	

	
}
