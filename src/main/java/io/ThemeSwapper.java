package io;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

//      swaps the background colour between pink and burgundy
public class ThemeSwapper implements EventHandler<MouseEvent> {
//    variables to hold all the info I need to mess with in order to make this happen!
    private final Pane rootPane;
    private final BackgroundFill backgroundColour;
    private final Button themeButton;
    private static final Color BURGUNDY = Color.web("800020");
    private static final Color PRETTYPINK = Color.web("#FDBCB4"); // CANTALOUPE MELON PINK!!!!
    private final Background darkMode = new Background(new BackgroundFill(BURGUNDY, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background lightMode = new Background(new BackgroundFill(PRETTYPINK, CornerRadii.EMPTY, Insets.EMPTY));
    private boolean theme;

//    beautiful constructor in its natural habitat
    public ThemeSwapper(Pane rootPane, BackgroundFill backgroundColour, Button themeButton) {
        this.rootPane = rootPane;
        this.backgroundColour = backgroundColour;
        this.themeButton = themeButton;
    }


//    TIME FOR THE MAGIC
    @Override
    public void handle(MouseEvent event) {
        Paint paint = backgroundColour.getFill();
        String current = paint.toString();

//      this activates... (this is how it goes back and forth)
        if (theme) {
            rootPane.setBackground(darkMode);
            themeButton.setText("Switch to Light");
            theme = !theme;
        } else if (!theme) { // no it's not dumbass (it keeps saying theme is always true)
            rootPane.setBackground(lightMode);
            themeButton.setText("Switch to Dark");
            theme = !theme;
        }
    }
}

