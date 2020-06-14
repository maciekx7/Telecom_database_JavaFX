package application.viewControllers.Services;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public interface IEditInterface extends Initializable {


    void onBackButton(ActionEvent event);


    void onApplyButton(ActionEvent event);

    @Override
    void initialize(URL url, ResourceBundle resourceBundle);
}
