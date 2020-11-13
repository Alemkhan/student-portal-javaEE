package DAO;

import Models.Club;
import Models.Event;
import Models.News;

import java.sql.*;
import java.util.ArrayList;

public class NewsDAO implements DAO<News>{

    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    @Override
    public News get(int id) {
        return null;
    }

    @Override
    public ArrayList<News> getAll() throws SQLException {

        ArrayList<News> newsList = new ArrayList<>();

        try {
            con = DatabaseConnection.createConnection();
            sql = "select * from news";
            stmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            while (resultSet.next()){

                int news_id = resultSet.getInt("news_id");
                String news_title = resultSet.getString("title");
                String news_description = resultSet.getString("description");
                Date publish_date = resultSet.getDate("publish_date");
                int club_id = resultSet.getInt("club_id");
                Club club = new Club(club_id);
                News news = new News(news_id,news_title,news_description, publish_date, club);

                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }
}
