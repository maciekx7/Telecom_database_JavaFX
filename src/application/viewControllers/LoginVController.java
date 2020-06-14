package application.viewControllers;

import application.Alerts.InformAlert;
import application.DBConnection.DBConectivity;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginVController implements Initializable {

    @FXML
    private Button loginB;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;




    private void login()
    {
        
        /*
        In this method program decides if user is a client or worker. Checking based of email address. In this sample, program uses sample emails stored in database. In production use delete "String email" and replace it with reading from TextFields.
        */
        
        if(email.getText().contains("@orange.com") || email.getText().contains("@orange.pl")) {
            String email = "lukasz.sleboda@orange.com";
            User.getInstance().setEmployee(email,password.getText());
            User.getInstance().setEmail(email);
            if(User.getInstance().getEmployee()!=null) {
                SceneControler.getInstance().setSceneWithClear("employeeBasicInfo", "login");
            } else {
                InformAlert.informAlert("Nieprawidłowe dane");
                password.setText("");
            }
        } else {
            String email = "stasss.skrzypek@gamil.com";
            User.getInstance().setClient(email,password.getText());
            User.getInstance().setEmail(email);
            if(User.getInstance().getClient()!= null) {
                SceneControler.getInstance().setSceneWithClear("clientBasicInfo", "login");
            } else {
                InformAlert.informAlert("Nieprawidłowe dane");
                password.setText("");
            }
        }

    }


    private void onLoginBClick(ActionEvent event) {
        login();
    }

    public void handleKeyPressed(javafx.scene.input.KeyEvent keyEvent) {
        System.out.print(keyEvent.getCode());
        if(keyEvent.getCode() == keyEvent.getCode().ENTER)
        {
            login();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = DBConectivity.getConnection();
        loginB.setOnAction(this::onLoginBClick);
    }


}
