package ca.bcit.comp2522.termproject.planetbandit;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Represents a login controller object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class LoginController {
    @FXML
    private TextField emailIdField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;

    /**
     * Sets the screen to the gameplay screen if the login is valid.
     *
     * @throws Exception if the login is invalid
     */
    @FXML
    public void login() throws Exception {
        Window owner = submitButton.getScene().getWindow();
        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());
        if (emailIdField.getText().isEmpty()) {
            showAlert(owner,
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(owner,
                    "Please enter a password");
            return;
        }
        String emailId = emailIdField.getText();
        String password = passwordField.getText();
        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(emailId, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Success");
            GamePlay gamePlay = new GamePlay();
            Stage stage = new Stage();
            gamePlay.start(stage);
        }
    }

    /**
     * Represents an info box that the user can type into.
     * @param infoMessage a string that represents the body of the info box
     * @param headerText a string that represents the header of the info box
     * @param title a string that represents the title of the info box
     */
    public static void infoBox(final String infoMessage, final String headerText, final String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(final Window owner,
                                  final String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Form Error!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
