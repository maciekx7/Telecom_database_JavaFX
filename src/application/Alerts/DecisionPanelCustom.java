package application.Alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DecisionPanelCustom {
    public static ButtonType decisionAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?" ,ButtonType.YES,ButtonType.NO);
        alert.setTitle("Jesteś pewny?");
        alert.setContentText(text);
        alert.showAndWait();
        return alert.getResult();
    }
}
