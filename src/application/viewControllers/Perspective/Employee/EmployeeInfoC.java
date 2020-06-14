package application.viewControllers.Perspective.Employee;

import application.AOD.User.Employee;
import application.sceneControler.SceneControler;
import application.user.User;
import application.viewControllers.Perspective.AbstractInfoV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeInfoC extends AbstractInfoV {
    Employee employee = User.getInstance().getEmployee();
    @FXML
    private Button officeDetailsButton;
    @FXML
    private Label bankAccount;
    @FXML
    private Label officeEmail;
    @FXML
    private Label position;
    @FXML
    private Label officeName;

    @Override
    protected void onEdit(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeEdit","employeeInfo");
    }

    @Override
    protected void onBack(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeBasicInfo","employeeInfo");
    }

    protected void onOfficeDetails(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("OfficeDetails","employeeInfo");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        officeDetailsButton.setOnAction(this::onOfficeDetails);
        officeName.setText(employee.getOffice().getName());
        officeEmail.setText(employee.getOffice().getEmail());
        //position.setText(employee.getPosition().getPositionDescription());
        position.setText(employee.getPosition().getPositionName());

        bankAccount.setText(String.valueOf(employee.getBankAccountNumber()));
    }
}
