package application.user;

import application.AOD.User.AbstractUser;
import application.AOD.User.Client;
import application.AOD.User.Employee;
import application.DBConnection.DBConectivity;

public class User {

    private String email;
    Client client = null;
    Employee employee = null;
    private int selectedService;
    private String serviceDate;

    private User() {
        if (User.Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    public static User getInstance() {
        return User.Holder.INSTANCE;
    }

    private static class Holder {
        private static final User INSTANCE = new User();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSelectedService() {
        return this.selectedService;
    }

    public void setSelectedService(int serviceID) {
        this.selectedService = serviceID;
    }


    public void setServiceDate(String date) {
        serviceDate = date;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setClient(String email, String password) {
        this.email=email;
        client = Client.getClient(DBConectivity.getConnection(),email,password);
    }

    public Client getClient() {
        if(client!=null) {
            return client;
        } else {
            return null;
        }
    }

    public void setEmployee(String email, String password) {
        this.email=email;
        employee = Employee.getEmployee(email,password);
    }

    public Employee getEmployee() {
        if(employee!=null) {
            return employee;
        } else {
            return null;
        }
    }

    public AbstractUser getAbstractUser() {
        if(employee!= null) {
            return (AbstractUser) employee;
        } else if(client!=null) {
            return (AbstractUser) client;
        }
        return null;

    }
}
