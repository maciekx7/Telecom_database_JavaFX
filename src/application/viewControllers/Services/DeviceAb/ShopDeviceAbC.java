package application.viewControllers.Services.DeviceAb;

import application.Alerts.DecisionAlert;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopDeviceAbC extends AbstractDeviceAbC {



    @FXML
    private Button buy;

    @Override
    protected void onBackButtonClick(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("shop","ShopDeviceAb");
    }

    private void buyDeviceAb(ActionEvent event) {
        ButtonType buttonType = DecisionAlert.decisionAlert("Na pewno chcesz kupić nowy sprzęt?");
        if (buttonType == ButtonType.YES)  {
            User.getInstance().getClient().buyService(String.valueOf(user.getSelectedService()));
            SceneControler.getInstance().setSceneWithClear("shop","ShopDeviceAb");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        super.initialize(url, resourceBundle);
        buy.setOnAction(this::buyDeviceAb);
    }
}
