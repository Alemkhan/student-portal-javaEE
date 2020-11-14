package Models;

import java.util.Date;

public class News extends Activity implements Comparable<News>{

    public News(int id, String title, String description, Date date, Club club) {
        super(id, title, description, date, club);
    }

    @Override
    public int compareTo(News o) {
        return o.getDate().compareTo(this.getDate());
    }
}
