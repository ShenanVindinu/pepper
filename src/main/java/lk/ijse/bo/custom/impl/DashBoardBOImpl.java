package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dao.custom.RecipeDAO;
import lk.ijse.dao.custom.impl.RecipeDAOImpl;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public class DashBoardBOImpl implements DashBoardBO {

    RecipeDAO recipeDAO = new RecipeDAOImpl();


    public List<RecipeDto> findRecipesByIngredient(String ingredient) throws SQLException {
        return recipeDAO.findRecipesByIngredients(ingredient);
    }

    public void addRecipeToWishlist(String recipeId) throws SQLException {
        recipeDAO.add(recipeId);
    }

}
