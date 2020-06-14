package application.viewControllers.Office;

import application.AOD.Salary;
import application.AOD.User.Employee;
import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.sceneControler.SceneControler;
import application.user.User;
import application.viewControllers.Shop.ShopC;
import com.sun.javafx.tk.ScreenConfigurationAccessor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class OfficeStaffViewC implements Initializable {
    ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    User user = User.getInstance();
    Connection connection = DBConectivity.getConnection();
    @FXML
    private Label mainLabel;
    @FXML
    private Button exitButton;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> lastName;
    @FXML
    private TableColumn<Employee, String> phone;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, String> position;
    @FXML
    private TextField searchField;

    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("OfficeDetails","OfficeStaff");
    }

    private void officeEmployees() {

        try {
            employeeList = Employee.getEmployees(connection,user.getEmployee().getOfficeID());
            name.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getFirstName())
            );
            lastName.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getLastName()))
            );
            phone.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getPhone()))
            );
            email.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getEmail())));
            position.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getPosition().getPositionName())));

            //SEARCH ENGINE------
            FilteredList<Employee> filteredList = new FilteredList<>(employeeList, b-> true);

            searchField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(emp -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCastFilter = newValue.toLowerCase();

                    if(String.valueOf(emp.getLastName()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(emp.getFirstName()).toLowerCase().indexOf(lowerCastFilter) != -1 ) {
                        return true;
                    } else if(String.valueOf(emp.getPhone()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(emp.getEmail()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(emp.getPosition().getPositionName()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<Employee> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(tableView.comparatorProperty());

            //ADD SEARCHED (or All) values
            tableView.setItems(sortedList);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ShopC.class.getName());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnAction(this::onBackButton);
        officeEmployees();
    }
}
