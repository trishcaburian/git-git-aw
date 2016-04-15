
import java.util.Date;
import java.sql.Time;

public class EventInfo {

	String eName;
	String eDesc;
	Date eDate;
	Time startTime;
    Time endTime;
    String eventLocation;
	
	  public String geteName() {
        return eName;
    }

    public String geteDesc() {
        return eDesc;
    }

    public Date geteDate() {
        return eDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}