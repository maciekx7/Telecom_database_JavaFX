package application.viewControllers.Services.DeviceAb;

import application.AOD.Services.DeviceAb;
import application.DBConnection.DBConectivity;
import application.LayoutManager.LayoutManager;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
public class AbstractDeviceAbC implements Initializable {

    Connection connection = DBConectivity.getConnection();
    DeviceAb deviceAb;
    User user = User.getInstance();


    @FXML
    protected Label camera;
    @FXML
    protected Label induction;
    @FXML
    protected Label size;
    @FXML
    protected Label port;
    @FXML
    protected Label jack;
    @FXML
    protected Label marka_model;
    @FXML
    protected Label cena_wejsciowa;
    @FXML
    protected Label cena_miesieczna;
    @FXML
    protected Label liczba_rat;
    @FXML
    protected Button backButton;


    protected void onBackButtonClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        deviceAb = DeviceAb.deviceAb(connection,String.valueOf(user.getSelectedService()));
        marka_model.setText(deviceAb.getPhoneModel().getPhoneMark() + " " +deviceAb.getPhoneModel().getPhoneModelName()); //TODO + phoneMark
        camera.setText(String.valueOf(deviceAb.getPhoneModel().getPhoneModelCamera()));
        induction.setText(LayoutManager.TF(String.valueOf(deviceAb.getPhoneModel().getPhoneModelInduction()),"a"));
        size.setText(String.valueOf(deviceAb.getPhoneModel().getPhoneModelSize()));
        port.setText(String.valueOf(deviceAb.getPhoneModel().getPhoneModelPort()));
        jack.setText(LayoutManager.TF(deviceAb.getPhoneModel().getPhoneModelJack(),"y"));
        cena_wejsciowa.setText(String.valueOf(deviceAb.getDeviceAbCost()));
        cena_miesieczna.setText(String.valueOf(deviceAb.getServicesMonthPrice()));
        liczba_rat.setText(String.valueOf(deviceAb.getServicesDuration()));

        backButton.setOnAction(this::onBackButtonClick);
    }




}
