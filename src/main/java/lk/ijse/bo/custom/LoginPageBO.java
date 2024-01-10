package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface LoginPageBO extends SuperBO {

    String getUserIdByHash(String sha1Hex) throws SQLException, ClassNotFoundException;


}
