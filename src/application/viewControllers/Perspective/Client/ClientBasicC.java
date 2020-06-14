package application.viewControllers.Perspective.Client;

import application.AOD.User.Client;
import application.sceneControler.SceneControler;
import application.user.User;
import application.viewControllers.Perspective.AbstractBasicC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientBasicC extends AbstractBasicC {
    Client client;

    @FXML
    protected Button servicesInfo;
    @FXML
    protected Button shop;


    private void servicesInfoView(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("services","clientBasicInfo");
    }

    @Override
    protected void detailsInfoView(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientInfo","clientBasicInfo");
    }

    private void shopView(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("shop","clientBasicInfo");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);

        servicesInfo.setOnAction(this::servicesInfoView);
        shop.setOnAction(this::shopView);

        client = User.getInstance().getClient();


        servicesPrice.setText(String.valueOf(client.getServicesPrice()) + " zł");
        servicesCount.setText(String.valueOf(client.getServicesCount()));


    }
}
