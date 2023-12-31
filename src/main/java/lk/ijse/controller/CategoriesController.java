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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CategoriesBO;
import lk.ijse.dto.RecipeDto;
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
    private TableView<RecipeDto> recipeTable;

    @FXML
    private TableColumn<RecipeDto, String> recipeIdColumn;

    @FXML
    private TableColumn<RecipeDto, String> recipeNameColumn;

    @FXML
    private TableColumn<RecipeDto, String> ingredientColumn;



    CategoriesBO categoriesBO = (CategoriesBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CATEGORIES);


    @FXML
    void IndianFoods(ActionEvent event) {
        clearTable();

        try {
            List<RecipeDto> indianFoods = categoriesBO.getFoods("Indian");

            ObservableList<RecipeDto> observableList = FXCollections.observableArrayList(indianFoods);

            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            recipeTable.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void americanFoods(ActionEvent event) {
        clearTable();

        try {
            List<RecipeDto> americanFoods = categoriesBO.getFoods("American");

            ObservableList<RecipeDto> observableList = FXCollections.observableArrayList(americanFoods);

            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            recipeTable.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void srilankanFoods(ActionEvent event) {
        clearTable();

        try {
            List<RecipeDto> sriLankanFoods = categoriesBO.getFoods("Sri Lankan");

            ObservableList<RecipeDto> observableList = FXCollections.observableArrayList(sriLankanFoods);

            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));


            recipeTable.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void thaiFoods(ActionEvent event) {

        try {
            List<RecipeDto> thaiFoodsList = categoriesBO.getFoods("Thai");
            clearTable();

            ObservableList<RecipeDto> data = FXCollections.observableArrayList(thaiFoodsList);

            recipeTable.setItems(data);
            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


    // Method to clear the table
    private void clearTable() {
        recipeTable.getItems().clear();
        recipeIdColumn.getColumns().clear();
        recipeNameColumn.getColumns().clear();
        ingredientColumn.getColumns().clear();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        //change Scene to signing page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

}
