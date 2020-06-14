package application.AOD.Services;

import application.DBConnection.DBReader;
import application.LayoutManager.LayoutManager;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiceType {

    private int ServicesTypeID;
    private String ServicesTypeName;

    public static String PHONE_AB;
    public static String DEVICE_AB;
    public static String TV_AB;
    public static String INT_ST_AB;
    public static String INT_MOB_ABD;


    public int getServicesTypeID() {
        return ServicesTypeID;
    }

    public void setServicesTypeID(int servicesTypeID) {
        ServicesTypeID = servicesTypeID;
    }

    public String getServicesTypeName() {
        return ServicesTypeName;
    }

    public void setServicesTypeName(String servicesTypeName) {
        ServicesTypeName = servicesTypeName;
    }


    public static void getServicesTypes(Connection connection) {
        ArrayList<String> servicesTypes = new ArrayList<>();

        try {
            String sql = SQLQuery.SERVICES_TYPES_LIST;
            ResultSet result = DBReader.getSQLResult(connection,sql);
            while(result.next()) {
                String x = LayoutManager.serviceType(result.getString(1).replaceAll("\\s",""));
                servicesTypes.add(x);
            }
        } catch(Exception e) {
            ErrorAlert.errorAlert(e, ServiceType.class.getName());
        }

        PHONE_AB = servicesTypes.get(0);
        DEVICE_AB = servicesTypes.get(1);
        TV_AB = servicesTypes.get(2);
        INT_ST_AB = servicesTypes.get(3);
        INT_MOB_ABD = servicesTypes.get(4);
    }

    public static int getServicesTypeCount(Connection connection) {
        int count = 0;
        String sql = SQLQuery.SERVICES_TYPES_COUNT;
        try {
            ResultSet resutl = DBReader.getSQLResult(connection,sql);
            while(resutl.next()) {
                count = resutl.getInt(1);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ServiceType.class.getName());
        }
        return count;
    }
}
