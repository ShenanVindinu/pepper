package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface WishListDAO extends CrudDAO<RecipeDto> {

    public void clearWishlist() throws SQLException;


}
