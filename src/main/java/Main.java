import javax.imageio.ImageIO;
//The package describes the contents of image files(ImageIO class)
import javax.swing.*;
//The package contains lightweight components that can create a GUI
import java.awt.*;
//The package creates user interfaces and paints graphics and images
import java.io.IOException;
//The package provides information for input output datastreams(Class,IOException,InputStream)
import java.io.InputStream;
import java.net.URI;
//This package provides the classes to implement networking applications(Class, HttpClient,HttpRequest,HttpResponse)
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*

Types used in this code:
1-inputstream
 -java.io
 - returns the next byte of info for
 2-ImageIO
 -javex.imageio
 -encodes and decodes image information
 3-IOException
 -java.io
 -signals if a I/O exception has occured
 4-URI
 -java.net
 -represents a uniform recource dentifyer
 5-httpClient
 java.net.http
 -sends and recieves requests
 6-HttpResponse
 -jave.net.http
 - accesses the response text
 7.-httpRequest
 -java.net.http
 -requests the info to make a uri link
 -8 string
 -java.lang
 -represents charecter stings
 -10 Jframe
 -java.awt.frame
 -Creates a new frame
 -11 Boolean
 -java.lang
 -sets somthing to true or false
(Add your answers to lab instruction #4 here)

 */

void main() {

    try {
        var avatarStream = getRandomAvatarStream();
        //avatarStream is type input stream and is a reference type
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {
        //  JOptionPane.showMessageDialog () = "class method" returns an error dialog (Void)
        //  2.e.getMessage() = "instance method" return  a string of text
        //  3.  JOptionPane.ERROR_MESSAGE) = "class variable"
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
// styles is a string type and  is a reference type
  // Math.random() = Class method returns a double that is greater or equal to 0 and lessthan 1
   // styles.length = instance variable
    var style = styles[(int)(Math.random() * styles.length)];
//style is a string type and is a reference type
  // Generate a random seed
    // Math.random() = Class Method returns a double that is greater or equal to 0 and lessthan 1 that's then multiplied by 10000
    var seed = (int)(Math.random() * 10000);
//seed is an int type and a primitive type
    // Create an HTTP request for a random avatar
  // URI.create() = Class Method returns a URI from a string input
    //"https://api.dicebear.com/9.x/%s/png?seed=%d".formatted() = instance method returns a string from a URI
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
//uri is a URI type and is a reference type
    //HttpRequest.newBuilder() class method that creates a https request
    var request = HttpRequest.newBuilder(uri).build();
//request is a HttpRequest type and is a reference type


    // Send the request
    //HttpClient.newHttpClient() = Class Method returns a https client with base settings
    try (var client = HttpClient.newHttpClient()) {
//client is a HttpClient type and is a refernce type
        // client.send() = instance method returns an http response containg its status body and headers
      //HttpResponse.BodyHandlers = Class variable
        //BodyHandlers.ofInputStream() = Class Method returns a streaming response BodyHandler
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
//response is a HttpResponse type and is a reference type
        //response.body() = instance method returns the body
        return response.body();
    }
}

void showAvatar(InputStream imageStream) {
    // Creates a new frame for the image to go in
    JFrame frame = new JFrame("PNG Viewer");
//frame is a JFrame type and a reference type
    // frame.setDefaultCloseOperation() = instance method sets the deafult of what the user would like to happen
  // Jframe.EXIT_ON_CLOSE = class variable
    // frame.setResizable() = instance method sets the resizability of the frame to false unless told otherwise
  //  frame.setSize() = instance method sets the hight and width of the frame to be 200 by 200
    // frame.getContentPane() = instance method returns the contentpane
  // getContentPane().setBackground() = instance method sets the background color to black
    // Color.BLACK = class variable
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//argument is a reference
    frame.setResizable(false);
//argument is a primitive
    frame.setSize(200, 200);
//argument is a primitive
    frame.getContentPane().setBackground(Color.BLACK);
//argument is a reference
    try {
        // Load the PNG image
      //ImageIO.read() = Class method returns a buffered image from the input or null
        Image image = ImageIO.read(imageStream);

        // Create a JLabel to display the image
      // creates a Jlabel for the previous image\
        // creates an image icon from an image object
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        // frame.add() = instant method changes the layout related information
      // BorderLayout.CENTER = class variable
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {
        // JOptionPane.showMessageDialog() = class method checks to see if the image is headless or not
      // e.getMessage() = instant method returns a string of text
        // JOptionPane.ERROR_MESSAGE = class variable
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
// frame.setVisible() = instant method sets the image to be visible
    frame.setVisible(true);
}
