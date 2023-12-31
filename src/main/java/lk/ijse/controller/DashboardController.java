package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dto.RecipeDto;
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
    private Button profileButton;

    @FXML
    private TableColumn<RecipeDto, String> recipe_id;

    @FXML
    private TableColumn<RecipeDto, String> recipe_name;

    @FXML
    private TableColumn<RecipeDto, String> ingredients;

    @FXML
    private TableView<RecipeDto> recipes;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;


    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DASHBOARD);




    @FXML
    void searchRecipe(ActionEvent event) throws SQLException, ClassNotFoundException {
        String enteredIngredients = searchBar.getText(); // Get entered ingredients



        // Fetch recipes from the database based on entered ingredients
        List<RecipeDto> filteredRecipes = dashBoardBO.findRecipesByIngredient(enteredIngredients);

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
            recipes.setRowFactory(tv -> {
                TableRow<RecipeDto> row = new TableRow<>();
                row.setOnMouseClicked(event2 -> {
                    if (event2.getClickCount() == 1 && !row.isEmpty()) {
                        RecipeDto selectedRecipe = row.getItem();
                        String recipeId = selectedRecipe.getRecipe_id();
                        try {
                           dashBoardBO.addRecipeToWishlist(recipeId);
                            showAlert("Added to Wishlist");
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.print("recipe " + recipeId + "Added");
                    }

                });
                return row;
            });
        } else {
            // Clear the table or display a message indicating no matching recipes
            recipes.getItems().clear();
        }
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

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
