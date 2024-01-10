package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface ProfileBO extends SuperBO {


    void excludeIngredient(String ingredient) throws SQLException, ClassNotFoundException;
    List<String> getAllergicIngredients() throws SQLException, ClassNotFoundException;



}
