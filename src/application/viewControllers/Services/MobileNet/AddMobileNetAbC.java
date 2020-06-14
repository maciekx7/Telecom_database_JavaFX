package application.viewControllers.Services.MobileNet;

import application.AOD.Services.MobileNet;
import application.Alerts.DecisionPanelCustom;
import application.Alerts.InformAlert;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMobileNetAbC extends AbstractEditMobileNetC {

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","AddMobileNetAb");
    }


    @Override
    public void onApplyButton(ActionEvent event) {
        if(name.getText().replaceAll("\\s","").isEmpty() || cena_miesieczna.getText().replaceAll("\\s","").isEmpty()
                || liczba_rat.getText().replaceAll("\\s","").isEmpty()
                || liczba_rat.getText().length() > 2 || name.getText().length() > 50) {
            InformAlert.informAlert("OOOPS!\nWrong data!");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz wprowadzić produkt do oferty?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                mobileNet = new MobileNet();
                mobileNet.setMobileNetName(name.getText());
                mobileNet.setMobileNetGb(limit.getValue().toString());
                mobileNet.setMobileNetHappyH(LayoutManager.TFfromScreen(happyHours.getValue().toString()));
                mobileNet.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                mobileNet.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                mobileNet.newRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditMobileNetAb");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //FIXME -> nwm czemu to nie dziala
        super.initialize(url,resourceBundle);

    }


}
