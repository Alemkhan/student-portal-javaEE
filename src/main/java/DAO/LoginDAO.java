package DAO;

import java.sql.SQLException;

public interface LoginDAO<T> {

    T getForLogin(String email, String password) throws SQLException;

}
