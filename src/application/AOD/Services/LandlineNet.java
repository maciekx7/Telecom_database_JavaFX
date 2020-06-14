package application.AOD.Services;

import application.AOD.User.Client;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class LandlineNet  extends Service{
    private int LandlineID;
    private String LandlineName;
    private String LandlineSpeed;
    private String LandlineCabel;

    public int getLandlineID() {
        return LandlineID;
    }

    public void setLandlineID(int landlineID) {
        LandlineID = landlineID;
    }

    public String getLandlineName() {
        return LandlineName;
    }

    public void setLandlineName(String landlineName) {
        LandlineName = landlineName;
    }

    public String getLandlineSpeed() {
        return LandlineSpeed;
    }

    public void setLandlineSpeed(String landlineSpeed) {
        LandlineSpeed = landlineSpeed;
    }

    public String getLandlineCabel() {
        return LandlineCabel;
    }

    public void setLandlineCabel(String landlineCabel) {
        LandlineCabel = landlineCabel;
    }



    public static ObservableList<LandlineNet> getAll(Connection connection) {
        ObservableList<LandlineNet> landlineNets = FXCollections.observableArrayList();
        try {
            String sql = SQLQuery.GET_ALL_LANDLINE;

            ResultSet result = DBReader.getSQLResult(connection,sql);
            while(result.next()) {
                LandlineNet landlineNet = new LandlineNet();
                landlineNet.oneLandlineNetFromDB(result);
                landlineNets.add(landlineNet);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, LandlineNet.class.getName());
        }
        return landlineNets;
     }

    public static LandlineNet getLandlineNet(Connection connection, String ServicesID) {
        LandlineNet landlineNet = null;
        try {
            String sql = SQLQuery.GET_LANDLINE;
            ResultSet result = DBReader.getSQLResult(connection, sql, new String[]{ServicesID});
            while(result.next()) {
                landlineNet = new LandlineNet();
                landlineNet.oneLandlineNetFromDB(result);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
        return landlineNet;
    }



    @Override
    public void updateRecord(Connection connection) {
        super.updateRecord(connection);
        params.add(LandlineName);
        params.add(String.valueOf(LandlineSpeed));
        params.add(LandlineCabel);
        params.add(String.valueOf(ServicesID));
        String sql = SQLQuery.UPDATE_LANDLINENET;
        DBReader.sendSQLQuery(connection,sql,params.toArray(new String[0]));
        params=null;
    }


    @Override
    public void newRecord(Connection connection) {
        //BEGIN SET TRANSACTION READ WRITE; INSERT INTO USLUGI values(SERVICE_ID.nextval,'1','4',?,?); INSERT INTO INTERNETY_MOBILNE VALUES(LANDNET_ID.nextval,?,?,?,SERVICE_ID.currval); COMMIT; END;
        String sql = SQLQuery.NEW_LANDNET;
        DBReader.sendSQLQuery(connection,sql,
                new String[]{
                        String.valueOf(ServicesMonthPrice),String.valueOf(ServicesDuration),
                        LandlineName,LandlineSpeed,LandlineCabel
        });
    }

    @Override
    public void deleteRecord(Connection connection) {
        String sql = SQLQuery.DELETE_LANDLINENET;
        DBReader.sendSQLQuery(connection,sql,new String[]{String.valueOf(ServicesID)});
    }

    public void oneLandlineNetFromDB(ResultSet result) {
        try {
            LandlineID = result.getInt(1);
            LandlineName = result.getString(2);
            LandlineSpeed = result.getString(3).replaceAll("\\s", "");
            LandlineCabel = result.getString(4).replaceAll("\\s","");
            ServicesID = result.getInt(5);
            ServicesOperatorID = result.getInt(7);
            ServicesTypeID = result.getInt(8);
            ServicesMonthPrice = result.getInt(9);
            ServicesDuration = result.getInt(10);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, LandlineNet.class.getName());
        }
    }



}
