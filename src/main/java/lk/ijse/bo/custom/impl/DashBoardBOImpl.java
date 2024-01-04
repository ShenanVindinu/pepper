package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RecipeDAO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public class DashBoardBOImpl implements DashBoardBO {

    RecipeDAO recipeDAO = (RecipeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECIPE);


    public List<RecipeDto> findRecipesByIngredient(String ingredient) throws SQLException, ClassNotFoundException {
        return recipeDAO.findRecipesByIngredients(ingredient);
    }

    public void addRecipeToWishlist(String recipeId) throws SQLException, ClassNotFoundException {
        recipeDAO.add(recipeId);
    }

}
