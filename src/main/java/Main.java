public class Main {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight(LightColor.RED);

        System.out.println("Start: " + light.getColor());
        light.next();
        System.out.println("After 1 next: " + light.getColor());
        light.next();
        System.out.println("After 2 next: " + light.getColor());
    }
}

// Not public → allowed in the same file as Main
enum LightColor {
    RED, YELLOW, GREEN
}

// Not public → allowed in same file
record Employee(String name, int id, String title, String department) { }

// Not public → allowed in same file
class TrafficLight {
    private LightColor color;

    public TrafficLight(LightColor startColor) {
        this.color = startColor;
    }

    public LightColor getColor() {
        return color;
    }

    public void next() {
        switch (color) {
            case RED -> color = LightColor.GREEN;
            case GREEN -> color = LightColor.YELLOW;
            case YELLOW -> color = LightColor.RED;
        }
    }
}