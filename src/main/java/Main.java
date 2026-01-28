import javax.imageio.ImageIO;
//Used for processing images. It allows you to "decode" raw data into a format the computer can draw
//Classes: Service providers and utility classes for reading/writing various image formats
import javax.swing.*;
//Library for creating a GUI
//UI components like windows, buttons, labels, and pre-built dialog boxes.
import java.awt.*;
//It provides the foundation for graphics and windowing.
//Graphics primitives like colors, fonts, and layout managers that control the positioning of UI elements.
import java.io.IOException;
//For system input and output through data streams.
//Tools that move data from one place to another
import java.io.InputStream;
//For system input and output through data streams.
//Tools that move data from one place to another
import java.net.URI;
//Provides the classes for implementing networking applications.
//Identifiers for web resources
import java.net.http.HttpClient;
//API for sending requests and receiving responses over HTTP.
//For web communication, request construction, and body handling.
import java.net.http.HttpRequest;
//API for sending requests and receiving responses over HTTP.
//For web communication, request construction, and body handling.
import java.net.http.HttpResponse;
//API for sending requests and receiving responses over HTTP.
//For web communication, request construction, and body handling.



void main() {

    try {
        var avatarStream = getRandomAvatarStream();
        //Fetches random image data from the API; returns InputStream
        //InputStream and Reference/Object
        showAvatar(avatarStream); //Passes the stream to the UI logic to render the window; returns void.
    } catch (IOException | InterruptedException e) {
        //Exception and Reference/Object
        JOptionPane.showMessageDialog(null, //Displays a graphical error alert popup; returns void.
                "Failed to load avatar: " + e.getMessage(), //Retrieves the error detail string from the exception; returns String.
                "Error", JOptionPane.ERROR_MESSAGE);
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    //java.lang	An exception thrown when a thread is waiting, sleeping, or occupied, and is interrupted.

    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    //java.lang	Represents character strings; the most commonly used type in Java.
    //String[] (Array)	and Reference/Object
    var style = styles[(int)(Math.random()
            //Generates a random double between $0.0$ and $1.0$; returns double.
            //java.lang	Contains methods for performing basic numeric operations like random number generation.
            * styles.length)];//Instance Variable (Array length property)


    var seed = (int)
            //int and Primitive
            (Math.random() * 10000); //Class Method (Static)
    //java.lang	Contains methods for performing basic numeric operations like random number generation.


    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d"
            //Class Method (Static)
            //Converts a string into a URI object; returns URI.
            //java.net	Represents a Uniform Resource Identifier (a string of characters identifying a resource).
            //URI and Reference/Object

            .formatted(style, seed));
    //Instance Method
    //Injects variables into the string; returns a formatted String.
    var request = HttpRequest.newBuilder(uri)
            //Class Method (Static)
            //initiates a request builder; returns HttpRequest.Builder.
            //java.net.http	Represents a single request to be sent via the HttpClient.
            //HttpRequest and Reference/Object
            .build();
    //Instance Method
    //Finalizes the request configuration; returns HttpRequest.


    try (var client = HttpClient.newHttpClient()) {
        //Class Method (Static)
        //Creates a client for network communication; returns HttpClient.
        //java.net.http	Used to send requests and retrieve responses from a server over the network.
        //HttpClient and Reference/Object
        var response = client.send
                //Instance Method
                //Sends the request to the server; returns HttpResponse<InputStream>
                //HttpResponse and Reference/Object
                        (request, HttpResponse.
                                BodyHandlers.ofInputStream());
        //Class Method (Static)
        //Sets the response to be handled as a stream; returns BodyHandler
        //java.net.http	Represents the response received after sending an HttpRequest.
        return response.body();
        //Instance Method
        //Extracts the image data from the response; returns InputStream
    }
}

void showAvatar(InputStream imageStream) {
    //InputStream and Reference/Object
    JFrame frame = new JFrame("PNG Viewer");
    //Constructs the main window object; returns JFrame.
    //Creates a new top-level window with the specified title. This is the main "shell" of your application's UI.
    //javax.swing	The main window class used to create a top-level container for a GUI.
    //java.io	An abstract class representing an input stream of bytes.
    //JFrame and Reference/Object
    //PNG Viewer and Reference
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Class Variable (Static constant)
    //JFrame.EXIT_ON_CLOSE and Primitive
    frame.setResizable(false);
    //Disables or enables window resizing; returns void.
    //false	and Primitive
    frame.setSize(200, 200);
    //Sets the window dimensions in pixels; returns void.
    //200, 200 and Primitive
    frame.getContentPane()
            //Class Variable (Static constant)
            //Gets the frame's internal container; returns Container.
            .setBackground(Color.BLACK);
    //Sets the background color of the component; returns void.
    //java.awt	A class used to encapsulate colors in the default RGB color space.
    //Color.BLACK and Reference

    try {

        Image image = ImageIO.read(imageStream);
        //Class Method (Static)
        //Decodes the input stream into an image; returns BufferedImage.
        //javax.imageio	A utility class providing static methods for reading and writing images.
        //java.awt	The abstract superclass for all classes that represent graphical images.
        //Image	and Reference/Object
        //imageStream and Reference


        JLabel imageLabel = new JLabel(new ImageIcon(image));
        //Converts an image into a displayable icon; returns ImageIcon.
        //Creates a component to hold the icon; returns JLabel.
        //Wraps the Image object (the pixels decoded from the stream) into a format that Swing components like labels can actually display.
        //Creates a display area for the image. By passing the ImageIcon into this constructor, you are telling the label exactly what content it should hold upon creation.
        //javax.swing	A display area for a short text string, an image, or both.
        //javax.swing	An implementation of the Icon interface that paints Icons from Images.
        //JLabel and Reference/Object
        //image	and Reference
        //new ImageIcon(image) and Reference
        frame.add(imageLabel, BorderLayout.CENTER);
        //Class Variable (Static constant)
        //Adds the component to the window layout; returns Component.
        //java.awt	A layout manager that arranges components in five regions (North, South, East, West, Center).
        //imageLabel and Reference
        //BorderLayout.CENTER and Reference

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(),
                //Instance Method
                //javax.swing	A class used to easily pop up standard dialog boxes for messages or errors.
                //java.io	An exception class used to signal that an I/O operation has failed or been interrupted.
                //frame	and Reference
                //"Failed to load" and Reference
                "Error", JOptionPane.ERROR_MESSAGE); //Class Variable (Static constant)
    }

    frame.setVisible(true);
    //Shows or hides the window; returns void.
    //true and Primitive
}
