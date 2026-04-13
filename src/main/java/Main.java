import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main {
    static void main() {
        IO.println("TODO: make a JavaFX app");
    }
    // IDEA: cookie clicker. nothing complicated but I havent decided if the cookie will crumble with each click or if i should
    // just have an animation that activates every time it gets clicked. probably the latter. cute background, pink obviously,
    // and you can't have a clicker without a timer. maybe I'll make a dark mode button that changes the background from baby pink
    // (the one i had in mind is apparently cantelope melon pink??? what) to like, a chocolate cosmos red. (aka really deep dark)
    // -> almost certainly going to be stack pane
    // -> shape nodes is easy, i don't think the background counts, so circle for the cookie, rectangle for dark mode and
        // counter, does text count? probably not, so maybe like an inorganic circle and we make chocolate chips for the
        // cookie. I'll have to think of something else too, oh but if control and shapes TOGETHER need to be 3 different,
        // then I'm set because clicking the cookie, the number changing, and the dark mode button, that WITH the shapes
        // has to at least count for four
    // -> event handler, obviously reading the clicks/where they are, EZPZ
    // -> file, network, or database: FUCK IDK
    // -> separate logic from UI, there are different folder for this so hopefully that's not too hard - wait but then what do
        // I put here? Maybe just calls to controller, which calls everything else?
// i actually still don't know what I'm supposed to put in here...oops
}
