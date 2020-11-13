package DAO;

import Models.Club;
import Models.Event;
import Models.News;
import Models.User;

import javax.xml.crypto.Data;
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

    private boolean isAllowedToChangeTheNews(User user, Club club) throws SQLException {
        String sql = "SELECT club_role_id FROM club_managers WHERE club_id = ? and user_id = ?";
        stmt = con.prepareStatement(sql);
        resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            int club_role_id = resultSet.getInt("club_role_id");
            stmt.close();
            if (club_role_id < 3 && club_role_id > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean insertNews(Club club, News news, User user) throws SQLException {
        con = DatabaseConnection.createConnection();
        if (isAllowedToChangeTheNews(user, club)) {
            String sql = "INSERT INTO news(title, description, publish_date, club_id) VALUES (?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getDescription());
            stmt.setString(3, news.getDate().toString());
            stmt.setInt(4, club.getClub_id());
            boolean rowInserted = stmt.executeUpdate() > 0;
            stmt.close();
            con.close();
            return rowInserted;
        }
        else {
            return false;
        }
    }

    public boolean deleteNews(Club club, News news, User user) throws SQLException {
        con = DatabaseConnection.createConnection();
        if (isAllowedToChangeTheNews(user, club)) {
            String sql = "DELETE FROM news WHERE news_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(news.getId());
            boolean rowIserted = stmt.executeUpdate() > 0;
            stmt.close();
            con.close();
            return rowIserted;
        }
        else {
            return false;
        }
    }

    public boolean changeNews(Club club, User user, News news) throws SQLException {
        con = DatabaseConnection.createConnection();
        if (isAllowedToChangeTheNews(user,club)) {
            String sql = "UPDATE news SET title = ?, description = ?"; // AVATAR is in discuss !!!!!!
            sql += " WHERE club_id = ? and news_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getTitle());
            stmt.setInt(3, club.getClub_id());
            stmt.setInt(4, news.getId());
            boolean rowUpdated = stmt.executeUpdate() > 0;
            stmt.close();
            con.close();
            return rowUpdated;
        }
        else {
            return false;
        }
    }
}
