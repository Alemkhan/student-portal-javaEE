package Models;

import java.util.Date;

public class Event extends Activity implements Comparable<Event>{

    public Event(int id, String title, String description, Date date, Club club) {
        super(id, title, description, date, club);
    }

    @Override
    public int compareTo(Event o) {
        return o.getDate().compareTo(this.getDate());
    }
}
