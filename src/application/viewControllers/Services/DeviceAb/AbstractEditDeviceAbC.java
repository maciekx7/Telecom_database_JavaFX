package application.viewControllers.Services.DeviceAb;

import application.AOD.Services.DeviceAb;
import application.AOD.Services.PhoneAbExtrases.PhoneMark;
import application.DBConnection.DBConectivity;
import application.LayoutManager.LayoutManager;
import application.user.User;
import application.viewControllers.Services.IEditInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbstractEditDeviceAbC implements IEditInterface {
    protected User user = User.getInstance();
    DeviceAb deviceAb;
    Connection connection = DBConectivity.getConnection();

    @FXML
    protected ChoiceBox mark;
    @FXML
    protected TextField model;
    @FXML
    protected TextField camera;
    @FXML
    protected ChoiceBox induction;
    @FXML
    protected TextField size;
    @FXML
    protected ChoiceBox port;
    @FXML
    protected ChoiceBox jack;
    @FXML
    protected TextField cena_wejsciowa;
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
    public void onApplyButton(ActionEvent event) {
        //TODO edit DeviceAb
    }

    private void addPort() {
        String[] ports = new String[]{"USB-C","USB-A","LIGHTNING"};
        for(String port_:ports) {
            port.getItems().add(port_);
        }
        port.setValue(ports[0]);
    }

    private void addJack() {
        jack.getItems().add(LayoutManager.TF("T","y"));
        jack.getItems().add(LayoutManager.TF("F","y"));
        jack.setValue(LayoutManager.TF("T","y"));
    }

    private void addInduction() {
        induction.getItems().add(LayoutManager.TF("T","a"));
        induction.getItems().add(LayoutManager.TF("F","a"));
        induction.setValue(LayoutManager.TF("T","a"));
    }

    private void addMarks() {
        ArrayList<String> markList = PhoneMark.getAll(connection);
        for(String mark_: markList) {
            mark.getItems().add(mark_);
            System.out.println(mark_);
        }
        mark.setValue(markList.get(0));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backButton.setOnAction(this::onBackButton);
        applyButton.setOnAction(this::onApplyButton);

        addPort();
        addJack();
        addInduction();
        addMarks();

        liczba_rat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                liczba_rat.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cena_wejsciowa.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cena_wejsciowa.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cena_miesieczna.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cena_miesieczna.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        /**
         * \\^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)
         */
        camera.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)")) {
                camera.setText(newValue.replaceAll("[^\\d.]", "").replaceAll("\\.+","."));
            }
        });
                /*
        camera.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
                camera.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

                 */

    }
}
