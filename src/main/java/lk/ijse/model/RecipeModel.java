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

}
