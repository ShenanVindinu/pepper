package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WishListModel {


    public void clearWishlist() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            // Assuming your wishlist table is named "wishlist_table"
            String sql = "DELETE FROM wish_list";

            statement = connection.prepareStatement(sql);
            int rowsAffected = statement.executeUpdate();

            System.out.println(rowsAffected + " rows deleted from the wishlist table");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}
