package Models;

import java.util.Date;

public class Event extends Activity implements Comparable<Event>{

    public Event(int id, String title, String description, Date date, Club club) {
        super(id, title, description, date, club);
    }

    public Event(String event_title, String event_description, java.sql.Date localDate, Club club) {
        super(event_title,event_description,localDate,club);
    }

    @Override
    public int compareTo(Event o) {
        return o.getDate().compareTo(this.getDate());
    }
}