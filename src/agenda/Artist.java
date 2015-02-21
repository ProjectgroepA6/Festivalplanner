package agenda;

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
    
    @Override
    public String toString(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
