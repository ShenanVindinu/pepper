package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dto.RecipeDto;
import lk.ijse.dto.UserDto;
import lk.ijse.model.RecipeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DashboardController {

    @FXML
    private Button LogoutButton;

    @FXML
    private Button WishlistButton;

    @FXML
    private Button addToWishlist;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button findRecipe;

    @FXML
    private TableColumn<RecipeDto, String> ingredients;

    @FXML
    private Button profileButton;

    @FXML
    private TableColumn<RecipeDto, String> recipe_id;

    @FXML
    private TableColumn<RecipeDto, String> recipe_name;

    @FXML
    private TableView<RecipeDto> recipes;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private TextField wishList_ids;



    @FXML
    void searchRecipe(ActionEvent event) throws SQLException {
        String enteredIngredients = searchBar.getText(); // Get entered ingredients

        // Fetch recipes from the database based on entered ingredients
        List<RecipeDto> filteredRecipes = RecipeModel.findRecipesByIngredients(enteredIngredients);

        // Clear existing data in columns
        recipe_id.setCellValueFactory(new PropertyValueFactory<>(""));
        recipe_name.setCellValueFactory(new PropertyValueFactory<>(""));
        ingredients.setCellValueFactory(new PropertyValueFactory<>(""));

        // Populate columns with retrieved data
        if (!filteredRecipes.isEmpty()) {
            RecipeDto firstRecipe = filteredRecipes.get(0); // Assuming there is at least one recipe
            recipe_id.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipe_name.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredients.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            recipe_id.setText("Recipe ID");
            recipe_name.setText("Recipe Name");
            ingredients.setText("Ingredients");

            ObservableList<RecipeDto> data = FXCollections.observableArrayList(filteredRecipes);
            recipes.setItems(data);
        } else {
            // Handle scenario when no recipes are found
            // Clear the table or display a message indicating no matching recipes
            recipes.getItems().clear();
        }
    }

    //
    @FXML
    void addToWishlistTable(ActionEvent event) throws SQLException {
        String enteredRecipeId = wishList_ids.getText();

        //get Current userId
        UserDto userDto = new UserDto();
        String userId = userDto.getUser_id();

        // Fetch recipes from the database based on entered Id
        List<RecipeDto> filteredRecipes = RecipeModel.findRecipeByIds(enteredRecipeId);

        // Instantiate the controller
        WishlistController wishlistController = new WishlistController();
        // Instantiate the model
        RecipeModel recipeModel = new RecipeModel();

        // Populate table in wishList page with given data using the instance
        wishlistController.populateTable(filteredRecipes);


        // Add RecipeId and UserId to Wishlist Table using the instance
        recipeModel.addIdsToWishlist(enteredRecipeId,userId);
    }


    @FXML
    void categories(ActionEvent event) throws IOException {
        //change Scene to categories page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/categories_page.fxml"));
        Stage window = (Stage) searchBar.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        //change Scene to profile page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/profile_page.fxml"));
        Stage window = (Stage) searchBar.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void wishlist(ActionEvent event) throws IOException {
        //change Scene to wishlist page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/wish_list_page.fxml"));
        Stage window = (Stage) searchBar.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        //change Scene to signing page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

}
