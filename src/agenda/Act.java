package agenda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjoosen on 06/02/15.
 */
public class Act {
    
    private List<Artist> artists;
    private Stage stage;
    private String genre, name;
    private ActTime actTime;

    public Act(String name, Stage stage, String genre, ActTime actTime, Artist... artists){
        this.name = name;
        this.stage = stage; 
        this.genre = genre;
        this.actTime = actTime;

        this.artists = new ArrayList<Artist>();
        for(Artist artist: artists) {
            this.artists.add(artist);
        }
    }

    /**
     * * returns a list with all the artists that are in the act.
     * @return all artists.
     */
    public List<Artist> getArtists() {
        return artists;
    }

    /**
     * * returns the stage the act plays on.
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }
    
    public void setStage(Stage stage) {
		this.stage = stage;
	}

    /**
     * * returns the genre of the act.
     * @return the genre of the act.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * *
     * @return act name
     */
    public String getName() {
        return name;
    }

    /**
     * * return the act time.
     * @return the act time object
     */
    public ActTime getActTime() {
        return this.actTime;
    }
    
    @Override
    public String toString(){
        String string = "";
        string+="Artists: ";
        for(Artist artist: this.artists){
            string += artist.getName() + "\n";
        }
        string+= "genre: " + this.genre + "\n";
        string+= "stage: " + this.stage.getName() + "\n";
        string+= this.actTime;
        return string;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtists(Artist[] artists) {
        this.artists = new ArrayList<>();
        for(Artist artist: artists){
            this.artists.add(artist);
        }
    }
}
