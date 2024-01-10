package lk.ijse.dao;


import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    List<T> getFoods(String category) throws SQLException;
    void add(String c) throws SQLException, ClassNotFoundException;
    List<T> get() throws SQLException, ClassNotFoundException;

}
