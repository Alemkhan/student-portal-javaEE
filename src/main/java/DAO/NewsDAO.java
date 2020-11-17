package DAO;

import Models.Club;
import Models.Event;
import Models.News;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class NewsDAO implements DAO<News> {

    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    @Override
    public News get(int id) throws SQLException {
        con = DatabaseConnection.createConnection();
        sql = "select n.news_id, n.title, n.description, n.publish_date, n.club_id, c.club_name " +
                "from news n JOIN clubs c WHERE n.club_id = c.club_id and n.news_id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            int news_id = resultSet.getInt("news_id");
            String news_title = resultSet.getString("title");
            String news_description = resultSet.getString("description");
            Date publish_date = resultSet.getDate("publish_date");
            int club_id = resultSet.getInt("club_id");
            String club_name = resultSet.getString("club_name");
            Club club = new Club(club_id, club_name);
            News news = new News(news_id, news_title, news_description, publish_date, club);
            con.close();
            return news;
        }
        return null;
    }

    @Override
    public ArrayList<News> getAll() {

        ArrayList<News> newsList = new ArrayList<>();

        try {
            con = DatabaseConnection.createConnection();
            sql = "select n.news_id, n.title, n.description, n.publish_date, n.club_id, c.club_name " +
                    "from news n JOIN clubs c WHERE n.club_id = c.club_id";
            stmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {

                int news_id = resultSet.getInt("news_id");
                String news_title = resultSet.getString("title");
                String news_description = resultSet.getString("description");
                Date publish_date = resultSet.getDate("publish_date");
                int club_id = resultSet.getInt("club_id");
                String club_name = resultSet.getString("club_name");
                Club club = new Club(club_id, club_name);
                News news = new News(news_id, news_title, news_description, publish_date, club);
                newsList.add(news);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }


    public boolean insertNews(int club_id, News news) throws SQLException {
        con = DatabaseConnection.createConnection();
        String sql = "INSERT INTO news(title, description, publish_date, club_id) VALUES (?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, news.getTitle());
        stmt.setString(2, news.getDescription());
        stmt.setString(3, news.getDate().toString());
        stmt.setInt(4, club_id);
        boolean rowInserted = stmt.executeUpdate() > 0;
        con.close();
        return rowInserted;
    }

    public boolean deleteNews(int club_id, int news_id) throws SQLException {
        String sql = "DELETE FROM news WHERE news_id = ? and club_id = ?";
        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(2, news_id);
        stmt.setInt(1, club_id);
        boolean rowInserted = stmt.executeUpdate() > 0;
        con.close();
        return rowInserted;
    }

    public boolean changeNews(Club club, News news) throws SQLException {
        con = DatabaseConnection.createConnection();
        String sql = "UPDATE news SET title = ?, description = ? WHERE club_id = ? and news_id = ?"; // AVATAR is in discuss !!!!!!
        stmt = con.prepareStatement(sql);
        stmt.setString(1, news.getTitle());
        stmt.setString(2, news.getDescription());
        stmt.setInt(3, club.getClub_id());
        stmt.setInt(4, news.getId());
        boolean rowUpdated = stmt.executeUpdate() > 0;
        con.close();
        return rowUpdated;
    }

    public ArrayList<News> getNewsByClubID(int club_id) throws SQLException {
        ArrayList<News> newsList = new ArrayList<>();
        sql = "select n.news_id, n.title, n.description, n.publish_date, n.club_id, c.club_name" +
                " from news n JOIN clubs c WHERE n.club_id = c.club_id and n.club_id = ?";
        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, club_id);
        resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int news_id = resultSet.getInt("news_id");
            String news_title = resultSet.getString("title");
            String news_description = resultSet.getString("description");
            Date publish_date = resultSet.getDate("publish_date");
            int clubId = resultSet.getInt("club_id");
            String club_name = resultSet.getString("club_name");
            Club club = new Club(clubId, club_name);
            News news = new News(news_id, news_title, news_description, publish_date, club);
            newsList.add(news);
        }
        con.close();
        return newsList;
    }
}
