package application.AOD.Services;

import application.AOD.User.Client;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;


public class PhoneAb extends Service{
    private int PhoneAbID;
    private String PhoneAbName;
    private String PhoneAbMinutes;
    private String PhoneAbGigabs;
    private String PhoneAbSpotify;

    public int getPhoneAbID() {
        return PhoneAbID;
    }

    public void setPhoneAbID(int phoneAbID) {
        PhoneAbID = phoneAbID;
    }

    public String getPhoneAbName() {
        return PhoneAbName;
    }

    public void setPhoneAbName(String phoneAbName) {
        PhoneAbName = phoneAbName;
    }

    public String getPhoneAbMinutes() {
        return PhoneAbMinutes;
    }

    public void setPhoneAbMinutes(String phoneAbMinutes) {
        PhoneAbMinutes = phoneAbMinutes;
    }

    public String getPhoneAbGigabs() {
        return PhoneAbGigabs;
    }

    public void setPhoneAbGigabs(String phoneAbGigabs) {
        PhoneAbGigabs = phoneAbGigabs;
    }

    public String getPhoneAbSpotify() {
        return PhoneAbSpotify;
    }

    public void setPhoneAbSpotify(String phoneAbSpotify) {
        PhoneAbSpotify = phoneAbSpotify;
    }



    public static ObservableList<PhoneAb> getAll(Connection connection) {
        ObservableList<PhoneAb> listPhoneAb = FXCollections.observableArrayList();
        String sql = SQLQuery.GET_ALL_PHONEAB;
        try {
            ResultSet result = DBReader.getSQLResult(connection,sql);
            while(result.next()) {
                PhoneAb phoneAb = new PhoneAb();
                phoneAb.onePhoneAbFromDB(result);
                listPhoneAb.add(phoneAb);
            }
        }catch (Exception e) {
            ErrorAlert.errorAlert(e,PhoneAb.class.getName());
        }
        return listPhoneAb;
    }


    public static PhoneAb getPhoneAb(Connection connection, String ServicesID) {
        PhoneAb phoneAb = null;
        try {
            String sql = SQLQuery.GET_PHONEAB;
            ResultSet result = DBReader.getSQLResult(connection, sql,new String[]{String.valueOf(ServicesID)});
            while(result.next()) {
                phoneAb = new PhoneAb();
                phoneAb.onePhoneAbFromDB(result);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
        return phoneAb;
    }

    @Override
    public void updateRecord(Connection connection) {
        super.updateRecord(connection);
        params.add(PhoneAbName);
        params.add(PhoneAbMinutes);
        params.add(PhoneAbGigabs);
        params.add(PhoneAbSpotify);
        params.add(String.valueOf(ServicesID));
        String sql = SQLQuery.UPDATE_PHONEAB;
        DBReader.sendSQLQuery(connection,sql, params.toArray(new String[0]));
        params=null;
    }

    @Override
    public void newRecord(Connection connection) {
        //BEGIN SET TRANSACTION READ WRITE; INSERT INTO USLUGI values(SERVICE_ID.nextval,?,?,?,?); INSERT INTO ABONAMENTY_KOMORKOWE VALUES(PHONEAB_ID.nextval,?,?,?,?,SERVICE_ID.currval); COMMIT; END;
        String sql = SQLQuery.NEW_PHONEAB;
        DBReader.sendSQLQuery(connection,sql,
                new String[]{
                        String.valueOf(ServicesMonthPrice),String.valueOf(ServicesDuration),
                        PhoneAbName,PhoneAbMinutes,PhoneAbGigabs,PhoneAbSpotify
        });
    }

    @Override
    public void deleteRecord(Connection connection) {
        String sql = SQLQuery.DELETE_PHONEAB;
        DBReader.sendSQLQuery(connection,sql,new String[]{String.valueOf(ServicesID)});
    }

    private void onePhoneAbFromDB(ResultSet result) {
        try {
            PhoneAbID = result.getInt(1);
            PhoneAbName = result.getString(2);
            PhoneAbMinutes = result.getString(3).replaceAll("\\s","");
            PhoneAbGigabs = result.getString(4).replaceAll("\\s","");
            PhoneAbSpotify = result.getString(5);
            ServicesID = result.getInt(6);
            ServicesOperatorID = result.getInt(8);
            ServicesTypeID = result.getInt(9);
            ServicesMonthPrice = result.getInt(10);
            ServicesDuration = result.getInt(11);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, PhoneAb.class.getName());
        }
    }



}
