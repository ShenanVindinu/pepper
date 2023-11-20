package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dto.RecipeDto;
import lk.ijse.model.CategoryModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoriesController {

    @FXML
    private Button LogoutButton;

    @FXML
    private Button WishlistButton;

    @FXML
    private Button americanFoodsButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button findRecipe;

    @FXML
    private Button indianFoodsButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button sriLankanFoodButton;

    @FXML
    private Button thaiFoodsButton;

    @FXML
    private ImageView wishlistButton;

    @FXML
    void IndianFoods(ActionEvent event) {

    }

    @FXML
    void americanFoods(ActionEvent event) {

    }



    @FXML
    void srilankanFoods(ActionEvent event) throws SQLException, IOException {
        // Get Sri Lankan foods data from CategoryModel
        List<RecipeDto> sriLankanFoodsData = CategoryModel.getSriLankanFoods();

        // Create CategoryRecipesController instance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("path_to_CategoryRecipes.fxml"));
        Parent root = loader.load();
        CategoryRecipesController categoryRecipesController = loader.getController();

        // Populate the table in CategoryRecipesController
        TableView<RecipeDto> recipesTableView = categoryRecipesController.getRecipesTableView();
        populateTable(recipesTableView, sriLankanFoodsData);


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void populateTable(TableView<RecipeDto> recipesTableView, List<RecipeDto> sriLankanFoodsData) {

    }

    @FXML
    void thaiFoods(ActionEvent event) {

    }

    @FXML
    void categories(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        //change Scene to signin page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        //change Scene to profile page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/profile_page.fxml"));
        Stage window = (Stage) wishlistButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void recipe(ActionEvent event) throws IOException {
        //change Scene to dashboard
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void wishlist(MouseEvent event) throws IOException {
        //change Scene to wishlist page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/wish_list_page.fxml"));
        Stage window = (Stage) wishlistButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

}
