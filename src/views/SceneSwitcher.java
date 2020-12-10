package views;

import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SceneSwitcher {
    private HashMap<String, Pane> scenes;
    private String lastScene;

    public SceneSwitcher() {
        scenes = new HashMap<>();
        lastScene = null;
    }

    public void addScene(String name, Pane scene) {
        scenes.put(name, scene);
    }

    public void loadLastScene() {
        if (lastScene != null) {

        }
    }
}
