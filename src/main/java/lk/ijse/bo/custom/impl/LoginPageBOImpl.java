package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginPageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;

import java.sql.SQLException;

public class LoginPageBOImpl implements LoginPageBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    public String getUserIdByHash(String sha1Hex) throws SQLException, ClassNotFoundException {
        return userDAO.getUserIdByHash(sha1Hex);
    }


}
