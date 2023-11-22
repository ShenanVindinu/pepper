package lk.ijse.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<RecipeDto, String> recipe_ids;

    @FXML
    private TableColumn<RecipeDto, String> recipe_names;

    @FXML
    private TableColumn<RecipeDto, String> ingredient;

    @FXML
    private TableView<RecipeDto> recipes;

    @FXML
    private Button clearWishlistButton;



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

    public void populateTable(List<RecipeDto> filteredRecipes) {

        if (!filteredRecipes.isEmpty()) {
            recipe_ids.setCellValueFactory(new PropertyValueFactory<>("recipe_id"));
            recipe_names.setCellValueFactory(new PropertyValueFactory<>("recipe_name"));
            ingredient.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));

            recipe_ids.setText("Recipe ID");
            recipe_names.setText("Recipe Name");
            ingredient.setText("Ingredients");

        } else {
            System.out.println("No Recipes Found");
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
