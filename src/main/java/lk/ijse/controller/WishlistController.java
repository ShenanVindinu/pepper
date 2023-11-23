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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.RecipeDto;
import lk.ijse.model.WishListModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class WishlistController {

    @FXML
    private Button LogoutButton;

    @FXML
    private Button WishlistButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button findRecipe;

    @FXML
    private Button printButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button clearWishlistButton;

    @FXML
    private Button addToWishlistButton;

    @FXML
    private TextField addToWishlistTextField;

    @FXML
    private TableColumn<RecipeDto, String> recipeIdColumn;

    @FXML
    private TableColumn<RecipeDto, String> recipeNameColumn;

    @FXML
    private TableColumn<RecipeDto, String> ingredientColumn;

    @FXML
    private TableView<RecipeDto> recipeTable;






    @FXML
    void printReport(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/wishlist_items.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport, //compiled report
                        null,
                        DbConnection.getInstance().getConnection()//database connection
                );

        JasperViewer.viewReport(jasperPrint, false);
    }


    @FXML
    void addToWishlist(ActionEvent event) {
        String recipeIdToAdd = addToWishlistTextField.getText();
        if (!recipeIdToAdd.isEmpty()) {
            try {
                WishListModel wishListModel = new WishListModel();
                RecipeDto addedRecipe = wishListModel.addRecipeToWishlist(recipeIdToAdd);

                if (addedRecipe != null) {
                    // If the recipe was added to the wishlist, update the table
                    ObservableList<RecipeDto> currentItems = recipeTable.getItems();
                    currentItems.add(addedRecipe);
                    recipeTable.setItems(currentItems);
                    showAlert("Recipe added to Wishlist!");
                } else {
                    showAlert("Recipe not found or could not be added to Wishlist.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error occurred while adding recipe to Wishlist.");
            }
        } else {
            showAlert("Please enter a Recipe ID to add to Wishlist.");
        }
    }





    @FXML
    void categories(ActionEvent event) throws IOException {
        //change Scene to categories page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/categories_page.fxml"));
        Stage window = (Stage) LogoutButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void findRecipe(ActionEvent event) throws IOException {
        //change Scene to dashboar
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
    void profile(ActionEvent event) throws IOException {
        //change Scene to profile page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/profile_page.fxml"));
        Stage window = (Stage) profileButton.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void clearWishlist(ActionEvent event) throws SQLException {
        WishListModel wishListModel = new WishListModel();
        wishListModel.clearWishlist();
    }

    private static void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
