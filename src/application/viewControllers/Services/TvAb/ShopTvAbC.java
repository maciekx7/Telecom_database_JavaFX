package application.viewControllers.Services.TvAb;

import application.Alerts.DecisionAlert;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopTvAbC extends AbstractTvAbC{

    @FXML
    private Button buy;

    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("shop","shopTvAb");
    }

    private void buyTvAb(ActionEvent event) {

        ButtonType buttonType = DecisionAlert.decisionAlert("Na pewno chcesz kupić nowy abonament telewizyjny?");
        if (buttonType == ButtonType.YES)  {
            User.getInstance().getClient().buyService(String.valueOf(user.getSelectedService()));
            SceneControler.getInstance().setSceneWithClear("shop","shopTvAb");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        buy.setOnAction(this::buyTvAb);
    }
}

