import javax.imageio.ImageIO; // A class containing static convenience methods for locating ImageReaders and ImageWriters, and performing simple encoding and decoding.
import javax.swing.*; // Provides a set of "lightweight" (all-Java language) components that, to the maximum degree possible, work the same on all platforms
import java.awt.*; // Contains all of the classes for creating user interfaces and for painting graphics and images.
import java.io.IOException; // Signals that an I/O exception of some sort has occurred.
import java.io.InputStream; // This abstract class is the superclass of all classes representing an input stream of bytes.
import java.net.URI; // Represents a Uniform Resource Identifier (URI) reference
import java.net.http.HttpClient; // An HttpClient can be used to send requests and retrieve their responses.
import java.net.http.HttpRequest; //An HttpRequest instance is built through an HttpRequest builder
import java.net.http.HttpResponse; // An HttpResponse is not created directly, but rather returned as a result of sending an HttpRequest.
//java.lang // Provides classes that are fundamental to the design of the Java programming language. The most important classes are Object, which is the root of the class hierarchy, and Class, instances of which represent classes at run time.

/*
Test_1
Types used in this code:

Classes: 16
InputStream: java.io.InputStream // This abstract class is the superclass of all classes representing an input stream of bytes.
IOException: java.io.IOException // Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations
InterruptedException: java.lang // Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted, either before or during the activity. Occasionally a method may wish to test whether the current thread has been interrupted, and if so, to immediately throw this exception.
JOptionPane: javax.swing // JOptionPane makes it easy to pop up a standard dialog box that prompts users for a value or informs them of something.
Math: java.lang // The class Math contains methods for performing basic numeric operations such as the elementary exponential, logarithm, square root, and trigonometric functions
URI: java.net.URI // Represents a Uniform Resource Identifier (URI) reference
HttpRequest: java.net.http.HttpRequest // An HTTP request
HttpClient: java.net.http // An HTTP Client
BodyHandlers: java.net.http // Implementations of BodyHandler that implement various useful handlers, such as handling the response body as a String, or streaming the response body to a file
JFrame: javax.swing //An extended version of java.awt.Frame that adds support for the JFC/Swing component architecture.
Color: java.awt.* // The Color class is used to encapsulate colors in the default sRGB color space or colors in arbitrary color spaces identified by a ColorSpace.
Image: javax.imageio.ImageIO // The abstract class Image is the superclass of all classes that represent graphical images.
ImageIo: javax.imageio.ImageIO // A class containing static convenience methods for locating ImageReaders and ImageWriters, and performing simple encoding and decoding.
JLabel: javax.swing // A display area for a short text string or an image, or both.
BorderLayout: java.awt // A border layout lays out a container, arranging and resizing its components to fit in five regions: north, south, east, west, and center
String: java.lang // The String class represents character strings.

Interfaces: 2
WindowsConstant: java.swing // Constants used to control the window-closing operation.
HttpResponse:java.net.http.HttpResponse; // An HTTP response.

*/

void main() { //it's the main thing, you know?

    try {
        var avatarStream = getRandomAvatarStream(); // Reference
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //showMessageDialog = class Variable //getMessage = class method //ERROR_MESSAGE = class variable
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException { //it inputs stuff
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    var style = styles[(int)(Math.random() * styles.length)]; //random = class method //length = instance variable //Primitive

    // Generate a random seed
    var seed = (int)(Math.random() * 10000); //random = class method // Primitive

    // Create an HTTP request for a random avatar
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); // create = class method //formated = instance method //Reference
    var request = HttpRequest.newBuilder(uri).build(); //newBuilder = class method //build = instance method //Reference

    // Send the request
    try (var client = HttpClient.newHttpClient()) { //newHttpClient = class method // Reference
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); //send = instance method //BodyHandlers = class variable //ofInputStream = class method //Reference
        return response.body(); //body = instance method
    }
}

void showAvatar(InputStream imageStream) { //displays avatar (no return)
    JFrame frame = new JFrame("PNG Viewer"); //Constructor method //creates JFrame //Reference value //Reference
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setDefaultCloseOperation = instance method //EXIT_ON_CLOSE  = class variable //Reference value
    frame.setResizable(false); //setResizable = instance method //Primative value
    frame.setSize(200, 200); //setSize = instance method //Primative Value
    frame.getContentPane().setBackground(Color.BLACK); //getContentPane = instance method // setBackground = instance method //BLACK = class variable //Reference Value

    try {
        // Load the PNG image
        Image image = ImageIO.read(imageStream); //read = class method //Reference value

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));// Constructor method //creates new image icon //Reference value //Reference
        frame.add(imageLabel, BorderLayout.CENTER); //add = instance method //CENTER = class variable //Reference value

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //showMessageDialog = class method //getMessage = instance method //ERROR_MESSAGE = class variable //Reference value
    }

    frame.setVisible(true); //setVisible = instance method //Primative value
}
