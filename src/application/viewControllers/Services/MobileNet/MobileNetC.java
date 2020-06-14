package application.viewControllers.Services.MobileNet;

import application.sceneControler.SceneControler;
import application.tools.date.DateTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MobileNetC extends AbstractMobileNetC {

    @FXML
    protected Label endOfPeriod;

    @Override
    protected void onBackButton(ActionEvent event) {
        SceneControler.getInstance().setSceneWithClear("services","MobileNetAb");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        //endOfPeriod.setText(user.getServiceDate().getDateAfter(mobileNet.getService().getServicesDuration()).dateWithMonthName());
        endOfPeriod.setText(DateTool.getDateWithMonthNames.YearFirst(DateTool.addMonths_(user.getServiceDate(),mobileNet.getServicesDuration())));

    }

}
