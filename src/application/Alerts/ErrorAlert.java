package application.Alerts;

import javafx.scene.control.Alert;

public class ErrorAlert extends AbAlert{

    public static void errorAlert(Exception e, String className) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error in class '" + className + "/" + getMethodName() + "'\n\n" + e);
        alert.show();
        System.out.println("!!!!---!!!!!---!!!!!----!!!!----!!!!");
        System.out.println("Error in  '" + className + "/" + getMethodName() + "'\n" + e);
        System.out.println("!!!!---!!!!!---!!!!!----!!!!----!!!!");
        e.printStackTrace();
    }
}
