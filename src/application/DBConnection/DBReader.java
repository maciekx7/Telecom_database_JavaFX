package application.DBConnection;

import application.Alerts.ErrorAlert;


import java.sql.*;

public class DBReader {

    public static ResultSet getSQLResult(Connection connection, String sql) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(sql);
        } catch(Exception e) {
            ErrorAlert.errorAlert(e, DBReader.class.getName());
        }
        return result;
    }

    public static ResultSet getSQLResult(Connection connection, String sql, String[] params) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for(int i=1; i<=params.length;i++) {
                statement.setString(i,params[i-1]);
            }
            resultSet = statement.executeQuery();
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DBReader.class.getName());
        }
        return resultSet;
    }

    public static void sendSQLQuery(Connection connection, String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DBReader.class.getName());
        }
    }

    public static void sendSQLQuery(Connection connection, String sql, String[] params) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for(int i=1; i<=params.length; i++) {
                statement.setString(i,params[i-1]);
            }
            statement.executeQuery();
            statement.close();
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DBReader.class.getName());
        }
    }
//cos
    public static String[] getColumnNames(ResultSet result) {
        String[] columnNames = null;

        try {
            ResultSetMetaData metaData = result.getMetaData();
            int counter = metaData.getColumnCount();
            columnNames = new String[counter];
            for(int i=1; i<= counter; i++) {
                columnNames[i-1] = metaData.getColumnLabel(i);
            }
        } catch(Exception e) {
            ErrorAlert.errorAlert(e, DBReader.class.getName());
        }

        return columnNames;
    }
}
