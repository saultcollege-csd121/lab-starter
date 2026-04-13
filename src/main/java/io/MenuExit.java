package io;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.*;

// this takes you out of the menu scene and brings you back to the game!
public class MenuExit implements EventHandler<MouseEvent> {
    private final Stage primaryStage;
    private final Scene mainScene;

    public MenuExit(Stage primaryStage, Scene mainScene) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
    }

    @Override
    public void handle(MouseEvent event) {
        primaryStage.setScene(mainScene);
    }
}
