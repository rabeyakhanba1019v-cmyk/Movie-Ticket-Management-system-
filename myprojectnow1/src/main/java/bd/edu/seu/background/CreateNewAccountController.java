package bd.edu.seu.background;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewAccountController implements Initializable {
    @FXML
    private PasswordField newpasswordFiled;

    @FXML
    private Label newpasswordinvalid;

    @FXML
    private Label newuserinvalid;

    @FXML
    private TextField newusername;
    @FXML
    private Label suceessfulLabel;

    @FXML
    void createAccount(ActionEvent event) {
        boolean set=true;
        String newuser=newusername.getText();
        if(newuser.trim().isEmpty())
        {newuserinvalid.setVisible(true);
            set=false;}
        else
        {   newuserinvalid.setVisible(false);
            set=true;}

        String pass=newpasswordFiled.getText();
        if(pass.trim().isEmpty())
        {
            newpasswordinvalid.setVisible(true);
            set=false;
        }
        else{
            newpasswordinvalid.setVisible(false);
            set=true;
        }

        if(set)
        {
            String fin1=newuser+","+pass;
             int point =0;
            System.out.println(fin1);
            Customerlogin.writenewlogin(fin1);
            Superpoints superpoints =new Superpoints(newuser,point);
            Savedataindatbase.savesuperpoints(superpoints);
            suceessfulLabel.setVisible(true);
        }
    }
    @FXML
    void returntologin(ActionEvent event) {
 HelloApplication.changescene("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newpasswordinvalid.setVisible(false);
        newuserinvalid.setVisible(false);
        suceessfulLabel.setVisible(false);
    }
}
