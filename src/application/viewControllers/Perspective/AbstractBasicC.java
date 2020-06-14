package application.viewControllers.Perspective;

import application.AOD.User.AbstractUser;
import application.DBConnection.DBConectivity;
import application.LayoutManager.LayoutManager;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AbstractBasicC implements Initializable {
    AbstractUser abstractUser;

    @FXML
    protected Label servicesCount;
    @FXML
    protected Label servicesPrice;
    @FXML
    protected Button details;

    @FXML
    protected Label name;
    @FXML
    protected Label phoneNumber;
    @FXML
    protected Button logout;


    private void logOut(ActionEvent event) {
        DBConectivity.closeConnection();
        System.exit(0);
    }

    protected void detailsInfoView(ActionEvent event) { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout.setOnAction(this::logOut);
        details.setOnAction(this::detailsInfoView);

        abstractUser = User.getInstance().getAbstractUser();

        name.setText(abstractUser.getFirstName()+" "+ abstractUser.getLastName());
        phoneNumber.setText(LayoutManager.phoneNumberToScreen(String.valueOf(abstractUser.getPhone())));

    }
}
