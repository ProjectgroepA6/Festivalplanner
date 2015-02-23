package gui.panels.table;

import agenda.Act;
import agenda.Agenda;
import agenda.Artist;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by gjoosen on 23/02/15.
 */
public class TableModel extends AbstractTableModel {

    private ArrayList<Act> acts;
    private Agenda agenda;

    private String[] columnNames;
    private Object[][] data;

    public TableModel(Agenda agenda){
        this.acts = new ArrayList<>();
        this.agenda = agenda;
        
        for(Act act: this.agenda.getActs()){
            this.acts.add(act);
        }
        this.columnNames = new String[]{"actname", "artists", "stage", "genre" ,"start time", "end time"};

        this.data = new Object[this.acts.size()][columnNames.length];
        for(int i = 0; i < acts.size(); i++){
            data[i][0] = acts.get(i).getName();
            
            String artists = "";
            for(Artist artist: acts.get(i).getArtists()){
                if(!artists.equals("")){
                    artists += " - " + artist;
                }else{
                    artists += artist;
                }
            }
            
            data[i][1] = artists;
            data[i][2] = acts.get(i).getStage();
            data[i][3] = acts.get(i).getGenre();
            data[i][4] = acts.get(i).getActTime().getBeginTimeString();
            data[i][5] = acts.get(i).getActTime().getEndTimeString();
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
