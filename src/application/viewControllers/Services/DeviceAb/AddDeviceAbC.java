package application.viewControllers.Services.DeviceAb;

import application.AOD.Services.DeviceAb;
import application.Alerts.DecisionPanelCustom;
import application.Alerts.ErrorAlert;
import application.Alerts.InformAlert;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDeviceAbC extends AbstractEditDeviceAbC {

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","AddDeviceAb");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        try {
            if(
                    model.getText().replaceAll("\\s","").isEmpty() || model.getText().length() > 30
                            || camera.getText().replaceAll("\\s","").isEmpty() || camera.getText().length() > 4
                            || size.getText().replaceAll("\\s","").isEmpty() || size.getText().length() > 4
                            || cena_miesieczna.getText().replaceAll("\\s","").isEmpty()
                            || liczba_rat.getText().replaceAll("\\s","").isEmpty() || liczba_rat.getText().length() > 2) {
                InformAlert.informAlert("OOOPS!\nWrong data!");
            } else {
                ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz wprowadzić produkt do oferty?\nTa Akcja jest nieodwracalna!");
                if(buttonType == ButtonType.YES) {
                    deviceAb = new DeviceAb();
                    deviceAb.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                    deviceAb.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                    deviceAb.getPhoneModel().setPhoneModelName(model.getText());
                    deviceAb.getPhoneModel().setPhoneMark(mark.getValue().toString());
                    deviceAb.getPhoneModel().setPhoneModelCamera(Integer.parseInt(camera.getText()));
                    deviceAb.getPhoneModel().setPhoneModelInduction(LayoutManager.TFfromScreen(induction.getValue().toString()));
                    deviceAb.getPhoneModel().setPhoneModelSize(size.getText());
                    deviceAb.getPhoneModel().setPhoneModelPort(port.getValue().toString());
                    deviceAb.getPhoneModel().setPhoneModelJack(LayoutManager.TFfromScreen(jack.getValue().toString()));
                    deviceAb.setDeviceAbCost(Integer.parseInt(cena_wejsciowa.getText()));
                    deviceAb.newRecord(connection);
                    SceneControler.getInstance().setSceneWithClear("Catalog","EditDeviceAb");
                }

            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, AddDeviceAbC.class.getName());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        super.initialize(url,resourceBundle);

    }


}
