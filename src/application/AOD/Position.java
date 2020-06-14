package application.AOD;

import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.resources.SQLQuery;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.ResultSet;

public class Position {
    int PositionID;
    String PositionName;
    String PositionDescription;

    public int getPositionID() {
        return PositionID;
    }

    public void setPositionID(int positionID) {
        PositionID = positionID;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getPositionDescription() {
        return PositionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        PositionDescription = positionDescription;
    }

    public static Position getPosition(int PositionID) {
        Position position = new Position();
        Connection connection = DBConectivity.getConnection();

        try {
            String sql = SQLQuery.GET_POSITION_ON_ID;
            ResultSet result = DBReader.getSQLResult(connection,sql,new String[]{String.valueOf(PositionID)});
            while(result.next()) {
                position.PositionID = Integer.parseInt(result.getString(1));
                position.PositionName = result.getString(2);
                position.PositionDescription = result.getString(3);
            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Position.class.getName());
        }

        return position;
    }

    /*
    public static Position getPosition(String email) {
        Position position = new Position();
        Connection connection = DBConectivity.getConnection();

        try {
            String sql = SQLQuery.GET_EMPLOYEE_ON_EMAIL;
            ResultSet result = DBReader.getSQLResult(connection,sql,new String[]{String.valueOf(email)});
            while(result.next()) {
                position.PositionID = Integer.parseInt(result.getString(1));
                position.PositionName = result.getString(2);
                position.PositionDescription = result.getString(3);
            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Position.class.getName());
        }

        return position;
    }

     */
}
