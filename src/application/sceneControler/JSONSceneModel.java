package application.sceneControler;

import application.Alerts.ErrorAlert;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.util.ArrayList;

class JSONSceneModel {
    protected ArrayList<Scene> scenes = new ArrayList<>();

    protected class Scene {
        public String name;
        public String fxmlFile;
        public int width;
        public int height;
    }

    public static JSONSceneModel getScenes() {
        JSONSceneModel jsonModel = new JSONSceneModel();
        try {
            String jsonFile = "src/application/sceneControler/scenes.json";
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new FileReader(jsonFile));
            jsonModel = gson.fromJson(jsonReader, JSONSceneModel.class);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, JSONSceneModel.class.getName());
        }
        return jsonModel;
    }
}

