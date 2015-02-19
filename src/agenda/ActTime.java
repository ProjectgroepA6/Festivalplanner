package agenda;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by gjoosen on 06/02/15.
 */
public class ActTime {
    
	private GregorianCalendar beginTime, endTime;
    
    public ActTime(int y1, int m1, int d1, int hh1, int mm1, int y2, int m2, int d2, int hh2, int mm2){
    	beginTime = new GregorianCalendar();
    	endTime = new GregorianCalendar();
    	setBeginTime(y1, m1, d1, hh1, mm1);
    	setEndTime(y2, m2, d2, hh2, mm2);
    }

    public void setBeginTime(int y, int m, int d, int hh, int mm){
    	beginTime.set(y,m-1,d,hh,mm);
    }
    
    public void setEndTime(int y, int m, int d, int hh, int mm){
    	endTime.set(y,m-1,d,hh,mm);
    }
    
    public String getBeginTimeString(){
    	return 	beginTime.get(Calendar.YEAR) + "-" + 
    			(beginTime.get(Calendar.MONTH)+1) + "-" + 
    			beginTime.get(Calendar.DATE) + " " + 
    			beginTime.get(Calendar.HOUR_OF_DAY) + ":" +
    			beginTime.get(Calendar.MINUTE);
    }
    
    public String getEndTimeString(){
    	return 	endTime.get(Calendar.YEAR) + "-" + 
    			(endTime.get(Calendar.MONTH)+1) + "-" + 
    			endTime.get(Calendar.DATE) + " " + 
    			endTime.get(Calendar.HOUR_OF_DAY) + ":" +
    			endTime.get(Calendar.MINUTE);
    }
    
    public GregorianCalendar getBeginTime() {
		return beginTime;
	}

	public GregorianCalendar getEndTime() {
		return endTime;
	}
  
    public int getLength(){
    	return (int) ((endTime.getTimeInMillis()/60000)-(beginTime.getTimeInMillis()/60000));
    }
  
    @Override
    public String toString(){
        return "start time: " + this.getBeginTimeString() + "\nend time: " + this.getEndTimeString() + "\nlength: " + getLength() + " Minuts";
    }
}
