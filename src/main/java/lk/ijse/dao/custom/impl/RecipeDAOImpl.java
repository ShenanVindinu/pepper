package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.RecipeDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public List<RecipeDto> findRecipesByIngredients(String enteredIngredients) throws SQLException, ClassNotFoundException {
        String[] ingredientArray = enteredIngredients.split(",\\s*");
        List<RecipeDto> recipes = new ArrayList<>();

        for (String ingredient : ingredientArray) {
            String sql = "SELECT * FROM recipe WHERE ingredient_name LIKE ?";
            ResultSet resultSet = SQLUtil.execute(sql, "%" + ingredient + "%");

            while (resultSet.next()) {
                String recipe_id = resultSet.getString("recipe_id");
                String recipe_name = resultSet.getString("recipe_name");
                String ingredient_name = resultSet.getString("ingredient_name");

                RecipeDto dto = new RecipeDto(recipe_id, recipe_name, ingredient_name);
                recipes.add(dto);
            }
        }
        return recipes;
    }

    @Override
    public List<RecipeDto> getFoods(String category) throws SQLException {
        List<RecipeDto> Foods = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT recipe.recipe_id, recipe.recipe_name, recipe.ingredient_name " +
                        "FROM recipe " +
                        "INNER JOIN category ON recipe.recipe_id = category.recipe_id " +
                        "WHERE category.category_name = ?")) {

            preparedStatement.setString(1, category);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String recipeId = resultSet.getString("recipe_id");
                    String recipeName = resultSet.getString("recipe_name");
                    String ingredientName = resultSet.getString("ingredient_name");

                    RecipeDto recipe = new RecipeDto(recipeId, recipeName, ingredientName);
                    Foods.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return Foods;
    }

    @Override
    public void add(String c) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Insert the recipe ID into the wishlist table
            String insertQuery = "INSERT INTO wish_list (recipe_id) VALUES (?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, c);
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
        return null;
    }


}
