package lk.ijse.dao;

import lk.ijse.dao.custom.impl.ProfileDAOImpl;
import lk.ijse.dao.custom.impl.RecipeDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dao.custom.impl.WishListDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        PROFILE,RECIPE,USER,WISHLIST
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case PROFILE:
                return new ProfileDAOImpl();
            case RECIPE:
                return new RecipeDAOImpl();
            case USER:
                return new UserDAOImpl();
            case WISHLIST:
                return new WishListDAOImpl();
            default:
                return null;
        }
    }
}
