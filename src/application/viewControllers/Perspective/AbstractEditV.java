package application.viewControllers.Perspective;

import application.AOD.User.AbstractUser;
import application.LayoutManager.LayoutManager;
import application.tools.date.DateTool;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AbstractEditV implements Initializable {
    AbstractUser abstractUser;


    @FXML
    protected TextField name;
    @FXML
    protected TextField lastName;
    @FXML
    protected ChoiceBox<String> gender;
    @FXML
    protected Button cancel;
    @FXML
    protected Button accept;
    @FXML
    protected TextField city;
    @FXML
    protected TextField street;
    @FXML
    protected TextField streetNumber;
    @FXML
    protected TextField buildingNumber;
    @FXML
    protected DatePicker birthday;
    @FXML
    protected Button passwordEdit;

    protected void onBackButton(ActionEvent event) { }

    protected void onAcceptButton(ActionEvent event) { }

    protected void onPasswordChangeButton(ActionEvent event) {}



    private void genderList() {
        gender.getItems().add(LayoutManager.genderToScreen("M"));
        gender.getItems().add(LayoutManager.genderToScreen("K"));
        gender.setValue(LayoutManager.genderToScreen(abstractUser.getGender()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        abstractUser = User.getInstance().getAbstractUser();
        cancel.setOnAction(this::onBackButton);
        accept.setOnAction(this::onAcceptButton);
        passwordEdit.setOnAction(this::onPasswordChangeButton);

        name.setText(abstractUser.getFirstName());
        lastName.setText(abstractUser.getLastName());
        city.setText(abstractUser.getAddress_().getAddressCity());
        street.setText(abstractUser.getAddress_().getAddressStreet());
        streetNumber.setText(abstractUser.getAddress_().getAddressStreetNumber());
        buildingNumber.setText(abstractUser.getAddress_().getAddressBuildingNumber());
        birthday.setValue(DateTool.getLocalDate(abstractUser.getBirth()));
        genderList();

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]+")) {
                name.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });
        lastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]+")) {
                lastName.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });
        city.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]+")) {
                city.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });

    }
}
