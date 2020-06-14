package application.viewControllers.Perspective.Employee;

import application.AOD.User.Client;
import application.AOD.User.Employee;
import application.Alerts.InformAlert;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
import application.user.User;
import application.viewControllers.Perspective.AbstractEditV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeEditC extends AbstractEditV {
    Employee employee;


    @FXML
    protected TextField bankAccount;

    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeInfo","employeeEdit");
    }

    @Override
    protected void onPasswordChangeButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("EmployeeChangePassword","employeeEdit");
    }

    @Override
    protected void onAcceptButton(ActionEvent event) {
        if (name.getText().length() >40 || lastName.getText().length() >40 || bankAccount.getText().length() > 26
                || name.getText().replaceAll("\\s","").isEmpty()
                || lastName.getText().replaceAll("\\s","").isEmpty()
                || bankAccount.getText().replaceAll("\\s","").isEmpty()) {
            InformAlert.informAlert("OOOPS!\n Wrong data!");
        } else {
            Employee tmp = new Employee(employee);
            tmp.setFirstName(name.getText());
            tmp.setLastName(lastName.getText());
            tmp.setBirth(String.valueOf(birthday.getValue()));
            tmp.setGender(LayoutManager.genderFromScreen(gender.getValue()));
            tmp.setBankAccountNumber(Long.parseLong(bankAccount.getText()));

            tmp.getAddress_().setAddressCity(city.getText());
            tmp.getAddress_().setAddressStreet(street.getText());
            tmp.getAddress_().setAddressStreetNumber(streetNumber.getText());
            tmp.getAddress_().setAddressBuildingNumber(buildingNumber.getText());

            employee.updateEmployee(tmp);
            SceneControler.getInstance().setSceneWithClear("employeeInfo","employeeEdit");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        employee = User.getInstance().getEmployee();
        bankAccount.setText(String.valueOf(employee.getBankAccountNumber()));

        bankAccount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                bankAccount.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


    }
}
