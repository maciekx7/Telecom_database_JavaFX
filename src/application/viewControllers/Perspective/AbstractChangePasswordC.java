package application.viewControllers.Perspective;

import application.DBConnection.DBConectivity;
import application.user.User;
import application.viewControllers.Services.IEditInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AbstractChangePasswordC implements IEditInterface {
    protected  Connection connection = DBConectivity.getConnection();
    protected  User user = User.getInstance();

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

    }

    @Override
    public void onApplyButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::onBackButton);
        applyButton.setOnAction(this::onApplyButton);
    }
}
