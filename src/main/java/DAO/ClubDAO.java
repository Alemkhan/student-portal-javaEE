package DAO;

import Models.Club;
import Models.News;
import Models.Student;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class ClubDAO implements DAO<Club> {

    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    @Override
    public Club get(int id) {

        Club club = new Club();

        try {

            con = DatabaseConnection.createConnection();
            sql = "select c.*, u.email from clubs c JOIN users u ON c.owner_id = u.user_id where club_id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {

                int club_id = resultSet.getInt("club_id");
                String club_name = resultSet.getString("club_name");
                String club_description = resultSet.getString("description");
                String club_avatar = resultSet.getString("avatar");
                int owner_id = resultSet.getInt("owner_id");
                String owner_email = resultSet.getString("email");
                User owner = new Student();
                owner.setId(owner_id);
                owner.setEmail(owner_email);
                club = new Club(club_id, club_name, club_description, club_avatar, owner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return club;
    }

    @Override
    public ArrayList<Club> getAll() {

        ArrayList<Club> clubs = new ArrayList<>();

        try {
            con = DatabaseConnection.createConnection();
            sql = "select c.*, u.email from clubs c JOIN users u ON c.owner_id = u.user_id";
            stmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int club_id = resultSet.getInt("club_id");
                String club_name = resultSet.getString("club_name");
                String club_description = resultSet.getString("description");
                String club_avatar = resultSet.getString("avatar");
                int owner_id = resultSet.getInt("owner_id");
                String owner_email = resultSet.getString("email");
                User owner = new Student();
                owner.setId(owner_id);
                owner.setEmail(owner_email);
                Club club = new Club(club_id, club_name, club_description, club_avatar, owner);
                clubs.add(club);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    private boolean updateClubManager(int ownerId, int clubId) throws SQLException {
        String sql = "INSERT INTO club_managers (club_id, user_id, club_role_id) VALUES (?, ?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, clubId);
        stmt.setInt(2, ownerId);
        stmt.setInt(3, 1);
        return stmt.executeUpdate() > 0;
    }

    public boolean createClub(int ownerId, Club club) throws SQLException {

        String sql = "INSERT INTO clubs (club_name, description, avatar, owner_id) VALUES (?, ?, ?, ?)";

        con = DatabaseConnection.createConnection(); //Connecting to Database

        stmt = con.prepareStatement(sql);
        stmt.setString(1, club.getClub_name());
        stmt.setString(2, club.getDescription());
        stmt.setString(3, club.getAvatar());
        stmt.setInt(4, ownerId);

        boolean rowInserted = stmt.executeUpdate() > 0;
        boolean rowUpdated = updateClubManager(club.getClub_id(), ownerId);
        con.close();
        return rowInserted && rowUpdated;
    }

    public boolean updateClub(int ownerId, Club club) throws SQLException {
        String sql = "UPDATE clubs SET club_name = ?, description = ? WHERE club_id = ? and owner_id = ?;"; // AVATAR is in discuss !!!!!!

        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setString(1, club.getClub_name());
        stmt.setString(2, club.getDescription());
        stmt.setInt(3, club.getClub_id());
        stmt.setInt(4, ownerId);
        boolean rowUpdated = stmt.executeUpdate() > 0;
        con.close();
        return rowUpdated;
    }

    public ArrayList<News> getAllClubNews(int club_id) throws SQLException {
        NewsDAO nDAO = new NewsDAO();
        return nDAO.getNewsByClubID(club_id);
    }

    public boolean deleteClub(int club_id) throws SQLException {
        String sql = "DELETE FROM clubs where club_id = ?";

        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1,club_id);
        boolean rowDeleted = stmt.executeUpdate() > 0;
        con.close();
        return rowDeleted;
    }
}



