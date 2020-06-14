package application.viewControllers.Services.PhoneAb;

import application.AOD.Services.PhoneAb;
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

public class AddPhoneAbC extends AbstractEditPhoneAbC {

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","AddPhoneAb");
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
                phoneAb = new PhoneAb();
                phoneAb.setPhoneAbName(name.getText());
                phoneAb.setPhoneAbMinutes(LayoutManager.minutesFromScreen(String.valueOf(minutes.getValue())));
                phoneAb.setPhoneAbGigabs(String.valueOf(gigabs.getValue()));
                phoneAb.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                phoneAb.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                phoneAb.setPhoneAbSpotify(LayoutManager.TFfromScreen(String.valueOf(spotify.getValue())));
                phoneAb.newRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditPhoneAb");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        super.initialize(url,resourceBundle);

    }
}
