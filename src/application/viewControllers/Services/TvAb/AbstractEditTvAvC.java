package application.viewControllers.Services.TvAb;

import application.AOD.Services.TV;
import application.DBConnection.DBConectivity;
import application.LayoutManager.LayoutManager;
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

public class AbstractEditTvAvC implements IEditInterface {
    TV tv;
    User user = User.getInstance();
    Connection connection = DBConectivity.getConnection();

    @FXML
    protected TextField name;
    @FXML
    protected TextField channels;
    @FXML
    protected ChoiceBox recorder;
    @FXML
    protected ChoiceBox multiroom;
    @FXML
    protected ChoiceBox hbo;
    @FXML
    protected ChoiceBox netflix;
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

    private void setExtras() {
        recorder.getItems().add(LayoutManager.TF("T","a"));
        recorder.getItems().add(LayoutManager.TF("F","a"));

        netflix.getItems().add(LayoutManager.TF("T","y"));
        netflix.getItems().add(LayoutManager.TF("F","y"));

        hbo.getItems().add(LayoutManager.TF("T","e"));
        hbo.getItems().add(LayoutManager.TF("F","e"));

        multiroom.getItems().add(LayoutManager.TF("T","y"));
        multiroom.getItems().add(LayoutManager.TF("F","y"));

        recorder.setValue(LayoutManager.TF("T","a"));
        netflix.setValue(LayoutManager.TF("T","y"));
        hbo.setValue(LayoutManager.TF("T","e"));
        multiroom.setValue(LayoutManager.TF("T","y"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::onBackButton);
        applyButton.setOnAction(this::onApplyButton);
        setExtras();

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

        channels.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                channels.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
