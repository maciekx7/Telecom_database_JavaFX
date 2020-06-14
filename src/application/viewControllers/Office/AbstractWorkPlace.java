package application.viewControllers.Office;

import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AbstractWorkPlace implements Initializable {


    @FXML
    protected Label city;
    @FXML
    protected Label street;
    @FXML
    protected Label streenNr;
    @FXML
    protected Label name;
    @FXML
    protected Label phone;
    @FXML
    protected Label email;
    @FXML
    protected Button back;
    @FXML
    protected Button employeButton;


    protected void onBackButton(ActionEvent event) {};

    protected void onEmployeeView(ActionEvent event) {};



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(this::onBackButton);
        employeButton.setOnAction(this::onEmployeeView);
    }
}
