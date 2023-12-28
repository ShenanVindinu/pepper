package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl {


    public List<RecipeDto> findRecipesByIngredients(String enteredIngredients) throws SQLException {
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

    public List<RecipeDto> getIndianFoods() throws SQLException {
        List<RecipeDto> indianFoods = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT recipe.recipe_id, recipe.recipe_name, recipe.ingredient_name " +
                        "FROM recipe " +
                        "INNER JOIN category ON recipe.recipe_id = category.recipe_id " +
                        "WHERE category.category_name = ?")) {

            preparedStatement.setString(1, "Indian");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String recipeId = resultSet.getString("recipe_id");
                    String recipeName = resultSet.getString("recipe_name");
                    String ingredientName = resultSet.getString("ingredient_name");

                    RecipeDto recipe = new RecipeDto(recipeId, recipeName, ingredientName);
                    indianFoods.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return indianFoods;
    }

    public List<RecipeDto> getAmericanFoods() throws SQLException {
        List<RecipeDto> americanFoods = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT recipe.recipe_id, recipe.recipe_name, recipe.ingredient_name " +
                        "FROM recipe " +
                        "INNER JOIN category ON recipe.recipe_id = category.recipe_id " +
                        "WHERE category.category_name = ?")) {

            preparedStatement.setString(1, "American");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String recipeId = resultSet.getString("recipe_id");
                    String recipeName = resultSet.getString("recipe_name");
                    String ingredientName = resultSet.getString("ingredient_name");

                    RecipeDto recipe = new RecipeDto(recipeId, recipeName, ingredientName);
                    americanFoods.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return americanFoods;
    }


    public List<RecipeDto> getSriLankanFoods() throws SQLException {
        List<RecipeDto> sriLankanFoods = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT recipe.recipe_id, recipe.recipe_name, recipe.ingredient_name " +
                            "FROM recipe " +
                            "INNER JOIN category ON recipe.recipe_id = category.recipe_id " +
                            "WHERE category.category_name = ?"
            );

            preparedStatement.setString(1, "Sri Lankan");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String recipeId = resultSet.getString("recipe_id");
                String recipeName = resultSet.getString("recipe_name");
                String ingredientName = resultSet.getString("ingredient_name");

                RecipeDto recipe = new RecipeDto(recipeId, recipeName, ingredientName);
                sriLankanFoods.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources except the connection
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Do not close the connection here
        }

        return sriLankanFoods;
    }


    public List<RecipeDto> getThaiFoods() throws SQLException {
        List<RecipeDto> thaiFoods = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT recipe.recipe_id, recipe.recipe_name, recipe.ingredient_name " +
                        "FROM recipe " +
                        "INNER JOIN category ON recipe.recipe_id = category.recipe_id " +
                        "WHERE category.category_name = ?")) {

            preparedStatement.setString(1, "Thai"); // Adjust to the appropriate category name

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String recipeId = resultSet.getString("recipe_id");
                    String recipeName = resultSet.getString("recipe_name");
                    String ingredientName = resultSet.getString("ingredient_name");

                    RecipeDto recipe = new RecipeDto(recipeId, recipeName, ingredientName);
                    thaiFoods.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return thaiFoods;
    }


}
