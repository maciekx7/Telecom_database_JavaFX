package application.AOD;

import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.resources.SQLQuery;
import application.tools.date.DateTool;

import java.sql.Connection;
import java.sql.ResultSet;

public class Operator {
    protected int ID;
    protected String Name;
    protected String FoundationDate;
    protected int AddressID;
    protected Address address;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFoundationDate() {
        return FoundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        FoundationDate = foundationDate;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Operator getCompany(Connection connection, String CompanyID) {
        Operator operator = new Operator();
        try {
            String sql = SQLQuery.GET_COMPANY;
            ResultSet result = DBReader.getSQLResult(connection,sql,new String[]{CompanyID});
            oneRecordFromDB(connection,result);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Operator.class.getName());
        }
        return operator;
    }

    private void oneRecordFromDB(Connection connection, ResultSet result) {
        try {
            ID = result.getInt(1);
            Name = result.getString(2);
            FoundationDate = DateTool.getStrDateFromOracleDB(result.getString(3));
            AddressID = result.getInt(4);
            address = Address.getAddress(connection,String.valueOf(AddressID));

        }catch (Exception e) {
            ErrorAlert.errorAlert(e, Operator.class.getName());
        }

    }
}
