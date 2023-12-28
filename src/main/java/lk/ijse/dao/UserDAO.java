package lk.ijse.dao;

import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface UserDAO {

    public boolean saveToDatabase(UserDto userDto);
    public String getUserIdByHash(String sha1Hash) throws SQLException;

}
