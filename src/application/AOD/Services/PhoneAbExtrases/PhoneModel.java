package application.AOD.Services.PhoneAbExtrases;

import application.AOD.Address;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhoneModel extends PhoneMark{
    private int PhoneModelID;
    private String PhoneModelName;
    private String PhoneModelSize;
    private String PhoneModelPort;
    private String PhoneModelJack;
    private int PhoneModelCamera;
    private String PhoneModelInduction;

    public int getPhoneModelID() {
        return PhoneModelID;
    }

    public void setPhoneModelID(int phoneModelID) {
        PhoneModelID = phoneModelID;
    }

    public String getPhoneModelName() {
        return PhoneModelName;
    }

    public void setPhoneModelName(String phoneModelName) {
        PhoneModelName = phoneModelName;
    }

    public String getPhoneModelSize() {
        return PhoneModelSize;
    }

    public void setPhoneModelSize(String phoneModelSize) {
        PhoneModelSize = phoneModelSize;
    }

    public String getPhoneModelPort() {
        return PhoneModelPort;
    }

    public void setPhoneModelPort(String phoneModelPort) {
        PhoneModelPort = phoneModelPort;
    }

    public String getPhoneModelJack() {
        return PhoneModelJack;
    }

    public void setPhoneModelJack(String phoneModelJack) {
        PhoneModelJack = phoneModelJack;
    }

    public int getPhoneModelCamera() {
        return PhoneModelCamera;
    }

    public void setPhoneModelCamera(int phoneModelCamera) {
        PhoneModelCamera = phoneModelCamera;
    }

    public String getPhoneModelInduction() {
        return PhoneModelInduction;
    }

    public void setPhoneModelInduction(String phoneModelInduction) {
        PhoneModelInduction = phoneModelInduction;
    }




    public static PhoneModel getPhoneModel(Connection connection, String ModelID)  {
        PhoneModel phoneModel = new PhoneModel();
        String sql = SQLQuery.GET_DEVICEAB_MODEL;
        try {
            ResultSet result = DBReader.getSQLResult(connection, sql, new String[]{String.valueOf(ModelID)});
            while(result.next()) {
                phoneModel.PhoneModelID = result.getInt(1);
                phoneModel.PhoneModelName = result.getString(2);
                phoneModel.PhoneModelSize = result.getString(3);
                phoneModel.PhoneModelPort = result.getString(4).replaceAll("\\s","");
                phoneModel.PhoneModelJack = result.getString(5).replaceAll("\\s","");
                phoneModel.PhoneModelCamera = result.getInt(6);
                phoneModel.PhoneModelInduction = result.getString(7);
                phoneModel.PhoneMarkID = result.getInt(8);
                phoneModel.PhoneMark = result.getString(10);

                //phoneModel.phoneMark = PhoneMark.getphoneMark(connection, String.valueOf(phoneModel.PhoneModelMarkID));

            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Address.class.getName());
        }
        return phoneModel;
    }

    @Override
    public void updateRecord(Connection connection) {
        params = new ArrayList<>();
        params.add(PhoneModelName);
        params.add(PhoneModelSize);
        params.add(PhoneModelPort);
        params.add(PhoneModelJack);
        params.add(String.valueOf(PhoneModelCamera));
        params.add(PhoneModelInduction);
        params.add(PhoneMark);
        params.add(String.valueOf(PhoneModelID));
        String sql = SQLQuery.UPDATE_PHONE_MODEL;
        DBReader.sendSQLQuery(connection,sql,params.toArray(new String[0]));
        params=null;
    }

}
