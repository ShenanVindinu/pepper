package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    public UserModel() {}

    public static boolean saveToDatabase(UserDto userDto) {
        String username = userDto.getUser_name();
        String sha1_hash = userDto.getSha1_hash();
        String userId = userDto.getUser_id();

        // Use DbConnection to get the connection
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO user (user_name, sha1_hash, user_id) VALUES (?, ?, ?)"
             )) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, sha1_hash);
            preparedStatement.setString(3, userId);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements
        }

        return false;
    }

    public static String getUserIdByHash(String sha1Hash) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT user_id FROM user WHERE sha1_hash = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sha1Hash);

        ResultSet resultSet = pstm.executeQuery();

        String userId = null;
        if (resultSet.next()) {
            userId = resultSet.getString(1);
        }
        return userId;
    }




}

