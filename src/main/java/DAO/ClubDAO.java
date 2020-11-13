package DAO;

import Models.Club;

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

                Club club = new Club(club_id, club_name, club_description, club_avatar);

                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubs;
    }
}
