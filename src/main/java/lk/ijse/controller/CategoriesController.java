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
import lk.ijse.dto.RecipeDto;
import lk.ijse.model.RecipeModel;

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





    @FXML
    void IndianFoods(ActionEvent event) {
        RecipeModel recipeModel = new RecipeModel();
        clearTable();

        try {
            List<RecipeDto> indianFoods = recipeModel.getIndianFoods();

            ObservableList<RecipeDto> observableList = FXCollections.observableArrayList(indianFoods);

            // Assuming recipeTable is the TableView and columns are recipeIdColumn, recipeNameColumn, and ingredientColumn
            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            recipeTable.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }


    @FXML
    void americanFoods(ActionEvent event) throws SQLException {
        RecipeModel recipeModel = new RecipeModel();
        clearTable();

        try {
            List<RecipeDto> americanFoods = recipeModel.getAmericanFoods();

            ObservableList<RecipeDto> observableList = FXCollections.observableArrayList(americanFoods);

            // Assuming recipeTable is the TableView and columns are recipeIdColumn, recipeNameColumn, and ingredientColumn
            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            recipeTable.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }


    @FXML
    void srilankanFoods(ActionEvent event) throws SQLException, IOException {

        RecipeModel recipeModel = new RecipeModel();
        clearTable();

        try {
            List<RecipeDto> sriLankanFoods = recipeModel.getSriLankanFoods();

            ObservableList<RecipeDto> observableList = FXCollections.observableArrayList(sriLankanFoods);

            // Assuming recipeTable is the TableView and columns are recipeIdColumn, recipeNameColumn, and ingredientColumn
            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));


            recipeTable.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }


    @FXML
    void thaiFoods(ActionEvent event) {
        RecipeModel recipeModel = new RecipeModel();

        try {
            List<RecipeDto> thaiFoodsList = recipeModel.getThaiFoods();
            clearTable();

            ObservableList<RecipeDto> data = FXCollections.observableArrayList(thaiFoodsList);

            recipeTable.setItems(data);
            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
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
        //change Scene to signin page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

}
