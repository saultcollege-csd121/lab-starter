import controllers.DashboardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.DashboardView;

/**
 * Entry point for the app.
 * Creates the controller and the view, then puts them together and opens the window.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {            // controller handles the logic, view handles the UI
        DashboardController controller = new DashboardController();
        DashboardView view = new DashboardView(controller);

        Scene scene = new Scene(view.buildRoot(), 750, 520);

        stage.setTitle("Market Dashboard");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(420);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}