package application.viewControllers.Perspective;

import application.AOD.User.AbstractUser;
import application.Alerts.ErrorAlert;
import application.LayoutManager.LayoutManager;
import application.tools.date.DateTool;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AbstractInfoV implements Initializable {
    AbstractUser abstractUser;

    @FXML
    protected Label city;
    @FXML
    protected Label buildingNr;
    @FXML
    protected Label streenNr;
    @FXML
    protected Label street;

    @FXML
    protected Label name;
    @FXML
    protected Label lastName;
    @FXML
    protected Label email;
    @FXML
    protected Label phone;
    @FXML
    protected Label pesel;
    @FXML
    protected Label birth;
    @FXML
    protected Label gender;
    @FXML
    protected Button edit;
    @FXML
    protected Button back;

    protected void onEdit(ActionEvent event) {};

    protected void onBack(ActionEvent event) {};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            abstractUser = User.getInstance().getAbstractUser();
            edit.setOnAction(this::onEdit);
            back.setOnAction(this::onBack);


            name.setText(abstractUser.getFirstName());
            lastName.setText(abstractUser.getLastName());
            email.setText(abstractUser.getEmail());
            phone.setText(LayoutManager.phoneNumberToScreen(String.valueOf(abstractUser.getPhone())));
            pesel.setText(String.valueOf(abstractUser.getPesel()));

            city.setText(abstractUser.getAddress_().getAddressCity());
            street.setText(abstractUser.getAddress_().getAddressStreet());
            streenNr.setText(abstractUser.getAddress_().getAddressStreetNumber());
            buildingNr.setText(abstractUser.getAddress_().getAddressBuildingNumber());
            birth.setText(DateTool.getDateWithMonthNames.YearFirst(abstractUser.getBirth()));
            gender.setText(LayoutManager.genderToScreen(abstractUser.getGender()));

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, AbstractInfoV.class.getName());
        }
    }
}
