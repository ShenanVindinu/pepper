package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProfileDAO;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOImpl implements ProfileDAO {



    public void add(String ingredient) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();


        // Begin transaction
        connection.setAutoCommit(false);

        // Step 1: Add ingredient to exclusion list
        SQLUtil.execute("INSERT INTO allergy (ingredient) VALUES (?)", ingredient);
        System.out.println("Ingredient added to exclusions: " + ingredient);


        // Step 2: Remove recipes with similar ingredient name
        SQLUtil.execute("DELETE FROM recipe WHERE ingredient_name LIKE ?", ingredient);

        // Commit the transaction if everything is successful
        connection.commit();

        // Rollback the transaction in case of any error
        connection.rollback();

        // Always set autocommit back to true (for safety)
        connection.setAutoCommit(true);

    }


    public List<String> get() throws SQLException, ClassNotFoundException {
        List<String> ingredients = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT ingredient FROM allergy");

        while (resultSet.next()) {
            ingredients.add(resultSet.getString("ingredient"));
        }

        return ingredients;
    }


    public List<String> getFoods(String category) throws SQLException {
        return null;
    }

}
