package lk.ijse.dao;

import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface RecipeDAO {

    public List<RecipeDto> findRecipesByIngredients(String enteredIngredients) throws SQLException;
    public List<RecipeDto> getIndianFoods() throws SQLException;
    public List<RecipeDto> getAmericanFoods() throws SQLException;
    public List<RecipeDto> getSriLankanFoods() throws SQLException;
    public List<RecipeDto> getThaiFoods() throws SQLException;


}
