package io;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


//      controls the actual counter of the cookie clicker
public class ClickerCounter {
    static int count;
    public void main(){}

    public static void incCount() { // increments the cookie count every time you hit the button (hopefully)
        count += 1;
    } // ups the count

    public static int getCount(){ // returns the cookie count for file saving
        return count;
    } // gets the count

    public static void setCount(int newCount) { // sets the count (we want 0 on launch) - didn't end up needing it!
        count = newCount;
    }

    //     Actually updates the count on the screen after clicking the button!!
    public record clickerCounter(Text clickCount) implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ClickerCounter.incCount();
            clickCount.setText("Times clicked: " + ClickerCounter.getCount());
        }
    }
}
