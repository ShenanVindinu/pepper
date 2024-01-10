package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;

public interface WishListDAO extends CrudDAO<RecipeDto> {

    void clearWishlist() throws SQLException, ClassNotFoundException;


}
