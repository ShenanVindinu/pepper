package lk.ijse.bo.custom;

import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface SignupPageBO {


    public boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException;



}
