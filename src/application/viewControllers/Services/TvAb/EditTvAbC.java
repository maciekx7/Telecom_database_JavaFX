package application.viewControllers.Services.TvAb;

import application.AOD.Services.TV;
import application.AOD.User.Client;
import application.Alerts.DecisionAlert;
import application.Alerts.DecisionPanelCustom;
import application.Alerts.ErrorAlert;
import application.Alerts.InformAlert;
import application.DBConnection.DBReader;
import application.LayoutManager.LayoutManager;
import application.resources.SQLQuery;
import application.sceneControler.SceneControler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EditTvAbC extends AbstractEditTvAvC {

    @FXML
    private Button deleteButton;


    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","EditTvAb");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        if(name.getText().replaceAll("\\s","").isEmpty() || channels.getText().replaceAll("\\s","").isEmpty()
        || cena_miesieczna.getText().replaceAll("\\s","").isEmpty() || liczba_rat.getText().replaceAll("\\s","").isEmpty() ||
        name.getText().length() > 50 || channels.getText().length() >4 || liczba_rat.getText().length() > 2)  {
            InformAlert.informAlert("OOOPS!\nWrong data");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz dokonać edycji?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                tv.setTVName(name.getText());
                tv.setTVCanals(Integer.parseInt(channels.getText()));
                tv.setServicesID(user.getSelectedService());
                tv.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                tv.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                tv.setTVRecorder(LayoutManager.TFfromScreen(recorder.getValue().toString()));
                tv.setTVMultiroom(LayoutManager.TFfromScreen(multiroom.getValue().toString()));
                tv.setTVNetflix(LayoutManager.TFfromScreen(netflix.getValue().toString()));
                tv.setTVHBOgo(LayoutManager.TFfromScreen(hbo.getValue().toString()));
                tv.updateRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditTvAb");
            }
        }
    }


    public void onDeleteButton(ActionEvent actionEvent)  {
        try {
            int count = 0;
            ResultSet result = DBReader.getSQLResult(connection, SQLQuery.COUNT_SERVICES, new String[]{String.valueOf(user.getSelectedService())});
            while(result.next())
            {
                count= result.getInt(1);
            }

            if (count == 0) {
                try {
                    ButtonType decision =  DecisionAlert.decisionAlert("Jesteś pewny?");
                    if(decision == ButtonType.YES) {
                        tv.deleteRecord(connection);
                        InformAlert.informAlert("Usunąłes usługę");
                        SceneControler.getInstance().setSceneWithClear("Catalog","EditDeviceAb");
                    }
                } catch (Exception e) {
                    System.out.println("jakis problem tutaj w DeviceAbEdit panel view w funkcji ");
                }
            }
            else {
                InformAlert.informAlert("Usługa zostakla już przez kogoś wykupiona");
            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);

        tv = TV.getTV(connection,String.valueOf(user.getSelectedService()));
        name.setText(tv.getTVName());
        channels.setText(String.valueOf(tv.getTVCanals()));
        liczba_rat.setText(String.valueOf(tv.getServicesDuration()));
        cena_miesieczna.setText(String.valueOf(tv.getServicesMonthPrice()));
        netflix.setValue(LayoutManager.TF(tv.getTVNetflix(),"y"));
        hbo.setValue(LayoutManager.TF(tv.getTVHBOgo(),"e"));
        multiroom.setValue(LayoutManager.TF(tv.getTVMultiroom(),"y"));
        recorder.setValue(LayoutManager.TF(tv.getTVRecorder(),"a"));
        deleteButton.setOnAction(this::onDeleteButton);
    }



}
