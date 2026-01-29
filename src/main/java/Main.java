import javax.imageio.ImageIO; // package for image input/output. It contains classes for reading and writing image files
import javax.swing.*; // package for graphical user interface. Contains classes for windows, buttons, labels and dialogs
import java.awt.*; //  Used for graphics and layout. It have classes for colors, images and layout managers
import java.io.IOException; // Its used for input/output operations. Contains classes for handling IO errors
import java.io.InputStream; // Its a package for stream data. It contains classes for reading bytes from files
import java.net.URI; // This package is used for networking. It contains classes for representing URLs and addresses
import java.net.http.HttpClient; // This is a package for HTTP communication. It have classes to send requests to web servers
import java.net.http.HttpRequest; // Its a package for HTTP requests, contains classes to build/configure requests
import java.net.http.HttpResponse; // This is a package for HTTP responses, contains classes for storing server responses


/*

Types used in this code:
JOptionPane: class : used to show message dialogs
Math: class : used to generate random numbers
HttpRequest: class : represents a request sent to a server
HttpClient: class : sends the request and gets the response
HttpResponse: interface : stores the server response
BodyHandlers: class : helps convert the response body to InputStream
JFrame: class : creates a window for the program
Color: class : represents colors like black
Image: class : represents an image object
ImageIO: class : reads image files
BorderLayout: class : organizes components in regions

(Add your answers to lab instruction #4 here)

 */

void main() {

    try {
        // avatarStream: reference type
        var avatarStream = getRandomAvatarStream(); // getRandomAvatarStream() gets a random avatar image as InputStream, returns InputStream
        showAvatar(avatarStream); // showAvatar() shows the avatar in a window, returns void
    } catch (IOException | InterruptedException e) {
        // showMessageDialog it's a class method. It shows an error message, returns void
        // getMessage() it's an instance method. It returns the error text
        // ERROR_MESSAGE it's a class constant for error dialog
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    // length it's an instance variable
    // Math it's a name type
    // random() it's a class method. It returns a random double number
    var style = styles[(int)(Math.random() * styles.length)]; // style: reference type

    // Generate a random seed
    // Math it's a name type
    // random() it's a class method. returns a random double number
    var seed = (int)(Math.random() * 10000); // seed: primitive type

    // Create an HTTP request for a random avatar
    // URI it's a constant
    // create() it's an class method. It creates a URI object, returns URI
    // formatted() it's an instance method. It formats the string with values, returns String
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); // uri: reference type
    // HttpRequest it's a name type
    // newBuilder() it's a class method. It creates a request builder and returns Builder
    // build() it's an instance method. Creates the HttpRequest and returns HttpRequest
    var request = HttpRequest.newBuilder(uri).build(); // request: reference type

    // Send the request
    // HttpClient it's a name type
    // newHttpClient() it's a class method. Creates a HttpClient object, returns HttpClient
    try (var client = HttpClient.newHttpClient()) { // client: reference type
        // send() it's a instance method. It sends request and returns response and returns HttpResponse
        // HttpResponse it's a name type
        // BodyHandlers it's a name type
        // ofInputStream it's a class method. Converts body to InputStream
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); // response: state whether primitive or object/reference type:
        // body() it's an instance method. Returns the InputStream from response
        return response.body();
    }
}
void showAvatar(InputStream imageStream) { // imageStream: reference type
    JFrame frame = new JFrame("PNG Viewer"); // constructor creates a window with a title
    // setDefaultCloseOperation it's an instance method. It sets what happens when window is closed
    // JFrame it's a name type
    // EXIT_ON_CLOSE it's a class variable
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setResizable() it's an instance method. Makes a window resizable or not
    frame.setResizable(false);
    // setSize() it's an instance method. Sets size of the window
    frame.setSize(200, 200);
    // getContentPane() it's an instance method. Gets the content area
    // setBackground it's an instance method. Sets background color
    // Color it's a name type
    // BLACK it's a class variable
    frame.getContentPane().setBackground(Color.BLACK);

    try {
        // Load the PNG image
        // ImageIO it's a name type
        // read() it's a class method. Reads image from stream and returns Image
        Image image = ImageIO.read(imageStream);

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image)); // constructor makes a label with the image inside
        // add() it's an instance method. adds label to the frame
        // BorderLayout it's a name type
        // CENTER it's a class variable
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {
        // showMessageDialog() it's a class method. It shows error dialog
        // getMessage() it's an instance method. Returns error message
        // ERROR_MESSAGE it's a class variable
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // setVisible() it's an instance method. Makes the frame visible
    frame.setVisible(true);
}
