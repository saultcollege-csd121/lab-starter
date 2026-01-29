import javax.imageio.ImageIO; //5. contains the basic classes and interfaces for describing the contents of image files, including metadata and thumbnails
import javax.swing.*; //5. for GUI creation using classes such as JFrame, JLabel, and JOptionPane
import java.awt.*; //5. for creating UIs, and painting graphics and images, using classes like Image
import java.io.IOException; //5. for system input and output through data streams, using classes such as InputStream, OutputStream
import java.io.InputStream;
import java.net.URI; //5. for networking and network addressing, using classes such as URI, URL, and InetAddress
import java.net.http.HttpClient; //5. for HTTP networking, using classes such as HttpClient, HttpRequest, and HttpResponse
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//im pretty sure I got all of the packages
//// I spent alot of time making this as precise and as descriptve as possible for my notes. Hopefully it all checks out LOL
/*

Types used in this code:
- IOException | InterruptedException - package: java.io - purpose: used for error handling
- URI - package: java.net - purpose: URI(Uniform Resource Identifier) to uniquely identify a name or address for any web-based asset
- int
- HttpRequest - package: java.net.http - purpose: represents a structured HTTP request message that is sent to a webserver to retreive something or trigger an action
- HttpClient - package: java.net.http - purpose: to send HTTP requests to servers and recieve responses
- HttpResponse<InputStream> - package: java.net.http - purpose: allows for on-demand streaming of the response body
- JFrame - package: javax.swing - purpose: provides a window for Java programs
- Image - package: java.awt - purpose: for graphical images
- JLabel - package: javax.swing - purpose: serves as a display area for non-changing content, such as a single line of text, image icons.

(Add your answers to lab instruction #4 here)

 */

void main() {

    try {
        var avatarStream = getRandomAvatarStream(); //6. avatarStream is an InputStream, making it a reference type
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog (null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);   //1. .showMessageDialog = Class method | .getMessage = Instance method | .ERROR_MESSAGE = Class variable
    }                                                                                                                           //2. .showMessageDialog - makes a dialog window, using 4 parameters, null for default frame, a string which displays the message "Failed to load avatar: " + an error message, the "error" title string for the dialog, and the message type to be displayed which is an "ERROR_MESSAGE"
                                                                                                                                //2. .getMessage - returns a detailed message which is the error message in this case
                                                                                                                                //2. .ERROR_MESSAGE - this determines which type of message is shown, this one is used for error messages.
                                                                                                                                //6. e is the type IOException | InterruptedException, making it a reference type

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
            //6. styles is a String[](square brackets make it a String array), making it a reference type
    var style = styles[(int)(Math.random() * styles.length)]; //1. Math.random() = Class method | styles.length = instance variable
                                                              //2. Math.random() - returns a double greater than or equal to 0.0 and less than 1.0
                                                              //2. styles.length - this stores a value equal to the number of strings inside the array.
                                                              //6. style is the type String, making it a reference type

    // Generate a random seed
    var seed = (int)(Math.random() * 10000); //1. Math.random() = Class method
                                             //2. Math.random() - returns a double between 0.0 and 1.0, which is then multiplied by 10000 and cast to int to generate a random seed.
                                             //6. seed is the type int, making it a primitive type

    // Create an HTTP request for a random avatar
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); //1. URI.create() = class method | "https://api.dicebear.com/9.x/%s/png?seed=%d".formatted() = instance method
                                                                                                //2. URI.create() - takes a string that is parsed into a URI, returns a new URI
                                                                                                //2. "https://api.dicebear.com/9.x/%s/png?seed=%d".formatted() - formats using this string as the format string, returns a formatted string.
                                                                                                //6. uri is the type URI, making it a reference type

    var request = HttpRequest.newBuilder(uri).build(); //1. .newBuilder() = class method | .build() = instance method
                                                       //2. .newBuilder() - creates a HttpRequest builder with the given URI, returns a new request builder
                                                       //2. .build() -  builds and returns a new HttpRequest
                                                       //6. request is the type HttpRequest, making it a reference type


    // Send the request
    try (var client = HttpClient.newHttpClient()) { //1. .newHttpClient() = Class method
                                                    //2. .newHttpClient() - this returns a new HttpClient with default settings
                                                    //6. client is the type HttpClient, making it a reference type

        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); //1. .send = instance method | .BodyHandlers = nested class | .ofInputStream = class method
                                                                                        //2. .send() - sends a given request using the client, returns a response
                                                                                        //2. .BodyHandlers - allows access to the .ofInputStream() method, doesn't return anything on its own.
                                                                                        //2. .ofInputStream() - returns a BodyHandler that processes the response body as an InputStream
                                                                                        //6. response is the type HttpResponse<InputStream>, making it a reference type
        return response.body(); //1. .body() = instance method
                                //2. .body() - returns the response body as an InputStream
    }
}

void showAvatar(InputStream imageStream) { //6. imageStream is the type InputStream, making it a reference type
                                           //7. the argument imageStream is passed as a reference
    JFrame frame = new JFrame("PNG Viewer"); //6. frame is the type JFrame, making it a reference type
                                             //3. new JFrame("PNG Viewer") - creates a new window frame with the title "PNG Viewer"

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //1. .setDefaultCloseOperation() = instance method | .EXIT_ON_CLOSE = class variable
                                                          //2. .setDefaultCloseOperation() - sets an operation that happens by default when user closes the frame
                                                          //2. .EXIT_ON_CLOSE - a constant that represents the exit application operation

    frame.setResizable(false); //1. .setResizable() = instance method
                               //2. .setResizable() - sets whether this frame is resizable by the user, if true; the frame is resizable

    frame.setSize(200, 200); //1. .setSize() = instance method
                             //2. .setSize() - sets the size of the frame by width and height

    frame.getContentPane().setBackground(Color.BLACK); //1. .getContentPane = instance method | .setBackground = instance method | .BLACK =  class variable
                                                       //2. .getContentPane() - returns the contentPane object for this frame
                                                       //2. .setBackground() - sets the background color of this frame
                                                       //2. .BLACK - constant used by .setBackground() to color the background black
    try {
        // Load the PNG image
        Image image = ImageIO.read(imageStream); //1. .read() = Class method
                                                 //2. .read() - as a result of decoding a supplied InputStream, returns an image containing the decoded contents of the input.
                                                 //6. image is the type Image, making it a reference type

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));
                                                    //3. new ImageIcon(image) - creates an icon from the image | new JLabel() - creates a label to display the icon
        frame.add(imageLabel, BorderLayout.CENTER); //1. .add = instance method | .CENTER = Class variable
                                                    //2. .add() - adds an imageLabel to the center of the frame
                                                    //2. .CENTER - constant that represents the centering of the imageLabel in a BorderLayout
                                                    //6. imageLabel is the type JLabel, making it a reference type

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //1. .showMessageDialog() = Class method | .getMessage = instance method | .ERROR_MESSAGE = class variable
                                                                                                                             //2. .showMessageDialog() - makes a dialog window, using 4 parameters, uses frame as a param, a string which displays the message "Failed to load avatar: " + an error message, the "error" title string for the dialog, and the message type to be displayed which is an "ERROR_MESSAGE"
                                                                                                                             //2. .getMessage() -  returns a detailed message which is the error message in this case
                                                                                                                             //2. .ERROR_MESSAGE - this is a constant that determines the type of message shown, this case it's an error message
    }

    frame.setVisible(true); //1. .setVisible() = instance method
                            //2. .setVisible() - this sets the window/frame to be visible
}
