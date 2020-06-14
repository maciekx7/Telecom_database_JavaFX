package application.AOD.User;

import application.AOD.Address;
import application.AOD.Operator;
import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.tools.date.DateTool;
import application.viewControllers.Perspective.AbstractEditV;

import java.sql.Connection;
import java.sql.ResultSet;

public class AbstractUser {
    protected int ID;
    protected String FirstName;
    protected String LastName;
    protected int Phone;
    protected String Email;
    protected int Pesel;
    protected String Birth;
    protected String Gender;
    protected int OperatorID;
    protected int AddressID;
    protected Address Address_;
    protected Operator Operator_;


    public AbstractUser(AbstractUser client) {
        ID = client.ID;
        FirstName = client.FirstName;
        LastName = client.LastName;
        Phone = client.Phone;
        Email = client.Email;
        Pesel = client.Pesel;
        Birth = client.Birth;
        Gender = client.Gender;
        OperatorID = client.OperatorID;
        AddressID = client.AddressID;
        Address_ = new Address(client.Address_);
        Operator_ = client.Operator_; //TODO konstruktor kopiujacy
    }

    public AbstractUser() {};


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPesel() {
        return Pesel;
    }

    public void setPesel(int pesel) {
        Pesel = pesel;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(int operatorID) {
        OperatorID = operatorID;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public Address getAddress_() {
        return Address_;
    }

    public void setAddress_(Address address_) {
        Address_ = address_;
    }

    public Operator getOperator_() {
        return Operator_;
    }

    public void setOperator_(Operator operator_) {
        Operator_ = operator_;
    }

    public String getPassword(Connection connection) { return "";}

    protected void oneRecordFromDB(Connection connection,ResultSet result) {
        try {
            FirstName = result.getString(1);
            LastName = result.getString(2);
            Phone = result.getInt(3);
            Email = result.getString(4);
            Pesel = result.getInt(5);
            Birth = DateTool.getStrDateFromOracleDB(result.getString(6));
            Gender = result.getString(7);
            AddressID = result.getInt(8);
            OperatorID = result.getInt(9);
            Address_ = Address.getAddress(connection, String.valueOf(AddressID));
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, Client.class.getName());
        }
    }

    protected void update(AbstractUser abstractUser) {
        Address_.updateAddress(new Address(abstractUser.Address_));
        FirstName = abstractUser.getFirstName();
        LastName = abstractUser.getLastName();
        Gender = abstractUser.getGender();
        Birth = abstractUser.getBirth();
    }


    public boolean updatePassword(Connection connection, String password,String oldPassword) {
        return false;
    }

}
