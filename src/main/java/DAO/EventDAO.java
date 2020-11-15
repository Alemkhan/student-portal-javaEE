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

            }
            con.close();
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
        con.close();
        return events;
    }

    public boolean deleteEvent(int event_id, int club_id) throws SQLException {
        String sql = "DELETE FROM events WHERE event_id = ? and club_id = ?";
        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, event_id);
        stmt.setInt(2, club_id);
        boolean rowInserted = stmt.executeUpdate() > 0;
        con.close();
        return rowInserted;
    }

    public boolean editEvent(int club_id,Event event) throws SQLException {
        String sql = "UPDATE events SET title = ?,description = ?, event_date = ? WHERE club_id = ? and event_id = ?";
        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setString(1, event.getTitle());
        stmt.setString(2, event.getDescription());
        stmt.setDate(3, (Date) event.getDate());
        stmt.setInt(4, club_id);
        stmt.setInt(5, event.getId());
        boolean rowInserted = stmt.executeUpdate() > 0;
        con.close();
        return rowInserted;
    }

    public boolean addEvent(Event event, int club_id) throws SQLException {
        con = DatabaseConnection.createConnection();
        String sql = "INSERT INTO events(title, description, event_date, club_id) VALUES (?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, event.getTitle());
        stmt.setString(2, event.getDescription());
        stmt.setDate(3, (Date) event.getDate());
        stmt.setInt(4, club_id);
        boolean rowInserted = stmt.executeUpdate() > 0;
        con.close();
        return rowInserted;
    }
}
