package application.sceneControler;

import application.Alerts.ErrorAlert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SceneModel {
    private HashMap<String, Parent> screenMap = new HashMap<>();
    private HashMap<String, Integer> widthMap = new HashMap<>();
    private HashMap<String, Integer> heightMap = new HashMap<>();
    private Scene scene;
    private Stage stage;


    private SceneModel() {
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    protected static SceneModel getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SceneModel INSTANCE = new SceneModel();
    }

    public void init(Stage stage, String primaryView) {
        if(Arrays.asList(this.getScenesNames()).contains(primaryView)) {
            this.stage = stage;
            scene = new Scene(screenMap.get(primaryView),widthMap.get(primaryView),heightMap.get(primaryView));
            this.stage.setWidth(widthMap.get(primaryView));
            this.stage.setHeight(heightMap.get(primaryView) + 17);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            System.out.println("----------WARNING!----------");
            System.out.println("NO SCREEN AT THAT NAME -> " + primaryView);
        }
    }


    public void addScreen(String name, String root, Integer width, Integer height) {
        try {
            Parent Root = FXMLLoader.load(getClass().getResource("../FXMLViews/" + root));
            screenMap.put(name, Root);
            widthMap.put(name, width);
            heightMap.put(name, height);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, SceneModel.class.getName());
        }
    }

    public void removeScreen(String name) {
        if(Arrays.asList(this.getScenesNames()).contains(name)) {
            screenMap.remove(name);
            widthMap.remove(name);
            heightMap.remove(name);
        } else {
            System.out.println("NO SCENE NAMED -> " + name);
        }
    }

    public void activate(String name) {
        if(Arrays.asList(this.getScenesNames()).contains(name)) {
            scene.setRoot(screenMap.get(name));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setWidth(widthMap.get(name));
            stage.setHeight(heightMap.get(name) + 17);
        } else {
            System.out.println("----------WARNING!----------");
            System.out.println("NO SCREEN AT THAT NAME -> " + name );
        }
    }


    public String[] getScenesNames() {
        ArrayList<String> names = new ArrayList<>();
        screenMap.keySet().forEach((key) -> {
            names.add(key);
        });
        return names.toArray(new String[0]);
    }
}
