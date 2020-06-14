package application.AOD;

import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class Address {
    private int AddressID;
    private String AddressCity;
    private String AddressStreet;
    private String AddressStreetNumber;
    private String AddressBuildingNumber;

    public Address() {}

    public Address(int id, String city, String street, String sNr, String bNr) {
        AddressID = id;
        AddressCity = city;
        AddressStreet = street;
        AddressStreetNumber = sNr;
        AddressBuildingNumber = bNr;
    }

    public Address(Address address) {
        AddressID = address.AddressID;
        AddressCity = address.AddressCity;
        AddressStreetNumber = address.AddressStreetNumber;
        AddressStreet = address.AddressStreet;
        AddressBuildingNumber = address.AddressBuildingNumber;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public String getAddressCity() {
        return AddressCity;
    }

    public void setAddressCity(String addressCity) {
        AddressCity = addressCity;
    }

    public String getAddressStreet() {
        return AddressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        AddressStreet = addressStreet;
    }

    public String getAddressStreetNumber() {
        return AddressStreetNumber;
    }

    public void setAddressStreetNumber(String addressStreetNumber) {
        AddressStreetNumber = addressStreetNumber;
    }

    public String getAddressBuildingNumber() {
        return AddressBuildingNumber;
    }

    public void setAddressBuildingNumber(String addressBuildingNumber) {
        AddressBuildingNumber = addressBuildingNumber;
    }

    public static ObservableList<Address> getAll(Connection connection) {
        ObservableList<Address> adressList = FXCollections.observableArrayList();
        System.out.println("method not implemented");
        return adressList;

    }

    public static Address getAddress(Connection connection, String id)  {
        Address address = new Address();

        try {
            String sql = SQLQuery.GET_ADDRESS;
            ResultSet result = DBReader.getSQLResult(connection, sql, new String[]{id});
            while(result.next()) {
                address.AddressID = result.getInt(1);
                address.AddressCity = result.getString(2);
                address.AddressStreet = result.getString(3);
                address.AddressStreetNumber = result.getString(4);
                address.AddressBuildingNumber = result.getString(5);
            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Address.class.getName());
        }
        return address;
    }

    public void updateAddress(Address address) {
        try {
            String sql = SQLQuery.UPDATE_ADDRESS;

            String[] params = new String[]{address.getAddressCity(),address.getAddressStreet(),address.getAddressStreetNumber(),address.getAddressBuildingNumber(),String.valueOf(address.getAddressID())};

            DBReader.sendSQLQuery(DBConectivity.getConnection(), sql, params);
            AddressStreet = address.getAddressStreet();
            AddressStreetNumber = address.getAddressStreetNumber();
            AddressCity = address.getAddressCity();
            AddressBuildingNumber = address.getAddressBuildingNumber();
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Address.class.getName());
        }
    }
}
