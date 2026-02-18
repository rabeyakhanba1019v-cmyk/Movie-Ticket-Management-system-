package bd.edu.seu.background;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomesceneController {

    @FXML
    void loginto(ActionEvent event) {
   HelloApplication.changescene("login");
    }

}
