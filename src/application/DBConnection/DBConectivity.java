package application.DBConnection;
import application.Alerts.ErrorAlert;

import java.sql.*;

public class DBConectivity {

    private DBConectivity() {
        if (DBConectivity.Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    public static DBConectivity getInstance() {
        return DBConectivity.Holder.INSTANCE;
    }

    private static class Holder {
        private static final DBConectivity INSTANCE = new DBConectivity();
    }


    private static Connection connection = null;

    private void connect() {

        String DB_ULR = "Your Database link";
        String DB_USER = "Your nickname";
        String DB_PASSWORD = "Your password";
        try {
            connection = DriverManager.getConnection(DB_ULR,DB_USER,DB_PASSWORD);
            System.out.println("conneced");
        } catch(Exception e) {
            ErrorAlert.errorAlert(e, DBConectivity.class.getName());
        }
    }



    public static Connection getConnection() {
        if(connection != null) {
            return connection;
        } else {
            DBConectivity.getInstance().connect();
            return connection;
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
            System.out.println("Connection already closed");
        }
    }
}
