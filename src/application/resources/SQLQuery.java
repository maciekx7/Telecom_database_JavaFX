package application.resources;
import application.configReader.*;



public class SQLQuery {
    private static String filename = "SQLQuery";

    public static String SELECT_OPERATORZY = ConfigReader.getValue(filename,"selectOperatorzy");
    public static String SELECT_USLUGI_KLIENTA = ConfigReader.getValue(filename, "selectUslugiKlienta");
    public static String SELECT_all_FROM_PhoneAb = ConfigReader.getValue(filename, "SELECTallFROMPhoneAb");
    public static String CLIENT_SERVICES_COUNT = ConfigReader.getValue(filename,"ClientServicesCount");
    public static String SERVICES_TYPES_COUNT = ConfigReader.getValue(filename,"ServicesTypeCount");
    public static String SERVICES_TYPES_LIST = ConfigReader.getValue(filename,"ServicesTypeList");
    public static String GET_ALL_TV = ConfigReader.getValue(filename, "GETAllTV");
    public static String GET_ALL_MOBILENET = ConfigReader.getValue(filename, "GETAllMobileNet");
    public static String GET_ADDRESS = ConfigReader.getValue(filename,"GETAddress");
    public static String GET_CLIENT = ConfigReader.getValue(filename,"GETClient");
    public static String GET_LANDLINE = ConfigReader.getValue(filename, "GETLandlineNet");
    public static String GET_ALL_LANDLINE = ConfigReader.getValue(filename, "GETAllLandlineNet");
    public static String GET_MOBILENET = ConfigReader.getValue(filename, "GETMobileNet");
    public static String GET_PHONEAB= ConfigReader.getValue(filename, "GETPhoneAb");
    public static String GET_ALL_PHONEAB = ConfigReader.getValue(filename,"GETAllPhoneAb");
    public static String GET_DEVICEAB = ConfigReader.getValue(filename,"GETDeviceAb");
    public static String GET_DEVICEAB_MODEL = ConfigReader.getValue(filename, "GETDeviceAbModel");
    public static String GET_DEVICEAB_MARK = ConfigReader.getValue(filename,"GETDeviceAbMark");
    public static String GET_TV = ConfigReader.getValue(filename, "GETTv");
    public static String GET_SERVICESINFO = ConfigReader.getValue(filename, "GETServices");
    public static String GET_ALL_DEVICEAB = ConfigReader.getValue(filename,"GETAllDeviceAb");
    public static String BUY_NEW_SERVICE = ConfigReader.getValue(filename,"BUYService");
    public static String GET_EMPLOYEE_ON_EMAIL = ConfigReader.getValue(filename, "GETEmployeeONEmail");
    public static String GET_EMPLOYEE_ON_ID = ConfigReader.getValue(filename, "GETEmployeeONId");
    public static String GET_EMPLOYEE_SALARY = ConfigReader.getValue(filename,"GETEmployeeSalary");
    public static String GET_POSITION_ON_ID = ConfigReader.getValue(filename, "GETPositionONId");
    public static String GET_OFFICE_EMPLOYEES = ConfigReader.getValue(filename, "GETOfficeEmployees");
    public static String GET_OFFICE_ON_ID = ConfigReader.getValue(filename, "GETOfficeONId");
    public static String GET_COMPANY = ConfigReader.getValue(filename,"GETCompany");
    public static String GET_PHONE_MARKS = ConfigReader.getValue(filename,"GETPhoneMarks");

    public static String UPDATE_EMPLOYEE = ConfigReader.getValue(filename,"UPDATEEmployee");
    public static String UPDATE_ADDRESS = ConfigReader.getValue(filename,"UPDATEAddress");
    public static String UPDATE_CLIENT = ConfigReader.getValue(filename, "UPDATEClient");
    public static String UPDATE_SERVICE = ConfigReader.getValue(filename,"UPDATEService");
    public static String UPDATE_PHONEAB = ConfigReader.getValue(filename,"UPDATEPhoneAb");
    public static String UPDATE_LANDLINENET = ConfigReader.getValue(filename,"UPDATELandlineNet");
    public static String UPDATE_MOBILENET = ConfigReader.getValue(filename,"UPDATEMobileNet");
    public static String UPDATE_TV = ConfigReader.getValue(filename,"UPDATETv");
    public static String UPDATE_DEVICEAB = ConfigReader.getValue(filename,"UPDATEDeviceAb");
    public static String UPDATE_PHONE_MODEL = ConfigReader.getValue(filename,"UPDATEPhoneModel");

    public static String NEW_PHONEAB = ConfigReader.getValue(filename,"NEWPhoneAb");
    public static String NEW_MOBNET = ConfigReader.getValue(filename,"NEWMobNetAb");
    public static String NEW_LANDNET = ConfigReader.getValue(filename,"NEWLandNetAb");
    public static String NEW_TV = ConfigReader.getValue(filename,"NEWTvAb");
    public static String NEW_DEVICEAB = ConfigReader.getValue(filename,"NEWDeviceAb");

    public static String UPDATE_EMPLOYEE_PASSWORD = ConfigReader.getValue(filename, "UPDATEEmployeePassword");
    public static String UPDATE_CLIENT_PASSWORD = ConfigReader.getValue(filename,"UPDATEClientPassword");

    public static String GET_EMPLOYEE_PASSWORD = ConfigReader.getValue(filename,"GETEmployeePassword");
    public static String GET_CLIENT_PASSWORD = ConfigReader.getValue(filename,"GETClientPassword");

    public static String COUNT_SERVICES = ConfigReader.getValue(filename, "CountServices");

    public static String DELETE_DEVICEAB = ConfigReader.getValue(filename,"DELETEDeviceAb");
    public static String DELETE_TVAB = ConfigReader.getValue(filename,"DELETETVAB");
    public static String DELETE_LANDLINENET = ConfigReader.getValue(filename,"DELETELandlineNet");
    public static String DELETE_MOBILENET = ConfigReader.getValue(filename,"DELETEMobileNet");
    public static String DELETE_PHONEAB = ConfigReader.getValue(filename,"DELETEPhoneAb");
}
