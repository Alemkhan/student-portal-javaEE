package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {

    T get(int id);

    ArrayList<T> getAll() throws SQLException;

}
