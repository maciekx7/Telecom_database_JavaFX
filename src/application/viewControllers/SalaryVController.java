package application.viewControllers;

import application.AOD.Salary;
import application.AOD.Services.PhoneAb;
import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.sceneControler.SceneControler;
import application.tools.date.DateTool;
import application.user.User;
import application.viewControllers.Shop.ShopC;
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

public class SalaryVController implements Initializable {

    ObservableList<Salary> salaryList = FXCollections.observableArrayList();
    User user = User.getInstance();
    Connection connection = DBConectivity.getConnection();

    @FXML
    private Label mainLabel;
    @FXML
    private Button exitButton;
    @FXML
    private TableView<Salary> tableView;
    @FXML
    private TableColumn<Salary,String> date;
    @FXML
    private TableColumn<Salary,String> brutto;
    @FXML
    private TableColumn<Salary,String> netto;
    @FXML
    private TableColumn<Salary,String> premia;
    @FXML
    private TextField searchField;

    private void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeBasicInfo","Salary");
    }

    private void getSalary() {

        try {
            salaryList = Salary.getEmployeeSalarys(connection,user.getEmployee().getID());
            date.setCellValueFactory(c->
                    new SimpleStringProperty(DateTool.getDateWithMonthNames.YearFirst(c.getValue().getSalaryDate()))
            );
            brutto.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getSalaryBrutto()))
            );
            netto.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getSalaryNetto()))
            );
            premia.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getExtraSalary())));

            //SEARCH ENGINE------
            FilteredList<Salary> filteredList = new FilteredList<>(salaryList, b-> true);

            searchField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(salary -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCastFilter = newValue.toLowerCase();

                    if(String.valueOf(salary.getSalaryBrutto()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(salary.getSalaryNetto()).toLowerCase().indexOf(lowerCastFilter) != -1 ) {
                        return true;
                    } else if(salary.getSalaryDate().toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<Salary> sortedList = new SortedList<>(filteredList);

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

        getSalary();
    }
}
