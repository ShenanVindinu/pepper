package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;

import java.sql.SQLException;

public interface ProfileDAO extends CrudDAO {

    public void add(String c) throws SQLException;

}
