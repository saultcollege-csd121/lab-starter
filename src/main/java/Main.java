// Package explanation.

// Related to ImageIO operations like buffers, readers...
import javax.imageio.ImageIO;
/* Consists of interfaces and classes that define Java Scripting Engines and provides a
   framework for their use in Java applications.*/
import javax.swing.*;
// Contains all the classes for creating user interfaces and for painting graphics and images.
import java.awt.*;
// Provides for system input and output through data streams, serialization and the file system.
import java.io.IOException;
import java.io.InputStream;
// Provides the classes for implementing networking applications. (Low-Level API & High-Level API)
import java.net.URI;
// Defines the HTTP Client and WebSocket APIs.
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*

Types used in this code:

* Type: InputStream -> Package: java.io;
        "An abstract class representing an input stream of bytes. Generalization of many possible applications
         such as AudioInputStream, ByteArrayInputStream, FileInputStream..."
* Type: IOException -> Package: java.io;
        "A general class for exceptions produced by failed or interrupted I/O operations."
* Type: InterruptedException -> Package: java.lang;
        "Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted,
         either before or during the activity."
* Type: JOptionPane -> Package: javax.swing;
        "Pops up a standard dialog box that prompts users for a value or informs them of something."
* Type: String -> Package: java.lang;
        "The well-known, immutable class String that represents every character string."
* Type: Math -> Package: java.lang;
        "The good-old, basic API containing pure methods for performing basic numeric operations
         such as the elementary exponential, logarithm, square root, and trigonometric functions."
* Type: URI -> Package: java.net;
        "Represents a Uniform Resource Identifier (URI) reference. There are absolute and relative URIs;
         hierarchical and opaque URIs."
* Type: HttpRequest -> Package: java.net.http;
        "A general, abstract class representing an HttpRequest. It's with an HttpRequest builder."
* Type: HttpClient -> Package: java.net.http;
        "A general, abstract class representing an HTTP Client. It can be used to send requests and retrieve
         their responses; created through a builder."
* Type: HttpResponse -> Package: java.net.http;
        "An interface representing an HTTP Response. Not created directly, but returned as a result of sending
         an HttpRequest. An HttpResponse is made available when the response status code and headers have been
         received, and typically after the response body has also been completely received.
* Type: BodyHandlers -> Package: java.net.http;
        "Implementations of BodyHandler that implement various useful handlers for an HttpResponse, such as
         handling the response body as a String, or streaming the response body to a file."
* Type: JFrame -> Package: javax.swing;
        "An extended version of java.awt.Frame that adds support for the JFC/Swing component architecture."
* Type: Color -> Package: java.awt;
        "The Color class is used to encapsulate colors in the default sRGB color space or colors in arbitrary
         color spaces identified by a ColorSpace."
* Type: Image -> Package: java.awt;
        "The abstract class Image is the superclass of all classes that represent graphical images. The image
         must be obtained in a platform-specific manner."
* Type: ImageIO -> Package: javax.imagio;
        "A class containing static convenience methods for locating ImageReaders and ImageWriters, and
         performing simple encoding and decoding."
* Type: JLabel -> Package: javax.swing;
        "A display area for a short text string or an image, or both. A label does not react to input events.
         As a result, it cannot get the keyboard focus."
* Type: ImageIcon -> Package: javax.swing;
        "An implementation of the Icon interface that paints Icons from Images. Images that are created from
         a URL, filename or byte array are preloaded using MediaTracker to monitor the loaded state of the
         image."
* Type: BorderLayout -> Package: java.awt;
        "A border layout lays out a container, arranging and resizing its components to fit in five regions:
         north, south, east, west, and center."

 */

