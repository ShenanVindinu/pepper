package lk.ijse.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.model.ProfileModel;
import lk.ijse.model.RecipeModel;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileController {

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


    @FXML
    void addToExclusionsButton(ActionEvent event) throws SQLException {
        //instances
        ProfileModel profileModel = new ProfileModel();
        RecipeModel recipeModel = new RecipeModel();

        String ingredient = allergicTextField.getText();

        // Call method in ProfileModel to add the ingredient to the exclusion list
        profileModel.addToExclusionsAndRemoveRecipes(ingredient);

        // Fetch updated allergy table data
        ObservableList<String> allergyIngredients = FXCollections.observableArrayList(profileModel.getAllergyIngredients());

        // Set the retrieved data to the table
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
