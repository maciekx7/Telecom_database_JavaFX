package application.viewControllers.Services.PhoneAb;

import application.AOD.Services.PhoneAb;
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

public class AbstractPhoneAbC implements Initializable {
    Connection connection = DBConectivity.getConnection();
    PhoneAb phoneAb;
    User user = User.getInstance();

    @FXML
    protected Button backToMenu;
    @FXML
    protected Label minutes;
    @FXML
    protected Label sms;
    @FXML
    protected Label gigabs;
    @FXML
    protected Label spotify;
    @FXML
    protected Label name;
    @FXML
    protected Label number;
    @FXML
    protected Label cena_miesieczna;
    @FXML
    protected Label liczba_rat;



    protected void onBackButton(ActionEvent event) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        phoneAb = PhoneAb.getPhoneAb(connection,String.valueOf(user.getSelectedService()));
        name.setText(phoneAb.getPhoneAbName());
        spotify.setText(LayoutManager.TF(phoneAb.getPhoneAbSpotify(),"e"));
        minutes.setText(phoneAb.getPhoneAbMinutes());
        gigabs.setText(phoneAb.getPhoneAbGigabs());
        cena_miesieczna.setText(String.valueOf(phoneAb.getServicesMonthPrice()));
        liczba_rat.setText(String.valueOf(phoneAb.getServicesDuration()));

        backToMenu.setOnAction(this::onBackButton);
    }
}
