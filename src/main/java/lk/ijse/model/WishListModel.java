package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public RecipeDto addRecipeToWishlist(String recipeId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RecipeDto recipe = null;

        try {
            // Get the database connection outside the try block
            connection = DbConnection.getInstance().getConnection();

            // Check if the recipe with the given ID exists
            String selectQuery = "SELECT recipe_id, recipe_name, ingredient_name FROM recipe WHERE recipe_id = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, recipeId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Recipe found, create a RecipeDto object
                String foundRecipeId = resultSet.getString("recipe_id");
                String recipeName = resultSet.getString("recipe_name");
                String ingredientName = resultSet.getString("ingredient_name");

                recipe = new RecipeDto(foundRecipeId, recipeName, ingredientName);

                // Insert the recipe into the wishlist table (assuming a table named 'wishlist')
                String insertQuery = "INSERT INTO wishlist (recipe_id, recipe_name, ingredient_name) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, foundRecipeId);
                preparedStatement.setString(2, recipeName);
                preparedStatement.setString(3, ingredientName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        } finally {
            // Close resources in reverse order
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return recipe;
    }


}
