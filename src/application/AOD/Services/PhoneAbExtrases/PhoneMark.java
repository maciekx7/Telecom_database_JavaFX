package application.AOD.Services.PhoneAbExtrases;

import application.AOD.Address;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.configReader.ConfigReader;
import application.resources.SQLQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhoneMark
{
    protected int PhoneMarkID;
    protected String PhoneMark;
    List<String> params;

    public int getPhoneMarkID() {
        return PhoneMarkID;
    }

    public void setPhoneMarkID(int phoneMarkID) {
        PhoneMarkID = phoneMarkID;
    }

    public String getPhoneMark() {
        return PhoneMark;
    }

    public void setPhoneMark(String phoneMark) {
        PhoneMark = phoneMark;
    }

    public static PhoneMark getphoneMark(Connection connection, String PhoneMarkID)  {
        PhoneMark phoneMark = new PhoneMark();

        try {
            String sql = SQLQuery.GET_DEVICEAB_MARK;
            ResultSet result = DBReader.getSQLResult(connection, sql, new String[]{PhoneMarkID});
            while(result.next()) {
                phoneMark.PhoneMarkID = result.getInt(1);
                phoneMark.PhoneMark = result.getString(2);

            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Address.class.getName());
        }
        return phoneMark;
    }


    public void updateRecord(Connection connection) {
    }

    public static ArrayList<String> getAll(Connection connection) {
        ArrayList<String> markList = new ArrayList<>();
        System.out.println("start");
        try {
            String sql = SQLQuery.GET_PHONE_MARKS;
            ResultSet result = DBReader.getSQLResult(connection,sql);
            while(result.next()) {
                markList.add(result.getString(1));
                System.out.println(result.getString(1));
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, PhoneMark.class.getName());
        }
        return markList;
    }
}
