package application.viewControllers.Services.LandlineNet;

import application.AOD.Services.LandlineNet;
import application.AOD.User.Client;
import application.Alerts.DecisionAlert;
import application.Alerts.DecisionPanelCustom;
import application.Alerts.ErrorAlert;
import application.Alerts.InformAlert;
import application.DBConnection.DBReader;
import application.resources.SQLQuery;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class EditLandlineAbC extends AbstractEditLandlineNet {

    @FXML
    private Button deleteButton;



    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","EditLandlineAb");
    }


    @Override
    public void onApplyButton(ActionEvent event) {
        if (name.getText().length() > 50 || cena_miesieczna.getText().replaceAll("\\s","").isEmpty()
        || liczba_rat.getText().replaceAll("\\s","").isEmpty()
        || liczba_rat.getText().length() > 2 || name.getText().length() > 50) {
            InformAlert.informAlert("OOOPS\nWrong data!");
        } else {
            ButtonType buttonType = DecisionPanelCustom.decisionAlert("Na pewno chcesz dokonać edycji?\nTa Akcja jest nieodwracalna!");
            if(buttonType == ButtonType.YES) {
                landlineNet.setLandlineName(name.getText());
                landlineNet.setLandlineCabel(cabel.getValue().toString());
                landlineNet.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                landlineNet.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                landlineNet.setLandlineSpeed(downlink.getValue().toString());
                landlineNet.setServicesID(user.getSelectedService());
                landlineNet.updateRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditLandlineAb");
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
                            landlineNet.deleteRecord(connection);
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
        landlineNet = LandlineNet.getLandlineNet(connection,String.valueOf(user.getSelectedService()));

        name.setText(landlineNet.getLandlineName());
        cena_miesieczna.setText(String.valueOf(landlineNet.getServicesMonthPrice()));
        liczba_rat.setText(String.valueOf(landlineNet.getServicesDuration()));
        downlink.setValue(String.valueOf(landlineNet.getLandlineSpeed()));
        cabel.setValue(String.valueOf(landlineNet.getLandlineCabel()).toUpperCase());
        deleteButton.setOnAction(this::onDeleteButton);
    }

}
