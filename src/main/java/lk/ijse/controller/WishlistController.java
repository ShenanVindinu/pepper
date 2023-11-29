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
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class WishlistController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateWishlist();
    }


    public void populateWishlist() {
        try {
            WishListModel wishListModel = new WishListModel();
            List<RecipeDto> wishlistItems = wishListModel.getAllWishlistItems();

            // Clear existing data in columns
            recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            // Populate columns with retrieved data
            if (!wishlistItems.isEmpty()) {
                ObservableList<RecipeDto> data = FXCollections.observableArrayList(wishlistItems);
                recipeTable.setItems(data);
            } else {
                // Handle the case when the wishlist is empty
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
    }







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
