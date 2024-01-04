package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public interface RecipeDAO extends CrudDAO<RecipeDto> {

    public List<RecipeDto> findRecipesByIngredients(String enteredIngredients) throws SQLException, ClassNotFoundException;

}
