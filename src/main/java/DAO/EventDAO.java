package DAO;

import Models.Event;

import java.sql.Connection;
import java.util.ArrayList;

public class EventDAO implements DAO<Event>{

    private Connection con;

    @Override
    public Event get(int id) {
        return null;
    }

    @Override
    public ArrayList<Event> getAll() {
        return null;
    }
}
