
package bd.edu.seu.background;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class LoginController {
    static String username;

    @FXML
    private TextField userTextfield;

    @FXML
    private PasswordField userpasswordField;

    @FXML
    void adminloginto(ActionEvent event) {
        HelloApplication.changescene("adminlogin");
    }

    @FXML
    void createnewac(ActionEvent event) {
        HelloApplication.changescene("createNewAccount");
    }

    @FXML
    void loginto(ActionEvent event) {
         username = userTextfield.getText(); // Assuming usernameField is the username input field
        String password = userpasswordField.getText(); // Assuming passwordField is the password input field
        List<Officer> officerList = Customerlogin.readLogInFile();
        boolean right = false; // To track successful login

        // Check credentials against the stored officers
        for (Officer officer : officerList) {
            if (officer.compareUsername(username) && officer.comparePassword(password)) {
                right = true;
                break; // Exit the loop once credentials are correct
            }
        }

        // After the loop
        if (right) {

            HelloApplication.changescene("dashboard");  // Pass username when changing the scene
        } else {
            // Show an error alert if login is unsuccessful
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Please check your credentials and try again.");
            alert.showAndWait();
        }
    }
    @FXML
    void chant(ActionEvent event) {
HelloApplication.changescene("homescene");
    }
}
