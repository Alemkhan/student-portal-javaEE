package DAO;

import Models.News;

import java.sql.Connection;
import java.util.ArrayList;

public class NewsDAO implements DAO<News>{

    private Connection con;

    @Override
    public News get(int id) {
        return null;
    }

    @Override
    public ArrayList<News> getAll() {
        return null;
    }
}
