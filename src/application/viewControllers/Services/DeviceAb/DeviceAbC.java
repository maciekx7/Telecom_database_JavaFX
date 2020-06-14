package application.viewControllers.Services.DeviceAb;


import application.sceneControler.SceneControler;
import application.tools.date.DateTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeviceAbC extends AbstractDeviceAbC {

    @FXML
    protected Label endOfPeriod;

    @Override
    protected void onBackButtonClick(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("services","DeviceAb");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        super.initialize(url,resourceBundle);
        endOfPeriod.setText(DateTool.getDateWithMonthNames.YearFirst(DateTool.addMonths_(user.getServiceDate(),deviceAb.getServicesDuration())));


    }



}

