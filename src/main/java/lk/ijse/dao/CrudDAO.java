package lk.ijse.dao;


import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public List<T> getFoods(String category) throws SQLException;
    public void add(String c) throws SQLException, ClassNotFoundException;
    public List<T> get() throws SQLException;

}
