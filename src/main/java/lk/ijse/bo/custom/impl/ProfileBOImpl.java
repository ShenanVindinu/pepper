package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ProfileBO;
import lk.ijse.dao.custom.ProfileDAO;
import lk.ijse.dao.custom.impl.ProfileDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ProfileBOImpl implements ProfileBO {

    ProfileDAOImpl profileDAO = new ProfileDAOImpl();

    public void excludeIngredient(String ingredient) throws SQLException, ClassNotFoundException {
        profileDAO.add(ingredient);
    }


    public List<String> getAllergicIngredients() throws SQLException, ClassNotFoundException {
        return profileDAO.get();
    }


}
