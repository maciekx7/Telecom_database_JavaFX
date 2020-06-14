package application.viewControllers.Services.LandlineNet;

import application.AOD.Services.LandlineNet;
import application.DBConnection.DBConectivity;
import application.user.User;
import application.viewControllers.Services.IEditInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AbstractEditLandlineNet implements IEditInterface {
    LandlineNet landlineNet;
    Connection connection = DBConectivity.getConnection();
    User user = User.getInstance();

    @FXML
    protected TextField name;
    @FXML
    protected ChoiceBox downlink;
    @FXML
    protected TextField uplink;
    @FXML
    protected TextField cena_miesieczna;
    @FXML
    protected TextField liczba_rat;
    @FXML
    protected ChoiceBox cabel;
    @FXML
    protected Button backButton;
    @FXML
    protected Button applyButton;

    public void onBackButton(ActionEvent event) { }


    public void onApplyButton(ActionEvent event) { }

    protected void addSpeeds() {
        String[] speeds = new String[] {"10","20","50","75","100","300","500","600","1000"};
        for(String speed: speeds) {
            downlink.getItems().add(speed);
        }
        downlink.setValue((speeds[0]));

    }

    protected void addTechnology() {
        String[] technology = new String[] {"swiatlowod".toUpperCase(), "miedziany".toUpperCase()};
        for(String tech:technology) {
            cabel.getItems().add(tech);
        }
        cabel.setValue(technology[0]);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::onBackButton);
        applyButton.setOnAction(this::onApplyButton);
        addSpeeds();
        addTechnology();

        liczba_rat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                liczba_rat.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cena_miesieczna.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cena_miesieczna.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
