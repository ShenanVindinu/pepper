package lk.ijse.bo.custom;

import java.sql.SQLException;
import java.util.List;

public interface ProfileBO {


    public void excludeIngredient(String ingredient) throws SQLException;
    public List<String> getAllergicIngredients() throws SQLException;



}
