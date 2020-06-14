package application.viewControllers.Shop;

import application.AOD.Services.*;
import application.DBConnection.DBConectivity;
import application.Alerts.ErrorAlert;
import application.LayoutManager.LayoutManager;
import application.sceneControler.SceneControler;
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

public class ShopC extends AbstractShop{

    @Override
    protected void LandlineNetInfo(LandlineNet rowData) {
        super.LandlineNetInfo(rowData);
        SceneControler.getInstance().setSceneWithoutClear("shopLandlineAb");
    }

    @Override
    protected void MobNetInfo(MobileNet rowData) {
        super.MobNetInfo(rowData);
        SceneControler.getInstance().setSceneWithoutClear("shopMobileNet");
    }

    @Override
    protected void TvInfo(TV rowData) {
        super.TvInfo(rowData);
        SceneControler.getInstance().setSceneWithoutClear("shopTvAb");
    }

    @Override
    protected void DeviceAbInfo(DeviceAb rowData) {
        super.DeviceAbInfo(rowData);
        SceneControler.getInstance().setSceneWithoutClear("ShopDeviceAb");
    }

    @Override
    protected void PhAbInfo(PhoneAb rowData) {
        super.PhAbInfo(rowData);
        SceneControler.getInstance().setSceneWithoutClear("shopPhoneAb");
    }

    @Override
    public void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("clientBasicInfo","shop");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }


}


