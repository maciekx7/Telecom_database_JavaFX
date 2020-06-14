package application.AOD.Services;

import application.AOD.User.Client;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class TV extends Service{
    private int TVID;
    private String TVName;
    private int TVCanals;
    private String TVNetflix;
    private String TVHBOgo;
    private String TVRecorder;
    private String TVMultiroom;

    public int getTVID() {
        return TVID;
    }

    public void setTVID(int TVID) {
        this.TVID = TVID;
    }

    public String getTVName() {
        return TVName;
    }

    public void setTVName(String TVName) {
        this.TVName = TVName;
    }

    public int getTVCanals() {
        return TVCanals;
    }

    public void setTVCanals(int TVCanals) {
        this.TVCanals = TVCanals;
    }

    public String getTVNetflix() {
        return TVNetflix;
    }

    public void setTVNetflix(String TVNetflix) {
        this.TVNetflix = TVNetflix;
    }

    public String getTVHBOgo() {
        return TVHBOgo;
    }

    public void setTVHBOgo(String TVHBOgo) {
        this.TVHBOgo = TVHBOgo;
    }

    public String getTVRecorder() {
        return TVRecorder;
    }

    public void setTVRecorder(String TVRecorder) {
        this.TVRecorder = TVRecorder;
    }

    public String getTVMultiroom() {
        return TVMultiroom;
    }

    public void setTVMultiroom(String TVMultiroom) {
        this.TVMultiroom = TVMultiroom;
    }


    public static ObservableList<TV> getAll(Connection connection) {
        ObservableList<TV> listTV = FXCollections.observableArrayList();
        String sql = SQLQuery.GET_ALL_TV;
        try {
            ResultSet result = DBReader.getSQLResult(connection,sql);
            String[] columnNames = DBReader.getColumnNames(result);
            while(result.next()) {
                TV tv = new TV();
                tv.oneTvFromDB(result);
                listTV.add(tv);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e,TV.class.getName());
        }
        return listTV;
    }



    public static TV getTV(Connection connection, String ServicesID) {
        TV tv = null;

        try {
            String sql = SQLQuery.GET_TV;
            ResultSet result = DBReader.getSQLResult(connection, sql, new String[]{String.valueOf(ServicesID)});
            while(result.next()) {
                tv = new TV();
                tv.oneTvFromDB(result);

            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
        return tv;
    }

    @Override
    public void updateRecord(Connection connection) {
        super.updateRecord(connection);
        params.add(TVName);
        params.add(String.valueOf(TVCanals));
        params.add(TVRecorder);
        params.add(TVMultiroom);
        params.add(TVHBOgo);
        params.add(TVNetflix);
        params.add(String.valueOf(ServicesID));
        String sql = SQLQuery.UPDATE_TV;
        DBReader.sendSQLQuery(connection,sql,params.toArray(new String[0]));
        params=null;
    }

    @Override
    public void newRecord(Connection connection) {
        //BEGIN SET TRANSACTION READ WRITE; INSERT INTO USLUGI values(SERVICE_ID.nextval,'1','3',?,?); INSERT INTO TELEWIZJE VALUES(TV_ID.nextval,?,?,?,?,?,?,SERVICE_ID.currval); COMMIT; END;
        String sql = SQLQuery.NEW_TV;
        DBReader.sendSQLQuery(connection,sql,
                new String[]{
                        String.valueOf(ServicesMonthPrice),String.valueOf(ServicesDuration),
                        TVName,String.valueOf(TVCanals),TVNetflix,TVHBOgo,TVRecorder,TVMultiroom
        });
    }

    @Override
    public void deleteRecord(Connection connection) {
        String sql = SQLQuery.DELETE_TVAB;
        DBReader.sendSQLQuery(connection,sql,new String[]{String.valueOf(ServicesID)});
    }

    private void oneTvFromDB(ResultSet result) {
        try {
            TVID = result.getInt(1);
            TVName = result.getString(2);
            TVCanals = result.getInt(3);
            TVNetflix = result.getString(4);
            TVHBOgo = result.getString(5);
            TVRecorder = result.getString(6);
            TVMultiroom = result.getString(7);
            ServicesID = result.getInt(8);
            ServicesOperatorID = result.getInt(10);
            ServicesTypeID = result.getInt(11);
            ServicesMonthPrice = result.getInt(12);
            ServicesDuration = result.getInt(13);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, TV.class.getName());
        }
    }

}
