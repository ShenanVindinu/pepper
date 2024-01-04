package lk.ijse.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SignupPageBO;
import lk.ijse.dto.UserDto;
import org.apache.commons.codec.digest.DigestUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



import java.io.IOException;
import java.sql.SQLException;

public class SignupPageController {

    @FXML
    private Button backToLogin;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    private TextField userId;

    SignupPageBO signupPageBO = (SignupPageBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SIGNUP);

    @FXML
    void backToLoginButton(ActionEvent event) throws IOException {
        //change Scene to dashboard
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_page.fxml"));
        Stage window = (Stage) userName.getScene().getWindow();
        window.setScene(new Scene(rootNode, 1200,800));
    }

    @FXML
    void signup(ActionEvent event) throws SQLException, ClassNotFoundException {
        String userName = this.userName.getText();
        String password = this.password.getText();
        String userId = this.userId.getText();

        // Validate username against regex
        if (!validateUsername(userName)) {
            showAlert("Invalid Username", "Username should contain only letters, numbers, and underscores.");
            return; // Stop further processing
        }

        // passing 'username' and 'password' to your login logic
        boolean isSignedUp = performSignup(userName, password, userId);

        if (isSignedUp) {
            showAlert("Signup Successful", "You have successfully signed up!");
        } else {
            showAlert("Signup Failed", "Signup failed. Please try again.");
        }
    }

    private boolean performSignup(String userName, String password, String userId) throws SQLException, ClassNotFoundException {
        //username and password combined to generate hashcode
        String combinedString = userName+password;

        //generating hashcode
        String sha1Hex = DigestUtils.sha1Hex(combinedString);
        //System.out.println("SHA-1 hash of '" + combinedString + "': " + sha1Hex);

        // Creating a UserDto object by passing username and password
        UserDto userDto = new UserDto(userId, userName, sha1Hex);

        return signupPageBO.saveUser(userDto);
    }

    private boolean validateUsername(String username) {
        // Regex pattern allowing only letters, numbers, and underscores
        String regexPattern = "^[a-zA-Z0-9_]+$";
        return username.matches(regexPattern);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
