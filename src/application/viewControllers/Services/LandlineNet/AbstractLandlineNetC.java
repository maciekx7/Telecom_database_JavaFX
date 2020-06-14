package application.viewControllers.Services.LandlineNet;

import application.AOD.Services.LandlineNet;
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

public class AbstractLandlineNetC implements Initializable {
    Connection connection = DBConectivity.getConnection();
    LandlineNet landlineNetl;
    User user = User.getInstance();
    @FXML
    protected Label speed;
    @FXML
    protected Label type;
    @FXML
    protected Label name;
    @FXML
    protected Label cena_miesieczna;
    @FXML
    protected Label liczba_rat;
    @FXML
    protected Button backToMenu;
    @FXML
    protected Label uplink;

    protected void onBackButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        landlineNetl = LandlineNet.getLandlineNet(connection,String.valueOf(user.getSelectedService()));
        name.setText(landlineNetl.getLandlineName());
        speed.setText(LayoutManager.downlinkToScreen(landlineNetl.getLandlineSpeed()));
        type.setText(landlineNetl.getLandlineCabel());
        liczba_rat.setText(String.valueOf(landlineNetl.getServicesDuration()));
        cena_miesieczna.setText(String.valueOf(landlineNetl.getServicesMonthPrice()));
        uplink.setText(LayoutManager.uplinkToScreen(landlineNetl.getLandlineSpeed()));

        backToMenu.setOnAction(this::onBackButton);
    }
}
