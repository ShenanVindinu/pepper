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
import java.io.IOException;


public class LoginPageController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;



    private Stage stage;
    private Scene scene;
    private Parent root;


    public LoginPageController() {}

    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = userName.getText();
        String password = passwordField.getText();

        // Now you can pass 'username' and 'password' to your login logic
        boolean isLoggedIn = performLogin(username, password);

        // Add logic to handle the result of the login attempt
        if (isLoggedIn) {
            // Successful login
            System.out.println("Login successful!");

            //change Scene to dashboard
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_page.fxml"));
            Stage window = (Stage) userName.getScene().getWindow();
            window.setScene(new Scene(rootNode, 1200,800));

        } else {
            // Failed login
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private void switchToDashboard() {

    }



    private boolean performLogin(String username, String password) {
        // Replace this with your actual login logic
        // Example: Check against a database, file, or any authentication mechanism
        return username.equals("shenan") && password.equals("1234");
    }

    @FXML
    public void toggleSignupLogin() {
//        loginPage.setVisible(!loginPage.isVisible());
//        signupPage.setVisible(!signupPage.isVisible());
    }
}
