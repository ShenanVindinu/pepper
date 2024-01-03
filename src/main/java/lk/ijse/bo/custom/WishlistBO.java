package lk.ijse.bo.custom;

import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface WishlistBO {

    public List<RecipeDto> getWishlistItems() throws SQLException;

    public void clearWishlist() throws SQLException;

}
