package src.agenda;

/**
 * Created by gjoosen on 06/02/15.
 */
public class ActTime {
    
    private Time beginTime, endTime;
    
    public ActTime(Time beginTime, Time endTime){
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    /**
     * *
     * @return the length of the act in minutes.
     */
    public int getLength(){
        int difHours = this.endTime.getHours() - this.beginTime.getHours();
        int difMinutes = this.endTime.getMinutes() - this.beginTime.getMinutes();
        return difHours  * 60 + difMinutes;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }
    
    @Override
    public String toString(){
        return "start time: " + this.beginTime + "\nend time: " + this.endTime;
    }
}
