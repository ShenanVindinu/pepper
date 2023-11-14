package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableColumn<?, ?> ingredients;

    @FXML
    private Button profileButton;

    @FXML
    private TableColumn<?, ?> recipe_id;

    @FXML
    private TableColumn<?, ?> recipe_name;

    @FXML
    private TableView<?> recipes;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

}
