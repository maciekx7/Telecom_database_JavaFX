package application.viewControllers.Services.DeviceAb;

import application.AOD.Services.DeviceAb;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EditDeviceAbC extends AbstractEditDeviceAbC {
    @FXML
    private Button deleteButton;

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","EditDeviceAb");
    }


    @Override
    public void onApplyButton(ActionEvent event) {
        if(
         model.getText().replaceAll("\\s","").isEmpty() || model.getText().length() > 30
        || camera.getText().replaceAll("\\s","").isEmpty() || camera.getText().length() > 4
        || size.getText().replaceAll("\\s","").isEmpty() || size.getText().length() > 4
        || cena_miesieczna.getText().replaceAll("\\s","").isEmpty()
        || liczba_rat.getText().replaceAll("\\s","").isEmpty() || liczba_rat.getText().length() > 2) {
            InformAlert.informAlert("OOOPS!\nWrong data!");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz dokonać edycji?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                deviceAb.setServicesID(user.getSelectedService());
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
                deviceAb.updateRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditDeviceAb");
            }
        }
    }

    public void onDeleteButton(ActionEvent actionEvent)  {
        try {
            int count = 0;
            ResultSet result = DBReader.getSQLResult(connection, SQLQuery.COUNT_SERVICES, new String[]{String.valueOf(user.getSelectedService())});
            while(result.next()) {
                count= result.getInt(1);
            }

            if (count == 0) {
                try {
                    ButtonType decision =  DecisionAlert.decisionAlert("Jesteś pewny?");
                    if(decision == ButtonType.YES) {
                        deviceAb.deleteRecord(connection);
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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        super.initialize(url,resourceBundle);
        deleteButton.setOnAction(this::onDeleteButton);

        deviceAb = DeviceAb.deviceAb(connection,String.valueOf(user.getSelectedService()));
        mark.setValue(deviceAb.getPhoneModel().getPhoneMark());
        model.setText(deviceAb.getPhoneModel().getPhoneModelName());
        camera.setText(String.valueOf(deviceAb.getPhoneModel().getPhoneModelCamera()));

        induction.setValue(LayoutManager.TF(deviceAb.getPhoneModel().getPhoneModelInduction(),"a"));
        jack.setValue(LayoutManager.TF(deviceAb.getPhoneModel().getPhoneModelJack(),"y"));

        size.setText(String.valueOf(deviceAb.getPhoneModel().getPhoneModelSize()));
        cena_miesieczna.setText(String.valueOf(deviceAb.getServicesMonthPrice()));
        cena_wejsciowa.setText(String.valueOf(deviceAb.getDeviceAbCost()));
        liczba_rat.setText(String.valueOf(deviceAb.getServicesDuration()));
        port.setValue(deviceAb.getPhoneModel().getPhoneModelPort());



    }
}
