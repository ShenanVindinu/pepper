package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface WishlistBO extends SuperBO {

    public List<RecipeDto> getWishlistItems() throws SQLException, ClassNotFoundException;

    public void clearWishlist() throws SQLException, ClassNotFoundException;

}
