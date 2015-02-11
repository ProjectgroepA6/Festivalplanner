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
    
    @Override
    public String toString(){
        return this.numberToTimeDigits(this.hours) + ":" + this.numberToTimeDigits(this.minutes);
    }
    
    private String numberToTimeDigits(int h){
        String hours;
        if(h == 0 || h == 1 || h == 2 || h == 3 || h == 4 || h == 5 || h == 6 || h == 7 || h == 8 || h == 9){
            hours = "0" + h;
        }else {
            hours = String.valueOf(h);
        }
        return hours;
    }
}
