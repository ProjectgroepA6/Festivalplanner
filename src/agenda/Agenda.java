package agenda;

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
        
        this.testAgenda();
    }

    private void testAgenda(){
        //stages
        this.stages.add(new Stage("Main stage"));
        this.stages.add(new Stage("Tent stage"));
        
        //artiesten
        this.artists.add(new Artist("Iron Maiden", "Heavy metal"));
        this.artists.add(new Artist("Slayer", "Tresh metal"));
        this.artists.add(new Artist("Sabaton", "Power metal"));

        //acts
        this.acts.add(new Act(this.stages.get(0), "Heavy metal", new ActTime(2015,02,11,21,00  ,2015,02,11,23,00), this.artists.get(0)));
        this.acts.add(new Act(this.stages.get(1), "Test metal" , new ActTime(2015,02,11,23,00  ,2015,02,12,04,30), this.artists.get(1)));
        this.acts.add(new Act(this.stages.get(0), "Power metal" ,new ActTime(2015,02,11,20,00  ,2015,02,11,23,00), this.artists.get(2)));
        
        System.out.println(this);
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
            string += act + "\n\n";
        }
        return string;
    }
}
