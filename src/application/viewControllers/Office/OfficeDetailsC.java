package application.viewControllers.Office;

import application.AOD.Buildings.Office;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OfficeDetailsC extends AbstractWorkPlace {

    @FXML
    protected Label specialization;



    @Override
    protected void onBackButton(ActionEvent event) {
        super.onBackButton(event);
        SceneControler.getInstance().setSceneWithClear("employeeInfo","OfficeDetails");
    }

    @Override
    protected void onEmployeeView(ActionEvent event) {
        super.onEmployeeView(event);
        SceneControler.getInstance().setSceneWithClear("OfficeStaff","OfficeDetails");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        Office office = Office.getOffice(User.getInstance().getEmployee().getOfficeID());

        name.setText(office.getName());
        specialization.setText(office.getSpecialization());
        city.setText(office.getAddress().getAddressCity());
        street.setText(office.getAddress().getAddressStreet());
        streenNr.setText(office.getAddress().getAddressStreetNumber());
        phone.setText(LayoutManager.phoneNumberToScreen(String.valueOf(office.getPhone())));
        email.setText(office.getEmail());

    }
}
