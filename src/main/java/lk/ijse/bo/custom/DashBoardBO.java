package lk.ijse.bo.custom;

import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface DashBoardBO {

    public List<RecipeDto> findRecipesByIngredient(String ingredient) throws SQLException;
    public void addRecipeToWishlist(String ingredient) throws SQLException;


}
