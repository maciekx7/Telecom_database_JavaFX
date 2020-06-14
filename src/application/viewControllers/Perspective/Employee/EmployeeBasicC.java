package application.viewControllers.Perspective.Employee;

import application.AOD.User.Employee;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
import application.user.User;
import application.viewControllers.Perspective.AbstractBasicC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeBasicC extends AbstractBasicC {
    @FXML
    private Button services;
    @FXML
    private Button salary;
    @FXML
    private Label office;
    @FXML
    private Label position;


    Employee employee;

    @Override
    protected void detailsInfoView(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeInfo","employeeBasicInfo");
    }

    protected void catalogView(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Catalog","employeeBasicInfo");
    }

    protected void salaryView(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("Salary","employeeBasicInfo");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        services.setOnAction(this::catalogView);
        salary.setOnAction(this::salaryView);

        employee = User.getInstance().getEmployee();
        position.setText(employee.getPosition().getPositionName());
        office.setText(employee.getOffice().getName());

    }
}
