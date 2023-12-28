package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    public static List<RecipeDto> findRecipeByIds(String enteredId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sqlQuery = "SELECT * FROM recipe WHERE recipe_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, enteredId);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<RecipeDto> recipes = new ArrayList<>();

        while (resultSet.next()) {
            RecipeDto recipe = new RecipeDto();
            recipe.setRecipe_id(resultSet.getString("recipe_id"));
            recipe.setRecipe_name(resultSet.getString("recipe_name"));
            recipe.setIngredient_name(resultSet.getString("ingredient_name"));

            recipes.add(recipe);
        }

        // Note: Do not close the connection, preparedStatement, or resultSet here
        //It might cause errors

        return recipes;
    }


    public void addIdsToWishlist(String recipeId, String recipeName) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "INSERT INTO Wishlist (user_id, recipe_name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipeId);
            preparedStatement.setString(2, recipeName);
            preparedStatement.executeUpdate();
        } finally {
            // Close resources except the connection in a finally block to ensure they're closed properly
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            // Do not close the connection here
        }
    }
}
