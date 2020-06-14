package application.sceneControler;

import application.Alerts.ErrorAlert;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;

public class SceneControler {
    //Singletone. Może zbędne, poźniej pomyślimy. W konstruktorze wywołuje metodę initialize()
    private SceneControler() {
        if (SceneControler.Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        } else {
            initialize(); //Adding to HashMap parameters about scenes. For now parameters are in class. Later mayby in property file
        }
    }

    public static SceneControler getInstance() {
        return SceneControler.Holder.INSTANCE;
    }

    private static class Holder {
        private static final SceneControler INSTANCE = new SceneControler();
    }


    //HashMapy przechowywujące informacje o istniejących scenach w aplikacji
    private HashMap<String, String> rootPath = new HashMap<>();
    private HashMap<String, Integer> widthMap = new HashMap<>();
    private HashMap<String, Integer> heightMap = new HashMap<>();
    //---------------------------------------------------------------


    //Zapobiega podwujmenu inicjalizowaniu okna aplikacji.
    //Ewentualnie pozniej mozna wprowadzic jakiś moduł czyczczenia tego okna żeby zrboić nowe czy cos
    private boolean isWindowCreated = false;


    //Model Okna przechowywującego stany scen
    private SceneModel sceneModel = SceneModel.getInstance();


    private void initialize() {
        try {
            for(JSONSceneModel.Scene object: JSONSceneModel.getScenes().scenes) {
                //System.out.println(object.name + " " + object.fxmlFile + " " + object.width + " " + object.height);
                rootPath.put(object.name, object.fxmlFile);
                widthMap.put(object.name, object.width);
                heightMap.put(object.name, object.height);
            }
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, SceneControler.class.getName());
        }
    }

    public void createWindow(Stage window,String welcomeScene) {
        if(!isWindowCreated) {
            System.out.println("Creating window with welcomeScene: "+ "'" + welcomeScene + "'");
            sceneModel.addScreen(welcomeScene, rootPath.get(welcomeScene),widthMap.get(welcomeScene),heightMap.get(welcomeScene));
            sceneModel.init(window,welcomeScene);
            isWindowCreated = true;
        } else {
            System.out.println("Window already created!");
        }
    }

    public void setSceneWithClear(String setSceneName, String sceneToClear) {
        if(!Arrays.asList(sceneModel.getScenesNames()).contains(setSceneName)) {
            sceneModel.addScreen(setSceneName,rootPath.get(setSceneName),widthMap.get(setSceneName),heightMap.get(setSceneName));
        }
        System.out.println("clearing " + "'" + sceneToClear +"'");
        sceneModel.removeScreen(sceneToClear);
        sceneModel.activate(setSceneName);
    }

    public void setSceneWithoutClear(String sceneName) {
        if(!Arrays.asList(sceneModel.getScenesNames()).contains(sceneName)) {
            sceneModel.addScreen(sceneName,rootPath.get(sceneName),widthMap.get(sceneName),heightMap.get(sceneName));
        }
        sceneModel.activate(sceneName);
    }


}
