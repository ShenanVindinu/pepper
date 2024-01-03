package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProfileDAO;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
//           String insertQuery = "INSERT INTO allergy (ingredient) VALUES (?)";
//           try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//               preparedStatement.setString(1, ingredient);
//               preparedStatement.executeUpdate();
        SQLUtil.execute("INSERT INTO allergy (ingredient) VALUES (?)", ingredient);
        System.out.println("Ingredient added to exclusions: " + ingredient);


        // Step 2: Remove recipes with similar ingredient name
//        String deleteQuery = "DELETE FROM recipe WHERE ingredient_name LIKE ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
//            preparedStatement.setString(1, "%" + ingredient + "%");
//            int rowsAffected = preparedStatement.executeUpdate();
        SQLUtil.execute("DELETE FROM recipe WHERE ingredient_name LIKE ?", ingredient);

        // Commit the transaction if everything is successful
        connection.commit();

        // Rollback the transaction in case of any error
        connection.rollback();

        // Always set autocommit back to true (for safety)
        connection.setAutoCommit(true);

        }







    public List<String> get() throws SQLException {
        List<String> ingredients = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT ingredient FROM allergy";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ingredients.add(resultSet.getString("ingredient"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }


    public List<String> getFoods(String category) throws SQLException {
        return null;
    }

}