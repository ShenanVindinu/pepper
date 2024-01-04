package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginPageBO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;

public class LoginPageBOImpl implements LoginPageBO {
    UserDAO userDAO = new UserDAOImpl();


    public String getUserIdByHash(String sha1Hex) throws SQLException, ClassNotFoundException {
        return userDAO.getUserIdByHash(sha1Hex);
    }


}
