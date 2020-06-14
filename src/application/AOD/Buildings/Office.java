package application.AOD.Buildings;

import application.AOD.Address;
import application.AOD.Operator;
import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.DBConnection.DBReader;
import application.resources.SQLQuery;

import java.awt.image.RescaleOp;
import java.sql.ResultSet;

public class Office extends AbstractBuilding {
    private String specialization;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public static Office getOffice(int OfficeID) {
        try {
            Office office = new Office();
            String sql = SQLQuery.GET_OFFICE_ON_ID;

            ResultSet result = DBReader.getSQLResult(DBConectivity.getConnection(), sql, new String[]{String.valueOf(OfficeID)});
            while(result.next()) {
                office.ID = result.getInt(1);
                office.name = result.getString(2);
                office.phone = result.getInt(3);
                office.email = result.getString(4);
                office.specialization = result.getString(5);
                office.OperatorID = result.getInt(6);
                //TODO operator
                //office.operator = Operator.getOperator(String.valueOf(office.OperatorID));
                office.AddressID = result.getInt(7);
                office.address = Address.getAddress(DBConectivity.getConnection(),String.valueOf(office.AddressID));


            }
            return office;
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Office.class.getName());
        }
        return null;
    }
}
