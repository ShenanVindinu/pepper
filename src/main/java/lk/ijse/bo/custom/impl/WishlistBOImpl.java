package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.WishlistBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.WishListDAO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public class WishlistBOImpl implements WishlistBO {

    WishListDAO wishListDAO = (WishListDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WISHLIST);

    public List<RecipeDto> getWishlistItems() throws SQLException, ClassNotFoundException {
        return wishListDAO.get();
    }

    public void clearWishlist() throws SQLException, ClassNotFoundException {
        wishListDAO.clearWishlist();
    }



}
