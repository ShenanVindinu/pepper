package lk.ijse.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.bo.custom.ProfileBO;
import lk.ijse.bo.custom.impl.ProfileBOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private TableColumn<String, String> ingredientColumn;

    @FXML
    private TableView<String> ingredientTable;

    @FXML
    private Button LogoutButton;

    @FXML
    private Button WishlistButton;

    @FXML
    private Button addExclude;

    @FXML
    private TextField allergicTextField;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button findRecipe;

    @FXML
    private Button profileButton;

    @FXML
    private Label userName;

    ProfileBO profileBO = new ProfileBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            // Fetch all allergy ingredients from the database
            ObservableList<String> allergyIngredients = FXCollections.observableArrayList(profileBO.getAllergicIngredients());

            // Setting the retrieved data to the table
            ingredientTable.setItems(allergyIngredients);
            ingredientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void addToExclusionsButton(ActionEvent event) throws SQLException {

        String ingredient = allergicTextField.getText();

        // Calling method in ProfileModel to add the ingredient to the exclusion list
        profileBO.excludeIngredient(ingredient);

        // Fetch updated allergy table data
        ObservableList<String> allergyIngredients = FXCollections.observableArrayList(profileBO.getAllergicIngredients());

        // Setting the retrieved data to the table
        ingredientTable.setItems(allergyIngredients);
        ingredientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

    }


    @FXML
    void categories(ActionEvent event) throws IOException {
        //change Scene to categories page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/categories_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void findRecipes(ActionEvent event) throws IOException {
        //change Scene to dashboard
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        //change Scene to signin page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void wishlist(ActionEvent event) throws IOException {
        //change Scene to wishlist page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/wish_list_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }


}
