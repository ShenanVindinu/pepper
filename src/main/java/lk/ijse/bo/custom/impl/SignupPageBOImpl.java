package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignupPageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public class SignupPageBOImpl implements SignupPageBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    public boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDAO.saveToDatabase(userDto);
    }


}
