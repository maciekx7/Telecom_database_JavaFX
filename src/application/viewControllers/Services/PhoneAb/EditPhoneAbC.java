package application.viewControllers.Services.PhoneAb;

import application.AOD.Services.PhoneAb;
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

public class EditPhoneAbC extends AbstractEditPhoneAbC {
    PhoneAb phoneAb;

    @FXML
    private Button deleteButton;

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","EditPhoneAb");
    }

    @Override
    public void onApplyButton(ActionEvent event) {
        if(name.getText().replaceAll("\\s","").isEmpty() || cena_miesieczna.getText().replaceAll("\\s","").isEmpty()
        || liczba_rat.getText().replaceAll("\\s","").isEmpty()
        || liczba_rat.getText().length() > 2 || name.getText().length() > 50) {
            InformAlert.informAlert("OOOPS!\nWrong data!");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz dokonać edycji?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                phoneAb.setPhoneAbName(name.getText());
                phoneAb.setPhoneAbMinutes(LayoutManager.minutesFromScreen(String.valueOf(minutes.getValue())));
                phoneAb.setPhoneAbGigabs(String.valueOf(gigabs.getValue()));
                phoneAb.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                phoneAb.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                phoneAb.setPhoneAbSpotify(LayoutManager.TFfromScreen(String.valueOf(spotify.getValue())));
                phoneAb.setServicesID(user.getSelectedService());
                phoneAb.updateRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditPhoneAb");
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
                        phoneAb.deleteRecord(connection);
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
        phoneAb = PhoneAb.getPhoneAb(connection,String.valueOf(user.getSelectedService()));
        name.setText(phoneAb.getPhoneAbName());
        cena_miesieczna.setText(String.valueOf(phoneAb.getServicesMonthPrice()));
        liczba_rat.setText(String.valueOf(phoneAb.getServicesDuration()));
        minutes.setValue(LayoutManager.minutesToScreen(phoneAb.getPhoneAbMinutes()));
        gigabs.setValue(phoneAb.getPhoneAbGigabs());
        spotify.setValue(LayoutManager.TF(phoneAb.getPhoneAbSpotify(),"e"));

        deleteButton.setOnAction(this::onDeleteButton);

    }
}
