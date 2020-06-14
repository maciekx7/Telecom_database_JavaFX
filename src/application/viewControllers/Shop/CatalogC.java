package application.viewControllers.Shop;

import application.AOD.Services.*;
import application.sceneControler.SceneControler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CatalogC extends AbstractShop {

    @FXML
    private Button AddLandlineNet;
    @FXML
    private Button addDevice;
    @FXML
    private Button addTv;
    @FXML
    private Button addMobileNet;
    @FXML
    private Button addPhone;


    @Override
    protected void LandlineNetInfo(LandlineNet rowData) {
        super.LandlineNetInfo(rowData);
        SceneControler.getInstance().setSceneWithClear("EditLandlineAb","Catalog");
    }

    @Override
    protected void MobNetInfo(MobileNet rowData) {
        super.MobNetInfo(rowData);
        SceneControler.getInstance().setSceneWithClear("EditMobileNetAb","Catalog");
    }

    @Override
    protected void TvInfo(TV rowData) {
        super.TvInfo(rowData);
        SceneControler.getInstance().setSceneWithClear("EditTvAb","Catalog");
    }

    @Override
    protected void DeviceAbInfo(DeviceAb rowData) {
        super.DeviceAbInfo(rowData);
        SceneControler.getInstance().setSceneWithClear("EditDeviceAb","Catalog");
    }

    @Override
    protected void PhAbInfo(PhoneAb rowData) {
        super.PhAbInfo(rowData);
        SceneControler.getInstance().setSceneWithClear("EditPhoneAb","Catalog");
    }

    protected void addLandlineNet_(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("AddLandlineAb","Catalog");
    }

    protected void addMobileNet_(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("AddMobileNetAb","Catalog");
    }

    protected void addDevive_(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("AddDeviceAb","Catalog");
    }

    protected void addPhoneAb_(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("AddPhoneAb","Catalog");
    }

    protected void addTvAb_(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("AddTvAb","Catalog");
    }


    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("employeeBasicInfo","Catalog");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url,resourceBundle);
        AddLandlineNet.setOnAction(this::addLandlineNet_);
        addMobileNet.setOnAction(this::addMobileNet_);
        addDevice.setOnAction(this::addDevive_);
        addPhone.setOnAction(this::addPhoneAb_);
        addTv.setOnAction(this::addTvAb_);
    }
}
