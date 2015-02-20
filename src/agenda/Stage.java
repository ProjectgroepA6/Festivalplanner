package agenda;

/**
 * Created by gjoosen on 06/02/15.
 */
public class Stage {
    
    private String name;
    
    public Stage(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
