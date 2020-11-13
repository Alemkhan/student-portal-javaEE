package DAO;

import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO implements DAO<User>, LoginDAO<User>{

    private Connection con = null;

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public User getForLogin(String email, String password) throws SQLException {
        con = DatabaseConnection.createConnection();
        String sql = "select u.user_id, u.first_name, u.last_name, u.email, u.role_id, " +
                "r.role_name, u.major_id, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id " +
                "where email = ? and password = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            int user_id = resultSet.getInt("user_id");
            String fname = resultSet.getString("first_name");
            String lname = resultSet.getString("last_name");
            String localEmail = resultSet.getString("email");
            int role_id = resultSet.getInt("role_id");
            String role_name = resultSet.getString("role_name");
            int major_id = resultSet.getInt("major_id");
            String major_name = resultSet.getString("major_name");
            Role role = new Role(role_id, role_name);
            Major major = new Major(major_id, major_name);

            con.close();
            if (role_name.equals("admin")) {
                return new Admin(user_id,fname,lname,localEmail,role);
            } else {
                return new Student(user_id, fname, lname, localEmail, role, major, getStudentClubs(user_id));
            }
        }
        return null;
    }

    private HashMap<Club, String> getStudentClubs(int student_id) throws SQLException {
        HashMap<Club, String> clubMap = new HashMap<>();

        String sql = "select cm.club_id, c.club_name, c.description, c.avatar, cr.club_role_name " +
                "from club_managers cm JOIN clubs c ON cm.club_id = c.club_id " +
                "JOIN club_roles cr ON cm.club_role_id = cr.club_role_id " +
                "WHERE cm.user_id = ?;";

        con = DatabaseConnection.createConnection();
        PreparedStatement stmt1 = con.prepareStatement(sql);
        stmt1.setInt(1,student_id);
        ResultSet rs = stmt1.executeQuery();

        while (rs.next()) {
            int club_id = rs.getInt("club_id");
            String club_name = rs.getString("club_name");
            String description = rs.getString("description");
            String avatar = rs.getString("avatar");
            String club_role_name = rs.getString("club_role_name");
            Club club = new Club(club_id,club_name,description,avatar);
            clubMap.put(club, club_role_name);

        }
        con.close();
        return clubMap;
    }

}
