package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean saveToDatabase(UserDto userDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user (user_name, sha1_hash, user_id) VALUES (?, ?, ?)",
                userDto.getUser_name(), userDto.getSha1_hash(), userDto.getUser_id());
    }

    @Override
    public String getUserIdByHash(String sha1Hash) throws SQLException {
        Connection connection;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        String userId = null;


        try {
            connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT user_id FROM user WHERE sha1_hash = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, sha1Hash);

            resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getString(1);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
        UserDto userDto = new UserDto();
        userDto.setUser_id(userId);
        return userId;
    }


}
