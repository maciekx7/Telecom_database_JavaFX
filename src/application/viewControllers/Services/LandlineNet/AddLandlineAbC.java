package application.viewControllers.Services.LandlineNet;

import application.AOD.Services.LandlineNet;
import application.Alerts.DecisionPanelCustom;
import application.Alerts.InformAlert;
import application.sceneControler.SceneControler;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLandlineAbC extends AbstractEditLandlineNet {

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","AddLandlineAb");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        if (name.getText().length() > 50 || cena_miesieczna.getText().replaceAll("\\s","").isEmpty()
                || liczba_rat.getText().replaceAll("\\s","").isEmpty()
        ) {
            InformAlert.informAlert("OOOPS\nWrong data!");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz wprowadzić produkt do oferty?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                landlineNet = new LandlineNet();
                landlineNet.setLandlineName(name.getText());
                landlineNet.setLandlineCabel(cabel.getValue().toString());
                landlineNet.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                landlineNet.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                landlineNet.setLandlineSpeed(downlink.getValue().toString());
                landlineNet.newRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditLandlineAb");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        super.initialize(url,resourceBundle);

    }

}
