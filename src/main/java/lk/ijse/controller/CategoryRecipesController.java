package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.dto.RecipeDto;

public class CategoryRecipesController {

    @FXML
    private TableColumn<RecipeDto, String > ingredients;

    @FXML
    private TableColumn<RecipeDto, String > recipe_id;

    @FXML
    private TableColumn<RecipeDto, String > recipe_name;

    public TableView<RecipeDto> getRecipesTableView() {
        return null;
    }
}
