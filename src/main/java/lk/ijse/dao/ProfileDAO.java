package lk.ijse.dao;

import java.sql.SQLException;
import java.util.List;

public interface ProfileDAO {

    public void addToExclusionsAndRemoveRecipes(String ingredient) throws SQLException;
    public List<String> getAllergyIngredients() throws SQLException;

}
