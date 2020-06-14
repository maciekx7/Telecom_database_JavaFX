package application.AOD.Services;

import application.AOD.User.Client;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class MobileNet extends Service {
    private int MobileNetID;
    private String MobileNetName;
    private String MobileNetGb;  //TODO Sprawdź czy internet może być INF. If yes -> change to string or do sth with it
    private String MobileNetHappyH;



    public int getMobileNetID() {
        return MobileNetID;
    }

    public void setMobileNetID(int mobileNetID) {
        MobileNetID = mobileNetID;
    }

    public String getMobileNetName() {
        return MobileNetName;
    }

    public void setMobileNetName(String mobileNetName) {
        MobileNetName = mobileNetName;
    }

    public String getMobileNetGb() {
        return MobileNetGb;
    }

    public String getMobileNetHappyH() {
        return MobileNetHappyH;
    }

    public void setMobileNetHappyH(String mobileNetHappyH) {
        MobileNetHappyH = mobileNetHappyH;
    }

    public void setMobileNetGb(String mobileNetGb) {
        MobileNetGb = mobileNetGb;
    }

    public static ObservableList<MobileNet> getAll(Connection connection) {
        ObservableList<MobileNet> listMobileNet = FXCollections.observableArrayList();

        String sql = SQLQuery.GET_ALL_MOBILENET;

        try {

            ResultSet result = DBReader.getSQLResult(connection,sql);
            while(result.next()) {
                MobileNet mobileNet = new MobileNet();
                mobileNet.oneMobileNetFromDB(result);
                listMobileNet.add(mobileNet);
            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, MobileNet.class.getName());
        }
        return listMobileNet;
    }


    public static MobileNet getMobileNet(Connection connection, String ServicesID) {
        MobileNet mobileNet = null;
        try {
            String sql = SQLQuery.GET_MOBILENET;

            ResultSet result = DBReader.getSQLResult(connection,sql ,new String[]{ServicesID});
            while(result.next()) {
                mobileNet = new MobileNet();
                mobileNet.oneMobileNetFromDB(result);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
        return mobileNet;
    }

    @Override
    public void updateRecord(Connection connection) {
        super.updateRecord(connection);
        params.add(MobileNetName);
        params.add(MobileNetGb);
        params.add(MobileNetHappyH);
        params.add(String.valueOf(ServicesID));
        String sql = SQLQuery.UPDATE_MOBILENET;
        DBReader.sendSQLQuery(connection,sql,params.toArray(new String[0]));
        params=null;
    }


    @Override
    public void newRecord(Connection connection) {
        //BEGIN SET TRANSACTION READ WRITE; INSERT INTO USLUGI values(SERVICE_ID.nextval,'1','5',?,?); INSERT INTO INTERNETY_MOBILNE VALUES(PHONEAB_ID.nextval,?,?,?,SERVICE_ID.currval); COMMIT; END;
        String sql = SQLQuery.NEW_MOBNET;
        DBReader.sendSQLQuery(connection,sql,
                new String[]{
                        String.valueOf(ServicesMonthPrice),String.valueOf(ServicesDuration),
                        MobileNetName,MobileNetGb,MobileNetHappyH
        });
    }

    @Override
    public void deleteRecord(Connection connection) {
        String sql = SQLQuery.DELETE_MOBILENET;
        DBReader.sendSQLQuery(connection,sql,new String[]{String.valueOf(ServicesID)});
    }

    private void oneMobileNetFromDB(ResultSet result) {
        try {
            MobileNetID = result.getInt(1);
            MobileNetName = result.getString(2);
            MobileNetGb = result.getString(3);
            MobileNetHappyH = result.getString(4);
            ServicesID = result.getInt(5);
            ServicesOperatorID = result.getInt(7);
            ServicesTypeID = result.getInt(8);
            ServicesMonthPrice = result.getInt(9);
            ServicesDuration = result.getInt(10);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, MobileNet.class.getName());
        }
    }

}
