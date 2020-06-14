package application.AOD;

import application.DBConnection.DBReader;
import application.LayoutManager.LayoutManager;
import application.Alerts.ErrorAlert;
import application.tools.date.DateTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.resources.SQLQuery;

import java.sql.Connection;
import java.sql.ResultSet;

public class ServicesTransactions {
    //cos

    private String ServicesTransactionsType;
    private Integer ServicesTransactionsPrice;
    private Integer ServicesTransactionsDuration;
    private String ServicesTransactionsDate;
    private Integer ServicesID; //TODO no inaczej to powinno wygladac xD

    public Integer getServicesTransactionsPrice() {
        return  ServicesTransactionsPrice;
    }

    public void setServicesTransactionsPrice(Integer getServicesTransactionsPrice) {
        this.ServicesTransactionsPrice = getServicesTransactionsPrice;
    }

    public Integer getServicesTransactionsDuration() {
        return  ServicesTransactionsDuration;
    }

    public void setServicesTransactionsDuration(Integer getServicesTransactionsDuration) {
        this.ServicesTransactionsDuration = getServicesTransactionsDuration;
    }

    public String getServicesTransactionsDate() {
        return  ServicesTransactionsDate;
    }

    public void setServicesTransactionsDate(String getServicesTransactionsDate) {
        this.ServicesTransactionsDate = getServicesTransactionsDate;
    }

    public String getServicesTransactionsType() {
        return  ServicesTransactionsType;
    }

    public void getServicesTransactionsType(String getServicesTransactionsType) {
        this.ServicesTransactionsType = ServicesTransactionsType;
    }

    public Integer getServicesID() {
        return  ServicesID;
    }

    public void getServicesTransactionsID(String getServicesTransactionsID) {
        this.ServicesID = ServicesID;
    }




    public ObservableList<ServicesTransactions> getAll(Connection connection, String email) {
        ObservableList<ServicesTransactions> listServicesTransactions = FXCollections.observableArrayList();

        String sql = SQLQuery.SELECT_USLUGI_KLIENTA;

        try {
            ResultSet resultSet = DBReader.getSQLResult(connection,sql, new String[]{email});
            while(resultSet.next()) {
                ServicesTransactions servicesTransactions = new ServicesTransactions();
                servicesTransactions.ServicesTransactionsType = LayoutManager
                                                                    .serviceType(resultSet
                                                                    .getString(1)
                                                                    .replaceAll("\\s",""));
                servicesTransactions.ServicesTransactionsPrice = resultSet.getInt(2);
                servicesTransactions.ServicesTransactionsDuration = resultSet.getInt(3);
                servicesTransactions.ServicesTransactionsDate = DateTool.getStrDateFromOracleDB(resultSet.getString(4));


                servicesTransactions.ServicesID = resultSet.getInt(5);
                listServicesTransactions.add(servicesTransactions);
            }
        } catch(Exception e) {
            ErrorAlert.errorAlert(e, ServicesTransactions.class.getName());
        }
        return listServicesTransactions;
    }
}
