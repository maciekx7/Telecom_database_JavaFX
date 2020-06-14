package application.Alerts;

import javafx.scene.control.Alert;

public class InformAlert extends AbAlert{

    public static void informAlert(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(info);
        alert.setTitle("INFO");
        alert.show();
    }
}
