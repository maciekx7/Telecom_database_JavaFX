package application.AOD.Services;

import application.AOD.Address;
import application.AOD.Operator;
import application.DBConnection.DBReader;
import application.Alerts.ErrorAlert;
import application.resources.SQLQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Service {
    protected int ServicesID;
    protected int ServicesOperatorID;
    protected int ServicesTypeID;
    protected int ServicesMonthPrice;
    protected int ServicesDuration;
    protected Operator operator;
    protected ServiceType serviceType;
    protected List<String> params;

    public int getServicesID() {
        return ServicesID;
    }

    public void setServicesID(int servicesID) {
        ServicesID = servicesID;
    }

    public int getServicesOperatorID() {
        return ServicesOperatorID;
    }

    public void setServicesOperatorID(int servicesOperatorID) {
        ServicesOperatorID = servicesOperatorID;
    }

    public int getServicesTypeID() {
        return ServicesTypeID;
    }

    public void setServicesTypeID(int servicesTypeID) {
        ServicesTypeID = servicesTypeID;
    }

    public int getServicesMonthPrice() {
        return ServicesMonthPrice;
    }

    public void setServicesMonthPrice(int servicesMonthPrice) {
        ServicesMonthPrice = servicesMonthPrice;
    }

    public int getServicesDuration() {
        return ServicesDuration;
    }

    public void setServicesDuration(int servicesDuration) {
        ServicesDuration = servicesDuration;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public static Service getService(Connection connection, String serviceID)  {
        Service service = new Service();

        try {
            String sql = SQLQuery.GET_SERVICESINFO;
            ResultSet result = DBReader.getSQLResult(connection, sql, new String[]{serviceID});
            while(result.next()) {
                service.ServicesID = result.getInt(1);
                service.ServicesOperatorID = result.getInt(2);
                service.ServicesTypeID = result.getInt(3);
                service.ServicesMonthPrice = result.getInt(4);
                service.ServicesDuration = result.getInt(5);
            }

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Address.class.getName());//ghg
        }
        return service;
    }


    /** Method DO NOT send info to DB, only add params to params array */
    public void updateRecord(Connection connection) {
        params = null;
        params = new ArrayList<>();
        //try {
            params.add(String.valueOf(ServicesMonthPrice));
            params.add(String.valueOf(ServicesDuration));
            params.add(String.valueOf(ServicesID));
            //String sql = SQLQuery.UPDATE_SERVICE;
            //DBReader.sendSQLQuery(connection,sql, new String[]{String.valueOf(ServicesMonthPrice),String.valueOf(ServicesDuration),String.valueOf(ServicesID)});

        /*} catch (Exception e) {
            ErrorAlert.errorAlert(e, Service.class.getName());
        }
        */
    }


    public void newRecord(Connection connection) {

    }

    public void deleteRecord(Connection connection) {

    }

}
