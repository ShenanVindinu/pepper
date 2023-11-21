package lk.ijse.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import javafx.event.ActionEvent;
import lk.ijse.model.UserModel;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.SQLException;


public class LoginPageController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;


    public LoginPageController() {}

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
        String username = userName.getText();
        String password = passwordField.getText();

        // passing 'username' and 'password' to your login logic
        boolean isLoggedIn = performLogin(username, password);

        // logic to handle the result of the login attempt
        if (isLoggedIn) {
            // Successful login
            System.out.println("Login successful!");
            showAlert("Login Successful", "Welcome! "+username);

            //change Scene to dashboard
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_page.fxml"));
            Stage window = (Stage) userName.getScene().getWindow();
            window.setScene(new Scene(rootNode, 1200,800));

        } else {
            // Failed login
            System.out.println("Login failed. Please check your credentials.");
            showAlert("Login Successful", "Password doesn't match or you are not Registered.\nPlease try again!");
        }
    }


    private boolean performLogin(String username, String password) throws SQLException {

        String combinedString = username+password;

        //generating hashcode
        String sha1Hex = DigestUtils.sha1Hex(combinedString);
        //System.out.println("SHA-1 hash of '" + combinedString + "': " + sha1Hex);

        String userIdByHash = null;

        userIdByHash = UserModel.getUserIdByHash(sha1Hex);

        if ( userIdByHash != null ) { return true; }

        else { return false; }

    }

    @FXML
    public void toggleSignupLogin() throws IOException {
        //change Scene to signup page
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/signup_page.fxml"));
        Stage window = (Stage) userName.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
