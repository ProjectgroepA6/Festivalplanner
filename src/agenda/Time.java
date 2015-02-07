package src.agenda;

/**
 * Created by gjoosen on 07/02/15.
 */
public class Time {
    
    private int hours, minutes;
    
    public Time(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
