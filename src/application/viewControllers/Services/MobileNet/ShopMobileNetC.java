package application.viewControllers.Services.MobileNet;

import application.Alerts.DecisionAlert;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopMobileNetC extends AbstractMobileNetC{

    @FXML
    private Button buy;

    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("shop","shopMobileNet");
    }

    private void buyMobileNetAb(ActionEvent event) {
        ButtonType buttonType = DecisionAlert.decisionAlert("Na pewno chcesz kupić nowy abonament na intetnet mobilny?");
        if (buttonType == ButtonType.YES) {
            User.getInstance().getClient().buyService(String.valueOf(user.getSelectedService()));
            SceneControler.getInstance().setSceneWithClear("shop","shopMobileNet");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        buy.setOnAction(this::buyMobileNetAb);
    }
}
