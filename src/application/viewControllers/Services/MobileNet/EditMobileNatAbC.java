package application.viewControllers.Services.MobileNet;

import application.AOD.Services.MobileNet;
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

public class EditMobileNatAbC extends AbstractEditMobileNetC {

    @FXML
    private Button deleteButton;


    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","EditMobileNetAb");
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
                mobileNet.setServicesID(user.getSelectedService());
                mobileNet.setMobileNetName(name.getText());
                mobileNet.setMobileNetGb(limit.getValue().toString());
                mobileNet.setMobileNetHappyH(LayoutManager.TFfromScreen(happyHours.getValue().toString()));
                mobileNet.setServicesDuration(Integer.parseInt(liczba_rat.getText()));
                mobileNet.setServicesMonthPrice(Integer.parseInt(cena_miesieczna.getText()));
                mobileNet.updateRecord(connection);
                SceneControler.getInstance().setSceneWithClear("Catalog","EditMobileNetAb");
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
                        mobileNet.deleteRecord(connection);
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

        mobileNet = MobileNet.getMobileNet(connection,String.valueOf(user.getSelectedService()));

        name.setText(mobileNet.getMobileNetName());
        liczba_rat.setText(String.valueOf(mobileNet.getServicesDuration()));
        cena_miesieczna.setText(String.valueOf(mobileNet.getServicesMonthPrice()));
        limit.setValue(String.valueOf(mobileNet.getMobileNetGb()));
        happyHours.setValue(LayoutManager.TF(mobileNet.getMobileNetHappyH(),"e"));

        deleteButton.setOnAction(this::onDeleteButton);
    }

}
