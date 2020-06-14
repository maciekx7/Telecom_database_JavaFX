package application.viewControllers.Services.MobileNet;

import application.AOD.Services.MobileNet;
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

public class AbstractMobileNetC implements Initializable {
    Connection connection = DBConectivity.getConnection();
    MobileNet mobileNet;
    User user = User.getInstance();

    @FXML
    protected Label cena_miesieczna;
    @FXML
    protected Label liczba_rat;
    @FXML
    protected Label name;
    @FXML
    protected Label limit;
    @FXML
    protected Label happyHours;
    @FXML
    protected Button backToMenu;


    protected void onBackButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mobileNet = MobileNet.getMobileNet(connection,String.valueOf(user.getSelectedService()));
        name.setText(mobileNet.getMobileNetName());
        limit.setText(mobileNet.getMobileNetGb());
        cena_miesieczna.setText(String.valueOf(mobileNet.getServicesMonthPrice()));
        liczba_rat.setText(String.valueOf(mobileNet.getServicesDuration()));
        happyHours.setText(LayoutManager.TF(mobileNet.getMobileNetHappyH(),"e"));

        backToMenu.setOnAction(this::onBackButton);
    }
}
