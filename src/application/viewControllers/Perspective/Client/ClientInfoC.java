package application.viewControllers.Perspective.Client;

import application.AOD.User.Client;
import application.sceneControler.SceneControler;
import application.user.User;
import application.viewControllers.Perspective.AbstractInfoV;
import javafx.event.ActionEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientInfoC extends AbstractInfoV {
    Client client;

    @Override
    protected void onEdit(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientEdit","clientInfo");
    }

    @Override
    protected void onBack(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientBasicInfo","clientInfo");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        client = User.getInstance().getClient();

    }
}
