package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface ProfileBO extends SuperBO {


    public void excludeIngredient(String ingredient) throws SQLException, ClassNotFoundException;
    public List<String> getAllergicIngredients() throws SQLException, ClassNotFoundException;



}
