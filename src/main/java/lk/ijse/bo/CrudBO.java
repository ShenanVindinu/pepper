package lk.ijse.bo;


import java.sql.SQLException;
import java.util.List;

public interface CrudBO<T> extends SuperBO {

    public List<T> getFoods(String category) throws SQLException;

}
