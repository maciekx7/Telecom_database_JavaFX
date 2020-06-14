package application.viewControllers;

import application.AOD.Services.PhoneAb;
import application.AOD.Services.ServiceType;
import application.AOD.Services.TV;
import application.DBConnection.DBConectivity;
import application.AOD.*;
import application.sceneControler.SceneControler;
import application.tools.date.DateTool;
import application.user.User;
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

public class ServicesViewC implements Initializable {
    User user = User.getInstance();



//cos
    private SceneControler sceneControler = SceneControler.getInstance();

    private Connection connection;

    private ObservableList<ServicesTransactions> listElement = FXCollections.observableArrayList();

    //sprawdzam
    private ObservableList<PhoneAb> listEl2 = FXCollections.observableArrayList();
    private ObservableList<TV> listTV = FXCollections.observableArrayList();
    //

    @FXML
    protected TextField search;

    @FXML
    private Button exitButton;






    @FXML
    private TableView<ServicesTransactions> tableView;

    @FXML
    private TableColumn<ServicesTransactions,String> dataZawarcia;

    @FXML
    private TableColumn<ServicesTransactions, String> typUslugi;

    @FXML
    private TableColumn<ServicesTransactions, String> cena;

    @FXML
    private TableColumn<ServicesTransactions, String> okres_zobowiazan;


    private void loadServices() {
        ServicesTransactions servicesTransactions = new ServicesTransactions();
        listElement = servicesTransactions.getAll(connection,user.getEmail());
        listEl2 = PhoneAb.getAll(connection);
        listTV = TV.getAll(connection);

        typUslugi.setCellValueFactory(c->
                new SimpleStringProperty(c.getValue().getServicesTransactionsType())
        );
        cena.setCellValueFactory(c->
                new SimpleStringProperty(String.valueOf(c.getValue().getServicesTransactionsPrice()))
        );
        okres_zobowiazan.setCellValueFactory( c->
                new SimpleStringProperty(String.valueOf(c.getValue().getServicesTransactionsDuration()))
        );
        dataZawarcia.setCellValueFactory(c->
                new SimpleStringProperty(DateTool.getDateWithMonthNames.YearFirst(c.getValue().getServicesTransactionsDate()))
        );


        FilteredList<ServicesTransactions> filteredList = new FilteredList<>(listElement, b-> true);

        search.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(transactions -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCastFilter = newValue.toLowerCase();

                if(String.valueOf(transactions.getServicesTransactionsType()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                    return true;
                } else if(String.valueOf(transactions.getServicesTransactionsPrice()).toLowerCase().indexOf(lowerCastFilter) != -1 ) {
                    return true;
                } else if(transactions.getServicesTransactionsDate().toLowerCase().indexOf(lowerCastFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<ServicesTransactions> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

    }

    private void exitButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientBasicInfo","services");
    }


    private void onRowClick(ServicesTransactions transaction) {
        String transactionType = transaction.getServicesTransactionsType();

        ServiceType.getServicesTypes(connection);

        user.setSelectedService(transaction.getServicesID()); //TODO id uslugi
        user.setServiceDate(transaction.getServicesTransactionsDate());

        if(transactionType.contains(ServiceType.PHONE_AB)) {
            sceneControler.setSceneWithClear("PhoneAb","services");
        } else if(transactionType.contains(ServiceType.DEVICE_AB)) {
            sceneControler.setSceneWithClear("DeviceAb","services");
        } else if(transactionType.contains(ServiceType.TV_AB)) {
            sceneControler.setSceneWithClear("TvAb","services");
        } else if(transactionType.contains(ServiceType.INT_ST_AB)) {
            sceneControler.setSceneWithClear("landlineAb","services");
        } else if(transactionType.contains(ServiceType.INT_MOB_ABD)) {
            sceneControler.setSceneWithClear("MobileNetAb","services");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection =  DBConectivity.getConnection();
        loadServices();

        exitButton.setOnAction(this::exitButton);

        tableView.setRowFactory(tv -> {
            TableRow<ServicesTransactions> row = new TableRow<>();
            row.setOnMouseClicked(event_ -> {
                if(event_.getClickCount() == 2 && (!row.isEmpty()))  {
                    ServicesTransactions rowData = row.getItem();
                    onRowClick(rowData);
                }
            });
            return row;
        });
    }
}
