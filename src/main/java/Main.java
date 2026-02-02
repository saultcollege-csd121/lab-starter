import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


// java.io
// - Purpose: Input/output streams, file/network stream types, IO exceptions
// - Used for: InputStream, IOException
// java.net
// - Purpose: Networking basics, including URIs/URLs
// - Used for: URI
// java.net.http
// - Purpose: Modern HTTP client API for sending HTTP requests and receiving responses
// - Used for: HttpClient, HttpRequest, HttpResponse
// javax.swing
// - Purpose: GUI components (windows, labels, dialogs)
// - Used for: JFrame, JLabel, JOptionPane, ImageIcon
// java.awt
// - Purpose: GUI drawing + layout + colors + images
// - Used for: Color, Image, BorderLayout
// javax.imageio
// - Purpose: Reading/writing images (PNG/JPG/etc)
// - Used for: ImageIO


void main() {

    try {
        // getRandomAvatarStream() is a function call
        // Purpose: downloads an avatar image and returns the response body as an InputStream
        var avatarStream = getRandomAvatarStream();
        // showAvatar(avatarStream) is a function call
        // Purpose: opens a GUI window and displays the image read from the InputStream
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {
        // JOptionPane.showMessageDialog(...) is a FUNCTION CALL
        // showMessageDialog is a CLASS METHOD
        // e.getMessage()
        // getMessage is an INSTANCE METHOD
        // JOptionPane.ERROR_MESSAGE
        //ERROR_MESSAGE is a CLASS VARIABLE
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    // Math.random() is a FUNCTION CALL
    //random is a CLASS METHOD (static method) of Math
    // styles.length
    // length is an INSTANCE VARIABLE
    var style = styles[(int)(Math.random() * styles.length)];

    // Generate a random seed
    var seed = (int)(Math.random() * 10000);

    // "https://...".formatted(style, seed)
    // formatted is an INSTANCE METHOD
    // URI.create(...)
    // create is a CLASS METHOD
    // Create an HTTP request for a random avatar
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
    // HttpRequest.newBuilder(uri)
    // newBuilder is a CLASS METHOD
    // .build()
    // build is an INSTANCE METHOD
    var request = HttpRequest.newBuilder(uri).build();

    // Send the request
    try (var client = HttpClient.newHttpClient()) {
        // HttpClient.newHttpClient()
        // newHttpClient is a CLASS METHOD
        // client.send(request, handler)
        // send is an INSTANCE METHOD
        // HttpResponse.BodyHandlers.ofInputStream()
        // BodyHandlers is a NESTED TYPE inside HttpResponse
        // ofInputStream is a CLASS METHOD
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        // response.body()
        // body is an INSTANCE METHOD
        return response.body();
    }
}

void showAvatar(InputStream imageStream) {
    // imageStream parameter is InputStream (object/reference type)
    // It refers to a stream object, not a primitive value
    JFrame frame = new JFrame("PNG Viewer");
    // frame.setDefaultCloseOperation(...)
    // setDefaultCloseOperation is an INSTANCE METHOD of JFrame
    // JFrame.EXIT_ON_CLOSE
    // EXIT_ON_CLOSE is a CLASS VARIABLE
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setResizable(false)
    // setResizable is an INSTANCE METHOD
    frame.setResizable(false);
    // frame.setSize(200, 200)
    // - setSize is an INSTANCE METHOD
    frame.setSize(200, 200);
    // frame.getContentPane()
    // getContentPane is an INSTANCE METHOD
    // .setBackground(Color.BLACK)
    // .setBackground is an INSTANCE METHOD
    // Color.BLACK
    // BLACK is a CLASS VARIABLE
    frame.getContentPane().setBackground(Color.BLACK);

    try {
        // ImageIO.read(imageStream)
        // read is a CLASS METHOD
        // Load the PNG image
        Image image = ImageIO.read(imageStream);
        // new ImageIcon(image) :creates an ImageIcon wrapper around an Image
        // new JLabel(...)      :creates a label component that can display the icon
        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        // frame.add(imageLabel, BorderLayout.CENTER)
        // add is an INSTANCE METHOD
        // BorderLayout.CENTER
        // CENTER is a CLASS VARIABLE
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {
        // JOptionPane.showMessageDialog(...)
        // showMessageDialog is a CLASS METHOD
        // e.getMessage()
        // getMessage is an INSTANCE METHOD on IOException
        // JOptionPane.ERROR_MESSAGE
        // ERROR_MESSAGE is a CLASS VARIABLE
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    // frame.setVisible(true)
    // - setVisible is an INSTANCE METHOD
    frame.setVisible(true);
}
