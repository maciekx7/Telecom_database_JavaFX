package application.AOD.User;

import application.AOD.Address;
import application.AOD.Buildings.Office;
import application.AOD.Buildings.ShopOffice;
import application.AOD.Position;
import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.resources.SQLQuery;
import application.tools.date.DateTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.awt.image.RescaleOp;
import java.sql.Connection;
import java.sql.DataTruncation;
import java.sql.ResultSet;

public class Employee extends AbstractUser{
    String EmploymentDate;
    long BankAccountNumber;
    int OfficeID;
    int ShopOfficeID;
    int PositionID;
    Office office;
    ShopOffice shopOffice;
    Position position;


    public Employee() {};

    public Employee(Employee employee) {
        ID = employee.ID;
        FirstName = employee.FirstName;
        LastName = employee.LastName;
        Phone = employee.Phone;
        Email = employee.Email;
        Pesel = employee.Pesel;
        Birth = employee.Birth;
        Gender = employee.Gender;
        OperatorID = employee.OperatorID;
        AddressID = employee.AddressID;
        Address_ = new Address(employee.Address_);
        Operator_ = employee.Operator_; //TODO konstruktor kopiujacy
        EmploymentDate = employee.EmploymentDate;
        BankAccountNumber = employee.BankAccountNumber;
        OfficeID = employee.OfficeID;
        ShopOfficeID = employee.ShopOfficeID;
        PositionID = employee.PositionID;
        office = employee.office;
        shopOffice = employee.shopOffice;
        position = employee.position;
    }

    public String getEmploymentDate() {
        return EmploymentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        EmploymentDate = employmentDate;
    }

    public long getBankAccountNumber() {
        return BankAccountNumber;
    }

    public void setBankAccountNumber(long bankAccountNumber) {
        BankAccountNumber = bankAccountNumber;
    }

    public int getOfficeID() {
        return OfficeID;
    }

    public void setOfficeID(int officeID) {
        OfficeID = officeID;
    }

    public int getShopOfficeID() {
        return ShopOfficeID;
    }

    public void setShopOfficeID(int shopOfficeID) {
        ShopOfficeID = shopOfficeID;
    }

    public int getPositionID() {
        return PositionID;
    }

    public void setPositionID(int positionID) {
        PositionID = positionID;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public ShopOffice getShopOffice() {
        return shopOffice;
    }

    public void setShopOffice(ShopOffice shopOffice) {
        this.shopOffice = shopOffice;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public static Employee getEmployee(String email, String password) {
        Employee employee = null;
        try {
            Connection connection = DBConectivity.getConnection();
            String sql = SQLQuery.GET_EMPLOYEE_ON_EMAIL;
            ResultSet result = DBReader.getSQLResult(DBConectivity.getConnection(),sql, new String[]{email,password});
            while(result.next()) {
                employee = new Employee();
                employee.oneRecordFromDB(connection,result);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Employee.class.getName());
        }
        return employee;
    }

    @Override
    public String getPassword(Connection connection) {
        String sql = "";
        //return DBReader.getSQLResult(connection,sql,new String[]{});
        return "";
    }


    public static ObservableList<Employee> getEmployees(Connection connection, int OfficeID) {
        ObservableList<Employee> employeeLiist = FXCollections.observableArrayList();
        try {
            String sql = SQLQuery.GET_OFFICE_EMPLOYEES;
            ResultSet resultSet = DBReader.getSQLResult(connection,sql, new String[]{String.valueOf(OfficeID)});
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.oneRecordFromDB(connection,resultSet);
                employeeLiist.add(employee);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Employee.class.getName());
        }
        return employeeLiist;
    }


    public void updateEmployee(Employee employee) {
        try {
            String sql = SQLQuery.UPDATE_EMPLOYEE;
            String[] params = new String[]{employee.getFirstName(),employee.getLastName(), employee.getBirth(),employee.getGender(),String.valueOf(employee.getID()),String.valueOf(employee.getBankAccountNumber())};
           DBReader.sendSQLQuery(DBConectivity.getConnection(),sql,params);

            //TODO -> Idź do metody update i dokonaj update'u parametrow pracownika, ktorych nie ma AbstractUser
            update(employee);

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
    }


    @Override
    protected void oneRecordFromDB(Connection connection,ResultSet result) {
        super.oneRecordFromDB(connection,result);
        try {
            //SELECT IMIE, NAZWISKO, NR_TELEFONU, EMAIL, PESEL, DATA_URODZENIA, PLEC, ID_ADRESU, ID_OPERATORA, ID_PRACOWNIKA, DATA_ZATRUDNIENIA, NR_KONTA_BANKOWEGO,ID_BIURA,ID_PUNKTU_SPRZEDAZY, ID_STANOWISKA FROM PRACOWNICY
            ID = result.getInt(10);
            EmploymentDate = DateTool.getStrDateFromOracleDB(result.getString(11));
            BankAccountNumber = result.getInt(12);
            OfficeID = result.getInt(13);
            office = Office.getOffice(OfficeID);
            ShopOfficeID = result.getInt(14);
            //TODO ShopOffice object
            PositionID = result.getInt(15);
            position = Position.getPosition(PositionID);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Employee.class.getName());
        }
    }

    protected void update(Employee employee) {
        try {
            update((AbstractUser) employee);
            BankAccountNumber = employee.getBankAccountNumber();

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Employee.class.getName());
        }
    }

    @Override
    public boolean updatePassword(Connection connection,String password, String oldPassword) {
        try {

            ResultSet result = DBReader.getSQLResult(connection,SQLQuery.GET_EMPLOYEE_PASSWORD, new String[]{String.valueOf(ID), oldPassword});
            if(result.next()) {
                String sql = SQLQuery.UPDATE_EMPLOYEE_PASSWORD;
                DBReader.sendSQLQuery(connection, sql, new String[]{password, String.valueOf(ID), oldPassword});
                return true;
            }
            return false;
        } catch (Exception e) {//SQLException e//) {
            return false;
        }
    }
}
