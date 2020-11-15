package DAO;

import Models.Club;
import Models.Event;
import Models.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class EventDAO implements DAO<Event>{

    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    @Override
    public Event get(int id) {
        return null;
    }

    @Override
    public ArrayList<Event> getAll() throws SQLException {

        ArrayList<Event> events = new ArrayList<>();

        try {
            con = DatabaseConnection.createConnection();
            sql = "select * from events";
            stmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            while (resultSet.next()){

                int event_id = resultSet.getInt("event_id");
                String event_title = resultSet.getString("title");
                String event_description = resultSet.getString("description");
                Date event_date = resultSet.getDate("event_date");
                int club_id = resultSet.getInt("club_id");
                Club club = new Club();
                club.setClub_id(club_id);
                Event event = new Event(event_id, event_title, event_description,event_date, club);
                events.add(event);
                stmt.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public ArrayList<Event> getAllClubEvents(int club_id) throws SQLException {

        ArrayList<Event> events = new ArrayList<>();
        sql = "select e.event_id, e.title, e.description, e.event_date, c.club_id, c.club_name" +
                " from events e JOIN clubs c WHERE e.club_id = c.club_id and e.club_id = ?";
        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, club_id);
        resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int event_id = resultSet.getInt("event_id");
            String event_title = resultSet.getString("title");
            String event_description = resultSet.getString("description");
            Date publish_date = resultSet.getDate("event_date");
            int clubId = resultSet.getInt("club_id");
            String club_name = resultSet.getString("club_name");
            Club club = new Club(clubId, club_name);
            Event event = new Event(event_id, event_title, event_description, publish_date, club);
            events.add(event);
        }
        stmt.close();
        con.close();
        return events;
    }

    public boolean deleteEvent(int event_id, int club_id) throws SQLException {
        String sql = "DELETE FROM events WHERE event_id = ? and club_id = ?";
    }
}
