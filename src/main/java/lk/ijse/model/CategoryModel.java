package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    public static List<RecipeDto> getSriLankanFoods() throws SQLException {
        List<RecipeDto> sriLankanFoodsData = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sqlQuery = "SELECT r.recipe_id, r.recipe_name, r.ingredient_name " +
                    "FROM recipe r " +
                    "JOIN category c ON r.recipe_id = c.recipe_id " +
                    "WHERE c.category_name = 'Sri Lankan'";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String recipeId = resultSet.getString("recipe_id");
                String recipeName = resultSet.getString("recipe_name");
                String ingredientName = resultSet.getString("ingredient_name");

                RecipeDto recipeDto = new RecipeDto(recipeId, recipeName, ingredientName);
                sriLankanFoodsData.add(recipeDto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sriLankanFoodsData;
    }
}
