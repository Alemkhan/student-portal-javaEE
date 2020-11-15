package Models;

import java.util.Date;

public class News extends Activity implements Comparable<News>{

    public News(int id, String title, String description, Date date, Club club) {
        super(id, title, description, date, club);
    }

    public News(String news_title, String news_description, java.sql.Date localDate, Club club) {
        super(news_title,news_description,localDate,club);
    }

    @Override
    public int compareTo(News o) {
        return o.getDate().compareTo(this.getDate());
    }
}