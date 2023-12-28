package lk.ijse.dao;

import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface WishListDAO {

    public void clearWishlist() throws SQLException;
    public void addRecipeToWishlist(String recipeId) throws SQLException;
    public List<RecipeDto> getAllWishlistItems() throws SQLException;


}
