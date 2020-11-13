package DAO;

import Models.Club;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClubDAO implements DAO<Club>{

    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    @Override
    public Club get(int id) {
        return null;
    }

    @Override
    public ArrayList<Club> getAll() throws SQLException {

        ArrayList<Club> clubs = new ArrayList<>();

        try {
            con = DatabaseConnection.createConnection();
            sql = "select * from clubs";
            stmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            while (resultSet.next()){

                int club_id = resultSet.getInt("club_id");
                String club_name = resultSet.getString("club_name");
                String club_description = resultSet.getString("description");
                String club_avatar = resultSet.getString("avatar");
                int owner_id = resultSet.getInt("owner_id");

                Club club = new Club(club_id, club_name, club_description, club_avatar, owner_id);

                clubs.add(club);
            }
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
        boolean rowInserted = stmt.executeUpdate() > 0;
        return rowInserted;
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
        stmt.close();
        con.close();
        if (rowInserted && rowUpdated) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateClub(int ownerId, Club club) throws SQLException {
        String sql = "UPDATE clubs SET club_name = ?, description = ?"; // AVATAR is in discuss !!!!!!
        sql += " WHERE club_id = ? and owner_id = ?";
        con = DatabaseConnection.createConnection();

        stmt = con.prepareStatement(sql);
        stmt.setString(1, club.getClub_name());
        stmt.setString(2, club.getDescription());
        stmt.setInt(3, club.getClub_id());
        stmt.setInt(4, ownerId);
        boolean rowUpdated = stmt.executeUpdate() > 0;
        stmt.close();
        con.close();
        return rowUpdated;
    }

//      !!!! -----------   NEED TO FIX IN SQL TABLES TO ON DELETE CASCADE ------------- !!!!!
//    public boolean deleteClub(Club club, User user) throws SQLException {
//        if (user.getRole().getRole_id() == 1) {
//            String sql = "DELETE FROM clubs, club_managers, events, news where club_id = ?";
//
//            con = DatabaseConnection.createConnection();
//
//            stmt = con.prepareStatement(sql);
//            stmt = setInt(1, club.getClub_id());
//
//            boolean rowDeleted = stmt.executeUpdate() > 0;
//            stmt = close();
//            con.close();
//            return rowDeleted;
//        }
//        else {
//            return false;
//        }
//    }
}
