package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean saveToDatabase(UserDto userDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user (user_name, sha1_hash, user_id) VALUES (?, ?, ?)",
                userDto.getUser_name(), userDto.getSha1_hash(), userDto.getUser_id());
    }

    @Override
    public String getUserIdByHash(String sha1Hash) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String userId = null;

        resultSet = SQLUtil.execute("SELECT user_id FROM user WHERE sha1_hash = ?", sha1Hash);

        if (resultSet.next()) {
            userId = resultSet.getString(1);
        }

        return userId;
    }


}
