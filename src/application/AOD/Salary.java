package application.AOD;

import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.resources.SQLQuery;
import application.tools.date.DateTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class Salary {
    int SalaryID;
    int SalaryBrutto;
    int SalaryNetto;
    String SalaryDate;
    String ExtraSalary;
    int SalaryEmployeeID;

    public int getSalaryID() {
        return SalaryID;
    }

    public void setSalaryID(int salaryID) {
        SalaryID = salaryID;
    }

    public int getSalaryBrutto() {
        return SalaryBrutto;
    }

    public void setSalaryBrutto(int salaryBrutto) {
        SalaryBrutto = salaryBrutto;
    }

    public int getSalaryNetto() {
        return SalaryNetto;
    }

    public void setSalaryNetto(int salaryNetto) {
        SalaryNetto = salaryNetto;
    }

    public String getSalaryDate() {
        return SalaryDate;
    }

    public void setSalaryDate(String salaryDate) {
        SalaryDate = salaryDate;
    }

    public String getExtraSalary() {
        return ExtraSalary;
    }

    public void setExtraSalary(String extraSalary) {
        ExtraSalary = extraSalary;
    }

    public int getSalaryEmployeeID() {
        return SalaryEmployeeID;
    }

    public void setSalaryEmployeeID(int salaryEmployeeID) {
        SalaryEmployeeID = salaryEmployeeID;
    }

    public static ObservableList<Salary> getEmployeeSalarys(Connection connection,int EmployeeID) {
        ObservableList<Salary> salaryList = FXCollections.observableArrayList();
        try {

            String sql = SQLQuery.GET_EMPLOYEE_SALARY;
            ResultSet result = DBReader.getSQLResult(connection,sql, new String[]{String.valueOf(EmployeeID)});
            while(result.next()) {
                Salary salary = new Salary();
                salary.SalaryID = result.getInt(1);
                salary.SalaryBrutto = result.getInt(2);
                salary.SalaryNetto = result.getInt(3);
                salary.SalaryDate = DateTool.getStrDateFromOracleDB(result.getString(4));
                salary.ExtraSalary = result.getString(5);
                salary.SalaryEmployeeID = result.getInt(6);

                salaryList.add(salary);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Salary.class.getName());
        }
        return salaryList;
    }
}