void main() {

    try {
        // Returns the "body" of an HTTP request for a randomly generated *avatar*.
        // @avatarStream is a InputStream reference type
        var avatarStream = getRandomAvatarStream();
        /* Shows the avatar by creating a new JFrame, sets basic properties for it,
           tries to read an "Image" to add to the frame with a new JLabel for it to
           finally make it visible; if it doesn't work, show a message pop-up with
           JOptionPane.*/
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {    // @e is a IOException or InterruptedException reference type.
    /*  (1) @showMessageDialog() is a *class method*.
        (2) @getMessage() is an *instance method*.
        (3) @ERROR_MESSAGE ia a *class variable* (constant). */
        /* (1) ERROR Pop-up window.
        *  (2) Returns the default message String for the catched Exception. */
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // @e is a IOException or InterruptedException reference type.
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    // @styles is a String array reference type.
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    /* (1) @random() is a *class method*.
    *  (2) @length is an *instance variable* (constant). */
    // Returns a *random* double value between 0 and 1.
    // @style is a String reference type.
    var style = styles[(int)(Math.random() * styles.length)];

    // Generate a random seed
    /* @random is a *class method*. */
    // Returns a *random* double value between 0 and 1.
    // @seed is an int primitive type.
    var seed = (int)(Math.random() * 10000);

    // Create an HTTP request for a random avatar
    /* (1) @create() is a *class method*.
    *  (2) @formatted() is an *instance method*. */
    /* (1) Returns a newly created URI by parsing the given String.
    *  (2) Returns a formatted String of the original String with the specified arguments. */
    // @uri is a URI reference type.
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
    /* (1) @newBuilder() is a *class method*.
    *  (2) @build() is an *instance method*. (abstract = instructions undefined) */
    /* (1) Constructor method for the Builder TYPE in the HttpRequest class with a uri String.
    *  (2) Constructor method for the HttpRequest defined within the Builder implementation. */
    // @request is a HttpRequest reference type.
    var request = HttpRequest.newBuilder(uri).build();

    // Send the request
    // @newHttpClient() is a *class method*.
    // Equivalent to newBuilder().build().
    // @client is a HttpClient reference type.
    try (var client = HttpClient.newHttpClient()) {
        /* (1) @send() is an *instance method*. (abstract = instructions undefined)
        *  (2) @BodyHandlers is a *class variable*. (a class member specifically)
        *  (3) @ofInputStream() is a *class method*. */
        /* (1) Sends the given request with the HttpClient instance and returns and HttpResponse
        *      containing the response status, headers, and body.
        *  (2) Returns the return of a BodyHandler<InputStream>, which is a BodySubscriber<InputStream>. */
        // @response is a HttpResponse<InputStream> reference type.
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        // @body is a *instance method*. (abstract = instructions undefined)
        // Returns the body of the HttpResponse. Null if returned from an invocation of previousResponse()
        return response.body();
    }
}

void showAvatar(InputStream imageStream /* <- this is a reference value */) {
    // Constructor method. Creates a new, invisible Frame with the specified title String.
    // @frame is a JFrame reference type.
    JFrame frame = new JFrame("PNG Viewer");
    /* (1) @setDefaultCloseOperation() is an *instance method*.
       (2) @EXIT_ON_CLOSE is a *class variable*. (constant)
     */
    // Sets the Default close operation from static options within the class.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // @setResizable() is an *instance method*.
    // Sets if the Frame is resizable or not.
    frame.setResizable(false);
    // @setSize() is an *instance method*.
    // Sets the size of the Frame with the given width and height
    frame.setSize(200, 200);
    /* (1) @getContentPane() is an *instance method*.
       (2) @setBackground() is an *instance method*.
       (3) @BLACK is a *class variable*. (constant)
     */
    /* (1) Returns the contentPane for this Frame. (The GUI block were it is displayed)
    *  (2) Sets the background color of this component. If the param is null, it will inherit
    *      the parent's background. */
    frame.getContentPane().setBackground(Color.BLACK);

    try {
        // Load the PNG image
        // @read() is a *class method*.
        /* Returns a BufferedImage by processing an InputStream;
           returns null if no ImageReader is registered. */
        // @image is an Image reference type.
        Image image = ImageIO.read(imageStream);

        // Create a JLabel to display the image
        // Constructor methods. Makes an ImageIcon with what it read to make a JLabel with it.
        // @imageLabel is a JLabel reference type.
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        /* (1) @add() is an *instance method*.
           (2) @CENTER is a *class variable*. (constant)
         */
        // "Appends" the image with its label to the container. (contentPane)
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {   // @e is a IOException or InterruptedException reference type.
        /*  (1) @showMessageDialog() is a *class method*.
        (2) @getMessage() is an *instance method*.
        (3) @ERROR_MESSAGE ia a *class variable* (constant). */
        /* (1) ERROR Pop-up window.
         *  (2) Returns the default message String for the catched Exception. */
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);    // @e is a IOException or InterruptedException reference type.
    }
    // @setVisible() is an *instance method*.
    // Sets the Frame to be visible.
    frame.setVisible(true);
}
