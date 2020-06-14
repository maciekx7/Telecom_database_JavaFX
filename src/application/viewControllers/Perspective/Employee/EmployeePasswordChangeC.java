package application.viewControllers.Perspective.Employee;

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

public class EmployeePasswordChangeC extends AbstractChangePasswordC {

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeEdit","EmployeeChangePassword");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        if(!checkPassword.getText().equals(newPassword.getText())) {
            InformAlert.informAlert("Wpisz poprawnie nowe hasło dwa razy");
        } else {
            if(user.getEmployee().updatePassword(connection, newPassword.getText(), oldPassword.getText())) {
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
