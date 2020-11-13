package DAO;

import Models.Club;
import Models.Event;

import java.sql.*;
import java.util.ArrayList;

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
                Club club = new Club(club_id);
                Event event = new Event(event_id, event_title, event_description,event_date, club);

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
}
