package src.agenda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjoosen on 06/02/15.
 */
public class Agenda {
    
    private List<Stage> stages;
    private List<Artist> artists;
    private List<Act> acts;
    
    public Agenda() {
        this.stages = new ArrayList<Stage>();
        this.artists = new ArrayList<Artist>();
        this.acts = new ArrayList<Act>();
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void addStage(Stage stage){
        this.stages.add(stage);
    }
    
    public void addArtist(Artist artist){
        this.artists.add(artist);
    }
    
    public void addAct(Act act){
        this.acts.add(act);
    }
    
    public List<Artist> getArtists() {
        return artists;
    }

    public List<Act> getActs() {
        return acts;
    }
    
    @Override
    public String toString(){
        String string = "";
        for(Act act: this.acts){
            string += act + "\n";
        }
        return string;        
    }
}
