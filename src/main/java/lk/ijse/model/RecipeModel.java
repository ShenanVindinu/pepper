package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {



    public static List<RecipeDto> findRecipesByIngredients(String enteredIngredients) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String[] ingredientArray = enteredIngredients.split(",\\s*"); // Split by comma

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM recipe WHERE ");
        for (int i = 0; i < ingredientArray.length; i++) {
            if (i > 0) {
                queryBuilder.append(" OR ");
            }
            queryBuilder.append("ingredient_name LIKE ?");
        }

        PreparedStatement pstm = connection.prepareStatement(queryBuilder.toString());

        for (int i = 0; i < ingredientArray.length; i++) {
            pstm.setString(i + 1, "%" + ingredientArray[i] + "%");
        }

        ResultSet resultSet = pstm.executeQuery();

        List<RecipeDto> recipes = new ArrayList<>();

        while (resultSet.next()) {
            String recipe_id = resultSet.getString("recipe_id");
            String recipe_name = resultSet.getString("recipe_name");
            String ingredient_name = resultSet.getString("ingredient_name");

            RecipeDto dto = new RecipeDto(recipe_id, recipe_name, ingredient_name);
            recipes.add(dto);
        }

        return recipes;
    }

    public static List<RecipeDto> findRecipeByIds(String enteredId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        // Assuming RecipeDto has fields recipe_id, recipe_name, ingredient_name, adjust the query accordingly
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

        // Close resources
        resultSet.close();
        preparedStatement.close();
        connection.close();

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
            // Close resources in a finally block to ensure they're closed properly
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
