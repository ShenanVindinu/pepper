package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignupPageBO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public class SignupPageBOImpl implements SignupPageBO {

    UserDAO userDAO = new UserDAOImpl();


    public boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDAO.saveToDatabase(userDto);
    }


}
