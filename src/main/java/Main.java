import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/*

Types used in this code:
InputStream- It represents a stream of binary data used for reading bytes
HttpClient- It is used to send HTTP requests and recieve responses
JFrame- Is a tio level window used for building GUI applications

(Add your answers to lab instruction #4 here)

 */

void main() {

    try {
        var avatarStream = getRandomAvatarStream();
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    //This is a string type and it is a reference
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    //.random() is a class method because it is static so it is called on the class, not an object
    var style = styles[(int)(Math.random() * styles.length)];
    //.length is an instance variable of an array object that stores the number of elements.

    // Generate a random seed
    //This is a int type and it is primitive
    var seed = (int)(Math.random() * 10000);

    // Create an HTTP request for a random avatar
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
    //the .create is a class method that returns the URI object.
    var request = HttpRequest.newBuilder(uri).build();

    // Send the request
    try (var client = HttpClient.newHttpClient())
     //.newhttpclient is a class method that returns the new httpclient object.
    {
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        //.send is a instance method that returns the httpresponse<inputstream>
        return response.body();
        //.body() is a instance method that returns an InputStream
    }
}
//this is a inputstream and it is a reference
//The showavatar is a inputstream type and is a object
void showAvatar(InputStream imageStream) {
    JFrame frame = new JFrame("PNG Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(200, 200);
    //.setSize is an instance method that returns void
    frame.getContentPane().setBackground(Color.BLACK);

    try {
        // Load the PNG image
        Image image = ImageIO.read(imageStream);
        //imageIO.read(imageStream) it reads the image data from an input stream

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        //JOptionPane.showMessageDialog shows a popup dialog box and returns nothing
    }

    frame.setVisible(true);
}
