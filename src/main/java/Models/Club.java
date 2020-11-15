package Models;

import java.util.ArrayList;
import java.util.HashSet;

public class Club {

    private int club_id;
    private String club_name;
    private String description;
    private String avatar;
    private User owner;

    private ArrayList<News> newsList;
    private ArrayList<Event> eventsList;

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public ArrayList<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(ArrayList<Event> eventsList) {
        this.eventsList = eventsList;
    }

    public Club() {
    }

    public Club(int club_id, String club_name, String description, String avatar, User owner) {
        this.club_id = club_id;
        this.club_name = club_name;
        this.description = description;
        this.avatar = avatar;
        this.owner = owner;
    }

    public Club(String club_name, String description, String avatar) {
        this.club_name = club_name;
        this.description = description;
        this.avatar = avatar;
    }

    public Club(int club_id, String club_name) {
        this.club_id = club_id;
        this.club_name = club_name;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
