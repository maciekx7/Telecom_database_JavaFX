package application.AOD.User;

import application.AOD.Address;
import application.AOD.Buildings.AbstractBuilding;
import application.AOD.Operator;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import application.tools.date.DateTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client extends AbstractUser {
    protected String JoiningDate;
    protected int ServicesCount;
    protected int ServicesPrice;

    public String getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        JoiningDate = joiningDate;
    }

    public int getServicesCount() {
        return ServicesCount;
    }

    public void setServicesCount(int servicesCount) {
        ServicesCount = servicesCount;
    }

    public int getServicesPrice() {
        return ServicesPrice;
    }

    public void setServicesPrice(int servicesPrice) {
        ServicesPrice = servicesPrice;
    }

    @Override
    public String getPassword(Connection connection) {
        String sql = "";
        //return DBReader.getSQLResult(connection,sql,new String[]{});
        return "";
    }

    public Client() { }

    public Client(Client client) {
        ID = client.ID;
        FirstName = client.FirstName;
        LastName = client.LastName;
        Phone = client.Phone;
        Email = client.Email;
        Pesel = client.Pesel;
        Birth = client.Birth;
        Gender = client.Gender;
        JoiningDate = client.JoiningDate;
        OperatorID = client.OperatorID;
        AddressID = client.AddressID;
        ServicesCount = client.ServicesCount;
        ServicesPrice = client.ServicesPrice;
        Address_ = new Address(client.Address_);
        Operator_ = client.Operator_; //TODO konstruktor kopiujacy
    }




    public static ObservableList<Client> getAll(Connection connection) {
        ObservableList<Client> clientsList = FXCollections.observableArrayList();

        System.out.println("method not yet implemented");

        return clientsList;
    }

    public static Client getClient(Connection connection, String email, String password) {
        Client client = null;

        try {
            String sql = SQLQuery.GET_CLIENT;
            String[] params = new String[]{email,password};
            ResultSet result = DBReader.getSQLResult(connection, sql,params);
            while(result.next()) {
                client = new Client();
                client.oneRecordFromDB(connection,result);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
        return client;
    }

    public void updateClient(Client client) {
        try {
            String sql = SQLQuery.UPDATE_CLIENT;
            String[] params = new String[]{client.getFirstName(),client.getLastName(), client.getBirth(),client.getGender(),String.valueOf(client.getID())};
            DBReader.sendSQLQuery(DBConectivity.getConnection(),sql,params);
            update(client);

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
    }

    public void buyService(String ServiceID) {
        try {
            Connection connection = DBConectivity.getConnection();
            String sql = SQLQuery.BUY_NEW_SERVICE;
            DBReader.sendSQLQuery(connection, sql, new String[]{String.valueOf(this.ID),String.valueOf(ServiceID)});
            ResultSet services = DBReader.getSQLResult(connection, SQLQuery.CLIENT_SERVICES_COUNT, new String[]{String.valueOf(this.getID())});
            while(services.next()) {
                this.ServicesPrice = services.getInt(1);
                this.ServicesCount = services.getInt(2);

            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
    }

    @Override
    protected void oneRecordFromDB(Connection connection, ResultSet result) {
        super.oneRecordFromDB(connection, result);
        try {
            ID = result.getInt(10);
            JoiningDate = result.getString(11);
            ResultSet services = DBReader.getSQLResult(connection, SQLQuery.CLIENT_SERVICES_COUNT, new String[]{String.valueOf(ID)});
            while(services.next()) {
                ServicesPrice = services.getInt(1);
                ServicesCount = services.getInt(2);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
    }

    @Override
    public boolean updatePassword(Connection connection,String password, String oldPassword) {
        try {
            ResultSet result = DBReader.getSQLResult(connection,SQLQuery.GET_CLIENT_PASSWORD, new String[]{String.valueOf(ID), oldPassword});
            if(result.next()) {
                String sql = SQLQuery.UPDATE_CLIENT_PASSWORD;
                DBReader.sendSQLQuery(connection, sql, new String[]{password, String.valueOf(ID), oldPassword});
                return true;
            }
            return false;
        } catch (Exception e) {//SQLException e//) {
            return false;
        }
    }

    protected void update(Client client) {
        try {
            update((AbstractUser) client);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e,Client.class.getName());
        }

    }

}