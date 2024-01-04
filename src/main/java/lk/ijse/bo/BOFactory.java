package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CATEGORIES, DASHBOARD, LOGIN, PROFILE, SIGNUP, WISHLIST
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CATEGORIES:
                return new CategoriesBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case LOGIN:
                return new LoginPageBOImpl();
            case PROFILE:
                return new ProfileBOImpl();
            case SIGNUP:
                return new SignupPageBOImpl();
            case WISHLIST:
                return new WishlistBOImpl();
            default:
                return null;
        }
    }
}
