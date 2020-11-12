package DAO;

import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements DAO<User>, LoginDAO<User>{

    private Connection con;

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getForLogin(String email, String password) throws SQLException {

        con = DatabaseConnection.createConnection();
        String sql = "select u.user_id, u.first_name, u.last_name, u.email, u.role_id, " +
                "u.major_id, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id " +
                "where email = ? and password = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {

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

            if (role_name.equals("admin")) {
                return new Admin(user_id,fname,lname,localEmail,role);
            } else {
                return new Student(user_id,fname,lname,localEmail,role,major);
            }

        }
        return null;
    }

}
