package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.WishListDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishListDAOImpl implements WishListDAO {


    @Override
    public void clearWishlist() throws SQLException {
        Connection connection;
        PreparedStatement statement;

        connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM wish_list";

        statement = connection.prepareStatement(sql);
        int rowsAffected = statement.executeUpdate();

        System.out.println(rowsAffected + " rows deleted from the wishlist table");
    }

    @Override
    public void add(String recipeId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Insert the recipe ID into the wishlist table
            String insertQuery = "INSERT INTO wish_list (recipe_id) VALUES (?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<RecipeDto> get() throws SQLException {
        List<RecipeDto> wishlistItems = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT recipe.recipe_id, recipe.recipe_name, recipe.ingredient_name " +
                    "FROM wish_list " +
                    "INNER JOIN recipe ON wish_list.recipe_id = recipe.recipe_id";

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RecipeDto wishlistItem = new RecipeDto(
                        resultSet.getString("recipe_id"),
                        resultSet.getString("recipe_name"),
                        resultSet.getString("ingredient_name")
                );
                wishlistItems.add(wishlistItem);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return wishlistItems;
    }

    @Override
    public List<RecipeDto> getFoods(String category) throws SQLException {
        return null;
    }



}
