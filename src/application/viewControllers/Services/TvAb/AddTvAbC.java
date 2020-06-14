package application.viewControllers.Services.TvAb;

import application.AOD.Services.TV;
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

public class AddTvAbC extends AbstractEditTvAvC {

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","AddTvAb");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        if(name.getText().replaceAll("\\s","").isEmpty() || channels.getText().replaceAll("\\s","").isEmpty()
                || cena_miesieczna.getText().replaceAll("\\s","").isEmpty() || liczba_rat.getText().replaceAll("\\s","").isEmpty() ||
                name.getText().length() > 50 || channels.getText().length() >4 || liczba_rat.getText().length() > 2)  {
            InformAlert.informAlert("OOOPS!\nWrong data");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz wprowadzić produkt do oferty?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                tv = new TV();
                tv.setTVName(name.getText());
                tv.setTVCanals(Integer.parseInt(channels.getText()));
                tv.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                tv.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                tv.setTVRecorder(LayoutManager.TFfromScreen(recorder.getValue().toString()));
                tv.setTVMultiroom(LayoutManager.TFfromScreen(multiroom.getValue().toString()));
                tv.setTVNetflix(LayoutManager.TFfromScreen(netflix.getValue().toString()));
                tv.setTVHBOgo(LayoutManager.TFfromScreen(hbo.getValue().toString()));
                tv.newRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditTvAb");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);

    }

}
