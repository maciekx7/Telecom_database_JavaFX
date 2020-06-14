package application.viewControllers.Perspective.Client;

import application.Alerts.InformAlert;
import application.sceneControler.SceneControler;
import application.viewControllers.Perspective.AbstractChangePasswordC;
import application.viewControllers.Services.IEditInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientPasswordChangeC extends AbstractChangePasswordC {


    @FXML
    protected PasswordField oldPassword;
    @FXML
    protected PasswordField newPassword;
    @FXML
    protected PasswordField checkPassword;
    @FXML
    protected Button backButton;
    @FXML
    protected Button applyButton;


    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientEdit","ClientChangePassword");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        if(!checkPassword.getText().equals(newPassword.getText())) {
            InformAlert.informAlert("Wpisz poprawnie nowe hasło dwa razy");
        } else {
            if(user.getClient().updatePassword(connection, newPassword.getText(), oldPassword.getText())) {
                InformAlert.informAlert("Zmiana hasła przebiegła pomyślnie");
                SceneControler.getInstance().setSceneWithClear("clientEdit","ClientChangePassword");
            } else {
                InformAlert.informAlert("Obecne hasło jest błędne");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
    }
}
