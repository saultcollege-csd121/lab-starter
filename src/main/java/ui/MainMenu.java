package ui;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu implements EventHandler<MouseEvent> {
//    variables to hold all the data!
    private final Stage primaryStage;
    private final Scene menuScene;
    private final Pane menuRoot;
    private final Pane menuTextPane;
    private final Pane inputPane;
    private final Pane submitPane;
    private final Pane exitButtonPane;


//    constructor to GET all the data
    public MainMenu(Stage primaryStage, Scene menuScene, Pane menuRoot, Pane menuTextPane, Pane inputPane, Pane submitPane, Pane exitButtonPane) {
        this.primaryStage = primaryStage;
        this.menuScene = menuScene;
        this.menuRoot = menuRoot;
        this.menuTextPane = menuTextPane;
        this.inputPane = inputPane;
        this.submitPane = submitPane;
        this.exitButtonPane = exitButtonPane;

    }


//    AND THE DRAMA!
    @Override
    public void handle(MouseEvent event) {
//        setting the scene...literally
        primaryStage.setScene(menuScene);
        primaryStage.show();

//        omg is that...a lambda?!? oooh ahhhh
        Platform.runLater(() -> {
            // relocating the items
            menuRoot.applyCss();
            menuRoot.layout();

            // the exit button, ez
            exitButtonPane.relocate(20, 20); // i just want it in the corner!

            // the text
            var menuWidth = menuRoot.getWidth();
            var menuHeight = menuRoot.getHeight();
            var menuTextWidth = menuTextPane.getWidth();
            var menuTextHeight = menuTextPane.getHeight();
            menuTextPane.relocate(((menuWidth - menuTextWidth) / 2), (menuTextHeight + 20));



            inputPane.setPrefHeight(30);
            inputPane.setPrefWidth(60);     // these three were trouble shooting but I'm scared to remove them now
            inputPane.toFront();
            // text box
            inputPane.applyCss();
            inputPane.layout();
            var textBoxWidth = inputPane.getWidth();
            var textBoxHeight = inputPane.getHeight();
            inputPane.relocate((menuWidth - textBoxWidth) / 2, 100);


//          Making the submit button be in a logical position - UX not just UI
            submitPane.applyCss();
            submitPane.layout();
            var submitButtonWidth = submitPane.getWidth();
            var submitButtonHeight = submitPane.getHeight();
            submitPane.relocate((menuWidth - submitButtonWidth)/2, (menuHeight - submitButtonHeight)/2);
        });

    }

}
