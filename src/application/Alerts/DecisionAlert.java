package application.Alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DecisionAlert {

    public static ButtonType decisionAlert(String infoText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?" ,ButtonType.YES,ButtonType.NO);
        alert.setTitle("Jesteś pewny?\n\n");
        alert.setContentText(infoText);
        alert.showAndWait();
        return alert.getResult();
    }
}
