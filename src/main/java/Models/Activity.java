package Models;

import java.util.Date;

public class Activity {

    private int id;
    private String title;
    private String description;
    private Date date;
    private Club club;

    public Activity(int id, String title, String description, Date date, Club club) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.club = club;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
