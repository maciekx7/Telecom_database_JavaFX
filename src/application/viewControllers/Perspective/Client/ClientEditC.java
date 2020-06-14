package application.viewControllers.Perspective.Client;

import application.AOD.User.Client;
import application.Alerts.InformAlert;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
import application.tools.date.DateTool;
import application.user.User;
import application.viewControllers.Perspective.AbstractEditV;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientEditC extends AbstractEditV {
    User user = User.getInstance();
    Client client;


    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientInfo","clientEdit");
    }

    @Override
    protected void onPasswordChangeButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("ClientChangePassword","clientEdit");
    }

    @Override
    protected void onAcceptButton(ActionEvent event) {
        if (name.getText().length() >40 || lastName.getText().length() >40
                || name.getText().replaceAll("\\s","").isEmpty()
                || lastName.getText().replaceAll("\\s","").isEmpty()) {
            InformAlert.informAlert("OOOPS!\n Wrong data!");
        } else {
            Client tmp = new Client(client);
            tmp.setFirstName(name.getText());
            tmp.setLastName(lastName.getText());
            tmp.setBirth(String.valueOf(birthday.getValue()));
            tmp.setGender(LayoutManager.genderFromScreen(gender.getValue()));

            tmp.getAddress_().setAddressCity(city.getText());
            tmp.getAddress_().setAddressStreet(street.getText());
            tmp.getAddress_().setAddressStreetNumber(streetNumber.getText());
            tmp.getAddress_().setAddressBuildingNumber(buildingNumber.getText());

            client.updateClient(tmp);
            SceneControler.getInstance().setSceneWithClear("clientInfo","clientEdit");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);

        client = User.getInstance().getClient();

    }



}
