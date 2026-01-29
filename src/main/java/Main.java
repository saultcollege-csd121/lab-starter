import javax.imageio.ImageIO;  // ImageIO is a class that provides static methods for reading and writing images.
import javax.swing.*; // swing is the primary GUI toolkit for building desktop applications. Provides a set of components for creating windows dialogs, buttons, text fields, tables etc.
import java.awt.*; // awt contains the classes for creating interfaces and painting graphics and images.
import java.io.IOException; // This is the general class for exceptions, produced when failures occur.
import java.io.InputStream; // superclass of all classes that representing an input stream of all bytes. Provides methods for reading raw byte data from a source.
import java.net.URI; //URI represents a uniform resource identifier. URI is a string of characters that identifies a resource, either location, name or both.
import java.net.http.HttpClient; //HttpClients are used to send requests and accepts responses. Requests to web servers, and recommended way to make HTTP calls.
import java.net.http.HttpRequest; // HttpRequests represents an HTTP request that you want to send to a server. Encapsulates information like url, headers, body, etc. to create a request.
import java.net.http.HttpResponse; // Not created directly. It is returned as a result of send the HttpRequest. Provides access to all the information returned by the server; status code, headers, body etc.


/*

Types used in this code:
- BorderLayout - java.awt, class - Layout manager that positions components in regions such as CENTER
- Color - java.awt , class - Represents predefined colors used in GUI components
- HttpClient - java.net.http, class - Sends HTTP requests and receives responses
- HttpRequest - java.net.http, class - Represents an HTTP request sent to a server
- HttpResponse - java.net.http, interface - Represents the response received from an HTTP request
- Image - java.awt, class - Graphical image data
- ImageIcon - javax.swing, class - Wraps an image so it can be displayed in swing components
- ImageIO - javax.imageio, class - Utility class used to read image data from an InputStream
- IOException - java.io, class - Thrown when an input/output operation fails
- InputStream - java.io, class - Represents a stream of raw byte data
- InterruptedException - java.lang, class - Thrown when a thread is interrupted while waiting
- JFrame - javax.swing, class - window used to display the GUI
- JLabel - javax.swing, class - a Swing component used to display text or an image
- JOptionPane - javax.swing, class - Display pop-up dialog boxed for message or errors
- Math - java.lang, class - math utility methods
- String - java.lang, class - Text data
- URI - java.net, class - uniform resource identifier



(Add your answers to lab instruction #4 here)

 */

void main() {

    try {
        var avatarStream = getRandomAvatarStream();
        //avatarStream is an object

        showAvatar(avatarStream);
        //showAvatar() shows the avatar image in a GUI window and returns a void


    } catch (IOException | InterruptedException e) {
        //.ERROR_MESSAGE is a class variable.
        //.getMessage() is an instance method. It returns the error message stored inside the exception as a string.
        //.showMessageDialog is a Class method. It is called using the class name JOptionPane. It brings up dialog that displays error on the screen.

        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    //String[] is an object
    //styles is an object and an array
    // .length  is an instance variable of the array. It gives the number of items in the array and returns an int (primitive).
    //.random() is a class method. It returns a double (primitive) between 0-1
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    var style = styles[(int)(Math.random() * styles.length)];

    // Generate a random seed
    //.random() is a class method and this code returns a double. The purpose of the code is to create a number that is random (whole) to produce a random seed value. Seed is an int (primitive)
    var seed = (int)(Math.random() * 10000);

    // Create an HTTP request for a random avatar
    //seed is an int and primitive
    // .create is a class method.
    //create() converts the string into a URI object
    // the returned value is URI (reference)
    //.formatted is an instance method. Inserts style and seed into the URL string. The returned value is a string (reference)

    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
    // .newBuilder() is a class method. It creates an HTTP request builder
    //uri is an object
    // .build() is an instance method and returns a completed HttpRequest
    //request is a reference variable
    var request = HttpRequest.newBuilder(uri).build();


    try (var client = HttpClient.newHttpClient()) {
        // Send the request
        //.newHttpClient is a class method
        //client is a reference variable
        //.send() is an instance method
        //.BodyHandlers is a static nested class
        //.ofInputStream() is a class method
        //.body() is an instance method
        //body() returns the response body as InputStream
        //send() sends the HTTP request
        //ofInputStream() specifies the response body as InputStream
        //response  HttpResponse is a reference variable
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        return response.body();
    }
}

void showAvatar(InputStream imageStream) {
    // new JFrame() is a constructor
    //frame JFrame is a reference variable
    // .setDefaultCloseOperation is an instance method
    //.EXIT_ON_CLOSE is a class variable
    //.setResizable is an instance method
    //.setSize is an instance method
    //.getContentPane is an instance  method
    // .setBackground is an instance method
    //.BLACK is a class variable

    JFrame frame = new JFrame("PNG Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(200, 200);
    frame.getContentPane().setBackground(Color.BLACK);

    try {
        // Load the PNG image
        //.read () is a class method
        //read() converts InputStream into an Image
        //image Image is a reference variable
        //new JLabel() is a constructor
        //new ImageIcon () is a constructor
        //imageLabel JLabel is a reference variable
        //.add() is a instance method
        //.CENTER is a class variable
        // Adds image to the center of the window
        Image image = ImageIO.read(imageStream);

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {
        // .showMessageDialog is a class method
        // .getMessage is an instance method
        //.ERROR_MESSAGE is a class variable
        // Displays an error dialog if the image fails to load
        //e IOException is a reference variable
        //.setVisible is an instance method
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    frame.setVisible(true);
}


