package src.agenda;

import java.sql.Time;

/**
 * Created by gjoosen on 06/02/15.
 */
public class ActTime {
    
    private Time beginTime, endTime;
    
    public ActTime(Time beginTime, Time endTime){
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
