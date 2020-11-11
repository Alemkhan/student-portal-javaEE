package Models;

import java.util.Date;

public class Event extends Activity {

    public Event(int id, String title, String description, Date date, Club club) {
        super(id, title, description, date, club);
    }

}
