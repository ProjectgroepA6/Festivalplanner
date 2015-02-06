package src.agenda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjoosen on 06/02/15.
 */
public class Act {
    
    private List<Artist> artists;
    private Stage stage;
    private String genre;
    private ActTime actTime;

    public Act(Stage stage, String genre, Artist... artists){
        this.stage = stage; 
        this.genre = genre;
        
        this.artists = new ArrayList<Artist>();
        for(Artist artist: artists) {
            this.artists.add(artist);
        }
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Stage getStage() {
        return stage;
    }

    public String getGenre() {
        return genre;
    }

    public ActTime getActTime() {
        return actTime;
    }
    
    @Override
    public String toString(){
        String string = "";
        string+="Artists: ";
        for(Artist artist: this.artists){
            string += artist.getName() + "\n";
        }
    
        string+= "genre: " + this.genre + "\n";
        string+= "stage: " + this.stage.getName();
        return string;
    }
}
