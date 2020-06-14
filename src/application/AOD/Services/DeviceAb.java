package application.AOD.Services;

import application.AOD.Services.PhoneAbExtrases.PhoneModel;
import application.AOD.User.Client;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class DeviceAb extends Service{

    private int DeviceAbID;
    private int DeviceAbCost;
    private int DeviceModelID;
    private PhoneModel phoneModel;

    public DeviceAb() {
        phoneModel = new PhoneModel();
    }

    public int getDeviceAbID() {
        return DeviceAbID;
    }

    public void setDeviceAbID(int deviceAbID) {
        DeviceAbID = deviceAbID;
    }

    public int getDeviceAbCost() {
        return DeviceAbCost;
    }

    public void setDeviceAbCost(int deviceAbCost) {
        DeviceAbCost = deviceAbCost;
    }

    public int getDeviceModelID() {
        return DeviceModelID;
    }

    public void setDeviceModelID(int deviceModelID) {
        DeviceModelID = deviceModelID;
    }

    public PhoneModel getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(PhoneModel phoneModel) {
        this.phoneModel = phoneModel;
    }


    public static DeviceAb deviceAb(Connection connection, String PhoneModelID) {
        DeviceAb deviceAb = null;

        try {
            String sql = SQLQuery.GET_DEVICEAB;
            ResultSet result = DBReader.getSQLResult(connection, sql,new String[]{PhoneModelID});
            while(result.next()) {
                deviceAb = new DeviceAb();
                deviceAb.oneDeviceAbFromDB(result);
                deviceAb.phoneModel = PhoneModel.getPhoneModel(connection, String.valueOf(deviceAb.getDeviceModelID()));
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
        return deviceAb;
    }

    public static ObservableList<DeviceAb> getAll(Connection connection) {
        ObservableList<DeviceAb> deviceList = FXCollections.observableArrayList();
        String sql = SQLQuery.GET_ALL_DEVICEAB;
        try {
            ResultSet result = DBReader.getSQLResult(connection,sql);
            while(result.next()) {
                DeviceAb deviceAb = new DeviceAb();
                deviceAb.oneDeviceAbFromDB(result);
                deviceAb.phoneModel = PhoneModel.getPhoneModel(connection, String.valueOf(deviceAb.getDeviceModelID()));
                deviceList.add(deviceAb);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DeviceAb.class.getName());
        }
        return deviceList;
    }

    public void newRecord(Connection connection) {
        /**
        BEGIN
        SET TRANSACTION READ WRITE;
        INSERT INTO USLUGI
        values(SERVICE_ID.nextval,'1','2',?,?);
        INSERT INTO MODELE_TELEFONOW
        VALUES(PHONE_MODEL.nextval,?,?,?,?,?,?,(SELECT ID_MARKI_TELEFONU FROM MARKI_TELEFONOW WHERE MARKA_TELEFONU=?));
        insert into ABONAMENTY_SPRZETOWE
        VALUES(DEVICE_AB.nextval,?,SERVICE_ID.currval,PHONE_MODEL.currval);
        COMMIT;
        END;
         */
        String sql = SQLQuery.NEW_DEVICEAB;
        DBReader.sendSQLQuery(connection,sql,
                new String[]{
                        String.valueOf(ServicesMonthPrice),String.valueOf(ServicesDuration),
                        phoneModel.getPhoneModelName(),phoneModel.getPhoneModelSize(),phoneModel.getPhoneModelPort(),
                        phoneModel.getPhoneModelJack(),String.valueOf(phoneModel.getPhoneModelCamera()),phoneModel.getPhoneModelInduction(),
                        phoneModel.getPhoneMark(),
                        String.valueOf(DeviceAbCost)
                });
    }


    @Override
    public void updateRecord(Connection connection) {
        super.updateRecord(connection);
        String sql = SQLQuery.UPDATE_DEVICEAB;
        params.add(String.valueOf(DeviceAbCost));
        params.add(String.valueOf(ServicesID));
        DBReader.sendSQLQuery(connection,sql,params.toArray(new String[0]));
        phoneModel.updateRecord(connection);
        params=null;
    }

    @Override
    public void deleteRecord(Connection connection) {
        String sql = SQLQuery.DELETE_DEVICEAB;
        DBReader.sendSQLQuery(connection,sql,new String[]{String.valueOf(ServicesID),String.valueOf(phoneModel.getPhoneModelID())});
    }

    private void oneDeviceAbFromDB(ResultSet result) {
        try {
            DeviceAbID = result.getInt(1);
            DeviceAbCost = result.getInt(2);
            ServicesID = result.getInt(3);
            DeviceModelID = result.getInt(4);
            ServicesOperatorID = result.getInt(6);
            ServicesTypeID = result.getInt(7);
            ServicesMonthPrice = result.getInt(8);
            ServicesDuration = result.getInt(9);

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DeviceAb.class.getName());
        }
    }
}
