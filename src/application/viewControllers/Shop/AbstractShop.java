package application.viewControllers.Shop;

import application.AOD.Services.*;
import application.Alerts.ErrorAlert;
import application.DBConnection.DBConectivity;
import application.LayoutManager.LayoutManager;
import application.user.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AbstractShop implements Initializable {


    protected ObservableList<PhoneAb> phoneAbList = FXCollections.observableArrayList();
    protected ObservableList<DeviceAb> deviceList = FXCollections.observableArrayList();
    protected ObservableList<TV> tvList = FXCollections.observableArrayList();
    protected ObservableList<MobileNet> mobNetList = FXCollections.observableArrayList();
    protected ObservableList<LandlineNet> landlineNetList = FXCollections.observableArrayList();

    protected Connection connection = DBConectivity.getConnection();

    protected User user = User.getInstance();

    @FXML
    protected TableView<DeviceAb> DevTab;
    @FXML
    protected TableColumn<DeviceAb,String> DevMark;
    @FXML
    protected TableColumn<DeviceAb,String> DevModel;
    @FXML
    protected TableColumn<DeviceAb,String> DevScreen;
    @FXML
    protected TableColumn<DeviceAb,String> DevStartPrice;
    @FXML
    protected TableColumn<DeviceAb,String> DevPrice;
    @FXML
    protected TableColumn<DeviceAb,String> DevDuration;
    @FXML
    protected TextField DevSearch;


    @FXML
    protected Button back;
    @FXML
    protected TableView<PhoneAb> PhAbTab;
    @FXML
    protected TableColumn<PhoneAb, String>  PhAbName;
    @FXML
    protected TableColumn<PhoneAb, String>  PhAbGb;
    @FXML
    protected TableColumn<PhoneAb, String>  PhAbTalk;
    @FXML
    protected TableColumn<PhoneAb, String>  PhAhDuration;
    @FXML
    protected TableColumn<PhoneAb, String> PhAbPrice;
    @FXML
    protected  TextField PhoneAbSearch;

    @FXML
    protected TableView<TV> TvTab;
    @FXML
    protected TableColumn<TV, String> TvName;
    @FXML
    protected TableColumn<TV, String>  TvCanals;
    @FXML
    protected TableColumn<TV, String>  TvMultiroom;
    @FXML
    protected TableColumn<TV, String>  TvPrice;
    @FXML
    protected TableColumn<TV, String>  TvDuration;
    @FXML
    protected TextField TvSearch;

    @FXML
    protected TableView<MobileNet> MobNetTab;
    @FXML
    protected TableColumn<MobileNet, String> MobNetName;
    @FXML
    protected TableColumn<MobileNet, String>  MobNetGB;
    @FXML
    protected TableColumn<MobileNet, String>  MobNetDuration;
    @FXML
    protected TableColumn<MobileNet, String>  MobNetPrice;
    @FXML
    protected TableColumn<MobileNet, String>  MobNetHappy;
    @FXML
    protected TextField MobNetSearch;

    @FXML
    protected TableView<LandlineNet> LandlineNetTab;
    @FXML
    protected TableColumn<LandlineNet, String> LandlineNetName;
    @FXML
    protected TableColumn<LandlineNet, String>  LandlineNetSpped;
    @FXML
    protected TableColumn<LandlineNet, String>  LandlineNetCabel;
    @FXML
    protected TableColumn<LandlineNet, String>  LandlineNetPrice;
    @FXML
    protected TableColumn<LandlineNet, String>  LandlineNetDuration;
    @FXML
    protected TextField LandlineNetSearch;

    public void onBackButton(ActionEvent event) {
        //SceneControler.getInstance().setSceneWithClear("clientBasicInfo","shop");
    }


    protected void PhAbTable() {
        try {
            phoneAbList = PhoneAb.getAll(connection);

            PhAbName.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getPhoneAbName())
            );
            PhAbGb.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getPhoneAbGigabs())
            );
            PhAbTalk.setCellValueFactory(c->
                    new SimpleStringProperty(LayoutManager.minutesToScreen(c.getValue().getPhoneAbMinutes()))
            );
            PhAbPrice.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesMonthPrice())));
            PhAhDuration.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesDuration())));

            //SEARCH ENGINE------
            FilteredList<PhoneAb> filteredList = new FilteredList<>(phoneAbList, b-> true);

            PhoneAbSearch.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(phoneAb -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCastFilter = newValue.toLowerCase();

                    if(phoneAb.getPhoneAbName().toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(phoneAb.getServicesMonthPrice()).toLowerCase().indexOf(lowerCastFilter) != -1 ) {
                        return true;
                    } else if(String.valueOf(phoneAb.getServicesDuration()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<PhoneAb> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(PhAbTab.comparatorProperty());

            //ADD SEARCHED (or All) values
            PhAbTab.setItems(sortedList);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ShopC.class.getName());
        }

    }

    protected void PhAbInfo(PhoneAb rowData) {
        user.setSelectedService(rowData.getServicesID());
    }

    protected void DeviceTable() {
        try {
            deviceList = DeviceAb.getAll(connection);

            DevMark.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getPhoneModel().getPhoneMark())
            );
            DevModel.setCellValueFactory(c ->
                    new SimpleStringProperty(c.getValue().getPhoneModel().getPhoneModelName())
            );
            DevScreen.setCellValueFactory(c ->
                    new SimpleStringProperty(c.getValue().getPhoneModel().getPhoneModelSize())
            );
            DevStartPrice.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getDeviceAbCost())));
            DevPrice.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesMonthPrice()))
            );
            DevDuration.setCellValueFactory(c ->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesDuration()))
            );


            //SEARCH ENGINE------
            FilteredList<DeviceAb> filteredList = new FilteredList<>(deviceList, b-> true);

            DevSearch.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(dev -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCastFilter = newValue.toLowerCase();

                    if(dev.getPhoneModel().getPhoneModelName().toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(dev.getPhoneModel().getPhoneMark()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(dev.getServicesMonthPrice()).toLowerCase().indexOf(lowerCastFilter) != -1 ){
                        return true;
                    } else if(String.valueOf(dev.getServicesDuration()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<DeviceAb> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(DevTab.comparatorProperty());

            //ADD SEARCHED (or All) values
            DevTab.setItems(sortedList);

        } catch (Exception e) {
            ErrorAlert.errorAlert(e,ShopC.class.getName());
        }


    }

    protected void DeviceAbInfo(DeviceAb rowData) {
        user.setSelectedService(rowData.getServicesID());
    }

    protected void TvTable() {
        try {
            tvList = TV.getAll(connection);

            TvName.setCellValueFactory(c ->
                    new SimpleStringProperty(c.getValue().getTVName())
            );
            TvCanals.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getTVCanals()))
            );
            TvMultiroom.setCellValueFactory(c->
                    new SimpleStringProperty(
                            LayoutManager.TF(
                                    c.getValue().getTVMultiroom(),"y"
                            )
                    )
            );
            TvDuration.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesDuration()))
            );
            TvPrice.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesMonthPrice())));

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ShopC.class.getName());
        }


        //SEARCH ENGINE------
        FilteredList<TV> filteredList = new FilteredList<>(tvList, b-> true);

        TvSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(tv -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCastFilter = newValue.toLowerCase();

                if(tv.getTVName().toLowerCase().indexOf(lowerCastFilter) != -1) {
                    return true;
                } else if(String.valueOf(tv.getServicesMonthPrice()).toLowerCase().indexOf(lowerCastFilter) != -1 ){
                    return true;
                } else if(String.valueOf(tv.getServicesDuration()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<TV> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(TvTab.comparatorProperty());

        //ADD SEARCHED (or All) values
        TvTab.setItems(sortedList);
    }

    protected void TvInfo(TV rowData) {
        user.setSelectedService(rowData.getServicesID());
    }

    private void MobNetTable() {
        try {
            mobNetList = MobileNet.getAll(connection);

            MobNetName.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getMobileNetName()
                    )
            );
            MobNetGB.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getMobileNetGb()
                    )
            );
            MobNetHappy.setCellValueFactory(c->
                    new SimpleStringProperty(
                            LayoutManager.TF(
                                    c.getValue().getMobileNetHappyH(),"e"
                            )
                    )
            );
            MobNetPrice.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesMonthPrice()))
            );
            MobNetDuration.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesDuration()))
            );


            //SEARCH ENGINE------
            FilteredList<MobileNet> filteredList = new FilteredList<>(mobNetList, b-> true);

            MobNetSearch.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(mobNet -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCastFilter = newValue.toLowerCase();

                    if(mobNet.getMobileNetName().toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(mobNet.getServicesMonthPrice()).toLowerCase().indexOf(lowerCastFilter) != -1 ) {
                        return true;
                    } else if(String.valueOf(mobNet.getServicesDuration()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<MobileNet> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(MobNetTab.comparatorProperty());

            //ADD SEARCHED (or All) values
            MobNetTab.setItems(sortedList);

        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ShopC.class.getName());
        }
    }

    protected void MobNetInfo(MobileNet rowData) {
        user.setSelectedService(rowData.getServicesID());
    }




    protected void LandlineNetTable() {
        try {
            landlineNetList = LandlineNet.getAll(connection);

            LandlineNetName.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getLandlineName())
            );
            LandlineNetSpped.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getLandlineSpeed())
            );
            LandlineNetCabel.setCellValueFactory(c->
                    new SimpleStringProperty(c.getValue().getLandlineCabel())
            );
            LandlineNetDuration.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesDuration()))
            );
            LandlineNetPrice.setCellValueFactory(c->
                    new SimpleStringProperty(String.valueOf(c.getValue().getServicesMonthPrice()))
            );





            //SEARCH ENGINE------
            FilteredList<LandlineNet> filteredList = new FilteredList<>(landlineNetList, b-> true);

            LandlineNetSearch.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(landlineNet -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCastFilter = newValue.toLowerCase();

                    if(landlineNet.getLandlineName().toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    } else if(String.valueOf(landlineNet.getServicesMonthPrice()).toLowerCase().indexOf(lowerCastFilter) != -1 ){
                        return true;
                    } else if(String.valueOf(landlineNet.getServicesDuration()).toLowerCase().indexOf(lowerCastFilter) != -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<LandlineNet> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(LandlineNetTab.comparatorProperty());

            //ADD SEARCHED (or All) values
            LandlineNetTab.setItems(sortedList);


        } catch (Exception e) {
            ErrorAlert.errorAlert(e, ShopC.class.getName());
        }
    }

    protected void LandlineNetInfo(LandlineNet rowData) {
        user.setSelectedService(rowData.getServicesID());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(this::onBackButton);

        PhAbTable();
        DeviceTable();
        TvTable();
        MobNetTable();
        LandlineNetTable();

        PhAbTab.setRowFactory(tv -> {
            TableRow<PhoneAb> row = new TableRow<>();
            row.setOnMouseClicked(event_ -> {
                if(event_.getClickCount() == 2 && (!row.isEmpty()))  {
                    PhoneAb rowData = row.getItem();
                    PhAbInfo(rowData);
                }
            });
            return row;
        });

        DevTab.setRowFactory(tv -> {
            TableRow<DeviceAb> row = new TableRow<>();
            row.setOnMouseClicked(event_ -> {
                if(event_.getClickCount() == 2 && (!row.isEmpty()))  {
                    DeviceAb rowData = row.getItem();
                    DeviceAbInfo(rowData);
                }
            });
            return row;
        });

        TvTab.setRowFactory(tv -> {
            TableRow<TV> row = new TableRow<>();
            row.setOnMouseClicked(event_ -> {
                if(event_.getClickCount() == 2 && (!row.isEmpty()))  {
                    TV rowData = row.getItem();
                    TvInfo(rowData);
                }
            });
            return row;
        });

        MobNetTab.setRowFactory(tv -> {
            TableRow<MobileNet> row = new TableRow<>();
            row.setOnMouseClicked(event_ -> {
                if(event_.getClickCount() == 2 && (!row.isEmpty()))  {
                    MobileNet rowData = row.getItem();
                    MobNetInfo(rowData);
                }
            });
            return row;
        });

        LandlineNetTab.setRowFactory(tv -> {
            TableRow<LandlineNet> row = new TableRow<>();
            row.setOnMouseClicked(event_ -> {
                if(event_.getClickCount() == 2 && (!row.isEmpty()))  {
                    LandlineNet rowData = row.getItem();
                    LandlineNetInfo(rowData);
                }
            });
            return row;
        });
    }
}



