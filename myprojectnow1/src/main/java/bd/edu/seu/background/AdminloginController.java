package bd.edu.seu.background;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminloginController implements Initializable {
    static String  adminName;

    @FXML
    private PasswordField adminid;

    @FXML
    private TextField adminname;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    static Officer admin = new Officer();
    @FXML
    void log(ActionEvent event) {

        adminName=adminname.getText();
        String password=adminid.getText();
        boolean a=false;
        if(admin.compare(password,adminName)){
            a=true;
        }
        else {
            label1.setVisible(true);
            label2.setVisible(true);
        }
        if(a)
        {
            HelloApplication.changescene("admindashboard");

        }

    }
    @FXML
    void chantttttt(ActionEvent event) {
HelloApplication.changescene("homescene");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setVisible(false);
        label2.setVisible(false);
    }
}
