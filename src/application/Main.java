package application;

import application.sceneControler.SceneControler;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

        
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UTILITY);
        SceneControler.getInstance().createWindow(primaryStage,"login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
