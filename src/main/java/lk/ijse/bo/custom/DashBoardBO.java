package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface DashBoardBO extends SuperBO {

    List<RecipeDto> findRecipesByIngredient(String ingredient) throws SQLException, ClassNotFoundException;
    void addRecipeToWishlist(String recipeId) throws SQLException, ClassNotFoundException;


}
