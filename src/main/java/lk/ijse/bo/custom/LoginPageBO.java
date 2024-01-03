package lk.ijse.bo.custom;

import java.sql.SQLException;

public interface LoginPageBO {

    public String getUserIdByHash(String sha1Hex) throws SQLException;


}
