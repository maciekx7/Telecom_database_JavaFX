package application.viewControllers.Services.PhoneAb;

import application.Alerts.DecisionAlert;
import application.Alerts.ErrorAlert;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopPhoneAbC extends AbstractPhoneAbC {
    @FXML
    private Button buy;

    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("shop","shopPhoneAb");
    }

    private void buyPhoneAb(ActionEvent event) {
        try {
            ButtonType buttonType = DecisionAlert.decisionAlert("Na pewno chcesz kupić nowy abonament komórkowy?");
            if (buttonType == ButtonType.YES)  {
                User.getInstance().getClient().buyService(String.valueOf(user.getSelectedService()));
                SceneControler.getInstance().setSceneWithClear("shop","shopPhoneAb");
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ShopPhoneAbC.class.getName());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        buy.setOnAction(this::buyPhoneAb);
    }
}
