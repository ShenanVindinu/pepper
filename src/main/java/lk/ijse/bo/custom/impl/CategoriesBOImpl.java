package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CategoriesBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RecipeDAO;
import lk.ijse.dao.custom.impl.RecipeDAOImpl;
import lk.ijse.dto.RecipeDto;

import java.sql.SQLException;
import java.util.List;

public class CategoriesBOImpl implements CategoriesBO {

    RecipeDAO recipeDAO = (RecipeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECIPE);

    public List<RecipeDto> getFoods(String category) throws SQLException {

        switch (category) {
            case "Indian" :
                return recipeDAO.getFoods("Indian");
            case "American" :
                return recipeDAO.getFoods("American");
            case "Thai" :
                return recipeDAO.getFoods("Thai");
            case "Sri Lankan" :
                return recipeDAO.getFoods("Sri Lankan");
            default:
                return null;
        }

    }






}
