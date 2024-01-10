package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {

    boolean saveToDatabase(UserDto userDto) throws SQLException, ClassNotFoundException;
    String getUserIdByHash(String sha1Hash) throws SQLException, ClassNotFoundException;

}
