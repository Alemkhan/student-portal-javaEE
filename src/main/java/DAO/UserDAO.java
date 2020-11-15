package DAO;

import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO implements DAO<User>, LoginDAO<User>{

    private Connection con = null;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        
        String sql = "select u.*, r.role_name, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id where role_name != 'admin';";

        try {
            con = DatabaseConnection.createConnection();
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int user_id = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String localEmail = rs.getString("email");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                int major_id = rs.getInt("major_id");
                String major_name = rs.getString("major_name");
                int year = rs.getInt("year");
                String group_name = rs.getString("group_name");
                Role role = new Role(role_id, role_name);
                Major major = new Major(major_id, major_name);

                Student student = new Student(user_id, fname, lname, localEmail, role, major, getStudentClubs(user_id));
                student.setYear(year);
                student.setGroup_name(group_name);
                users.add(student);
            }
            con.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public User getForLogin(String email, String password) throws SQLException {
        con = DatabaseConnection.createConnection();
        String sql = "select u.user_id, u.first_name, u.last_name, u.email, u.role_id, " +
                "r.role_name, u.major_id, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id " +
                "where email = ? and password = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        resultSet = stmt.executeQuery();

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
        con.close();
        return null;
    }

    public HashMap<Club, String> getStudentClubs(int student_id) throws SQLException {
        HashMap<Club, String> clubMap = new HashMap<>();

            String sql = "select cm.club_id, c.club_name, c.description, c.avatar, c.owner_id, u.email, cr.club_role_name " +
                    "from club_managers cm JOIN clubs c ON cm.club_id = c.club_id " +
                    "JOIN club_roles cr ON cm.club_role_id = cr.club_role_id " +
                    "JOIN users u on c.owner_id = u.user_id " +
                "WHERE cm.user_id = ?;";

        con = DatabaseConnection.createConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1,student_id);
        resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            int club_id = resultSet.getInt("club_id");
            String club_name = resultSet.getString("club_name");
            String description = resultSet.getString("description");
            String avatar = resultSet.getString("avatar");
            int owner_id = resultSet.getInt("owner_id");
            String owner_email = resultSet.getString("email");
            String club_role_name = resultSet.getString("club_role_name");
            User owner = new Student();
            owner.setId(owner_id);
            owner.setEmail(owner_email);
            Club club = new Club(club_id,club_name,description,avatar, owner);
            clubMap.put(club, club_role_name);

        }
        con.close();
        return clubMap;
    }

    public ArrayList<User> getCertain(String find) {

        ArrayList<User> users = new ArrayList<>();

        String sql = "select u.*, r.role_name, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id where role_name != 'admin' and u.last_name like ?;";

        try {
            con = DatabaseConnection.createConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, find + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int user_id = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String localEmail = rs.getString("email");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                int major_id = rs.getInt("major_id");
                String major_name = rs.getString("major_name");
                int year = rs.getInt("year");
                String group_name = rs.getString("group_name");
                Role role = new Role(role_id, role_name);
                Major major = new Major(major_id, major_name);

                Student student = new Student(user_id, fname, lname, localEmail, role, major, getStudentClubs(user_id));
                student.setYear(year);
                student.setGroup_name(group_name);
                users.add(student);

            }
            con.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;

    }

    public ArrayList<User> getByMajor(String find) {

        ArrayList<User> users = new ArrayList<>();

        String sql = "select u.*, r.role_name, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id where role_name != 'admin' and m.major_name like ?;";

        try {
            con = DatabaseConnection.createConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, find + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int user_id = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String localEmail = rs.getString("email");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                int major_id = rs.getInt("major_id");
                String major_name = rs.getString("major_name");
                int year = rs.getInt("year");
                String group_name = rs.getString("group_name");
                Role role = new Role(role_id, role_name);
                Major major = new Major(major_id, major_name);

                Student student = new Student(user_id, fname, lname, localEmail, role, major, getStudentClubs(user_id));
                student.setYear(year);
                student.setGroup_name(group_name);
                users.add(student);

            }
            con.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;

    }

    public ArrayList<User> getByYear(String find) {

        ArrayList<User> users = new ArrayList<>();

        String sql = "select u.*, r.role_name, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id where role_name != 'admin' and u.year = ?;";

        try {
            con = DatabaseConnection.createConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(find));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int user_id = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String localEmail = rs.getString("email");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                int major_id = rs.getInt("major_id");
                String major_name = rs.getString("major_name");
                int year = rs.getInt("year");
                String group_name = rs.getString("group_name");
                Role role = new Role(role_id, role_name);
                Major major = new Major(major_id, major_name);

                Student student = new Student(user_id, fname, lname, localEmail, role, major, getStudentClubs(user_id));
                student.setYear(year);
                student.setGroup_name(group_name);
                users.add(student);

            }
            con.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;

    }

    public ArrayList<User> getByGroup(String find) {

        ArrayList<User> users = new ArrayList<>();

        String sql = "select u.*, r.role_name, m.major_name " +
                "from users u join majors m on u.major_id = m.major_id " +
                "join roles r on u.role_id = r.role_id where role_name != 'admin' and u.group_name like ?;";

        try {
            con = DatabaseConnection.createConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, find + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int user_id = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String localEmail = rs.getString("email");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                int major_id = rs.getInt("major_id");
                String major_name = rs.getString("major_name");
                int year = rs.getInt("year");
                String group_name = rs.getString("group_name");
                Role role = new Role(role_id, role_name);
                Major major = new Major(major_id, major_name);

                Student student = new Student(user_id, fname, lname, localEmail, role, major, getStudentClubs(user_id));
                student.setYear(year);
                student.setGroup_name(group_name);
                users.add(student);

            }
            con.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;

    }
}
