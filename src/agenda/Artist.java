package src.agenda;

/**
 * Created by gjoosen on 06/02/15.
 */
public class Artist {
    
    private String name, genre;
    
    public Artist(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
