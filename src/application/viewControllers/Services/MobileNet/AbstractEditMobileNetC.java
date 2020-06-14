package application.viewControllers.Services.MobileNet;

import application.AOD.Services.MobileNet;
import application.Alerts.ErrorAlert;
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
import java.util.ResourceBundle;

public class AbstractEditMobileNetC implements IEditInterface {
    MobileNet mobileNet;
    Connection connection = DBConectivity.getConnection();
    User user = User.getInstance();
    @FXML
    protected TextField name;
    @FXML
    protected ChoiceBox limit;
    @FXML
    protected ChoiceBox happyHours;
    @FXML
    protected TextField cena_miesieczna;
    @FXML
    protected TextField liczba_rat;
    @FXML
    protected Button backButton;
    @FXML
    protected Button applyButton;



    @Override
    public void onBackButton(ActionEvent event) {

    }

    @Override
    public void onApplyButton(ActionEvent event) {

    }

    private void addGB() {
        String[] gb = new String[]{"30","50","75","100","200","400","500"};
        for(String g:gb) {
            limit.getItems().add(g);
        }
        limit.setValue(gb[0]);
    }

    private void addHappy() {
        happyHours.getItems().add(LayoutManager.TF("T","e"));
        happyHours.getItems().add(LayoutManager.TF("F","e"));
        happyHours.setValue(LayoutManager.TF("T","e"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            backButton.setOnAction(this::onBackButton);
            applyButton.setOnAction(this::onApplyButton);

            addGB();
            addHappy();

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


        } catch (Exception e) {
            ErrorAlert.errorAlert(e,AbstractEditMobileNetC.class.getName());
        }

    }
}
