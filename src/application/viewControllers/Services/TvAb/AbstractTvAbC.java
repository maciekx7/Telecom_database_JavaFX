package application.viewControllers.Services.TvAb;

import application.AOD.Services.TV;
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

public class AbstractTvAbC implements Initializable {
    Connection connection = DBConectivity.getConnection();
    TV tv;
    User user = User.getInstance();

    @FXML
    protected Label cena_miesieczna;
    @FXML
    protected Label liczba_rat;
    @FXML
    protected Label recorder;
    @FXML
    protected Label multiroom;
    @FXML
    protected Label hbo;
    @FXML
    protected Label netflix;
    @FXML
    protected Label name;
    @FXML
    protected Label channels;
    @FXML
    protected Button backToMenu;

    protected void onBackButton(ActionEvent event) { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv = TV.getTV(connection,String.valueOf(user.getSelectedService()));
        recorder.setText(LayoutManager.TF(tv.getTVRecorder(),"a"));
        multiroom.setText(LayoutManager.TF(tv.getTVMultiroom(),"y"));
        hbo.setText(LayoutManager.TF(tv.getTVHBOgo(),"e"));
        netflix.setText(LayoutManager.TF(tv.getTVNetflix(),"y"));
        channels.setText(String.valueOf(tv.getTVCanals()));
        name.setText(tv.getTVName());

        liczba_rat.setText(String.valueOf(tv.getServicesDuration()));
        cena_miesieczna.setText(String.valueOf(tv.getServicesMonthPrice()));

        backToMenu.setOnAction(this::onBackButton);
    }
}
