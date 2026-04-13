package io;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

//    MAKES THE COOKIE PULSE!!
public record ImageClick(ImageView cookie) implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        var throb = new ScaleTransition(Duration.seconds(0.1), this.cookie);
        throb.setAutoReverse(true);
        throb.setCycleCount(2);
        throb.setByX(.2);
        throb.setByY(.2);
        throb.playFromStart();
    }
}