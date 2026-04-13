package ui;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;


// LOOK AT THEEEEEEMMM, THEY MADE A CRISP AND CLEAN MAIN FUNCTION AND YOU CAN'T SEE ANYTHING ELSE THEY CODED IN HERE WOW
public class CookieDesign extends Application {
    static void main(String[] args) { // keep this! it is necessary to function
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneBuild myUI = new SceneBuild();
        myUI.buildUI(primaryStage);
    }
}
// it's almost like they're learning :3