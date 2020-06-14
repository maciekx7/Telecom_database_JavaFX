package application.viewControllers.Services.LandlineNet;

import application.Alerts.DecisionAlert;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopLandlineNetC extends AbstractLandlineNetC   {

    @FXML
    private Button buy;

    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("shop","shopLandlineAb");
    }

    private void buyLandlineNetAb(ActionEvent event) {
        ButtonType buttonType = DecisionAlert.decisionAlert("Na pewno chcesz kupić nowy abonament na internet stacjonarny?");
        if (buttonType == ButtonType.YES)  {
            User.getInstance().getClient().buyService(String.valueOf(user.getSelectedService()));
            SceneControler.getInstance().setSceneWithClear("shop","shopLandlineAb");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        buy.setOnAction(this::buyLandlineNetAb);
    }
}
