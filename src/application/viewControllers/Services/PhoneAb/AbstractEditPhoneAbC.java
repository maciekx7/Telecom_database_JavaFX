package application.viewControllers.Services.PhoneAb;

import application.AOD.Services.PhoneAb;
import application.DBConnection.DBConectivity;
import application.LayoutManager.LayoutManager;
import application.user.User;
import application.viewControllers.Services.IEditInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AbstractEditPhoneAbC implements IEditInterface {
    Connection connection = DBConectivity.getConnection();
    PhoneAb phoneAb;
    User user = User.getInstance();
    @FXML
    protected TextField name;
    @FXML
    protected ChoiceBox minutes;
    @FXML
    protected TextField sms;
    @FXML
    protected ChoiceBox gigabs;
    @FXML
    protected ChoiceBox spotify;
    @FXML
    protected TextField cena_miesieczna;
    @FXML
    protected TextField liczba_rat;
    @FXML
    protected Button backButton;
    @FXML
    protected Button applyButton;

    @Override
    public void onBackButton(ActionEvent event) { }

    @Override
    public void onApplyButton(ActionEvent event) { }

    private void setMinutes() {
        String[] minutes_ = new String[]{"100","200","500","1000", LayoutManager.minutesToScreen("INF")};
        for(String minute:minutes_) {
            minutes.getItems().add(minute);
        }
        minutes.setValue(minutes_[0]);
    }

    private void setSpotify() {
        spotify.getItems().add(LayoutManager.TF("T","e"));
        spotify.getItems().add(LayoutManager.TF("F","e"));
        spotify.setValue(LayoutManager.TF("T","e"));
    }

    private void setGb() {
        String[] gb = new String[]{"1","2","5","10","15","30"};
        for(String g: gb) {
            gigabs.getItems().add(g);
        }
        gigabs.setValue(gb[0]);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::onBackButton);
        applyButton.setOnAction(this::onApplyButton);

        setMinutes();
        setGb();
        setSpotify();

        sms.setText("nielimitowane");
        sms.setDisable(true);

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
