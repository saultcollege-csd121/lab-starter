import javax.imageio.ImageIO; //This package contains the basic classes and interfaces for describing the contents of image files
import javax.swing.*;//whole lot of API and GUI implementations to make designing much smoother
import java.awt.*;//Contains classes used for painting and designing images on the screen
import java.io.IOException;//general class for an exception when an error has occurred
import java.io.InputStream;//abstract class representing an input stream of bytes
import java.net.URI;//this class provides constructors for creating URI instances from their components
import java.net.http.HttpClient;//used to send requests and retrieve their responses
import java.net.http.HttpRequest;//builds an http request based on many pre-set components such as uri, body, header, and other such things
import java.net.http.HttpResponse;//not created directly but instead a result of sending the http request


/*
    TYPES
IOException - class - part of the IO package : signals that an i/o exception of some sort has occurred
interruptedexception - class - part of the lang package : Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted
string - class - part of the lang package : Represents a character of strings
joptionpane - class - part of the java swing package : This makes it much easier for the programmer to pop up a standard dialogue box
math - class - part of the math package : This package simplifies the process of performing numeric calculations in code
URI - class - part of the io package : Helps generate the highest level of URI in string form
httprequest - class - part of the net package : Generates a client side request utilizing tcp connections to send and recieve multiple internet resources such as html documents
httpsclient - class - part of the net package : generating the client side of tcp connection requests
jframe - class - part of the swing package : creates a new, initially visible frame with the specified title
image - class - part of the awt package : a superclass of all classes that represent graphical images
imageio - class - part of the io package : A class containing static convenience methods for locating ImageReaders and ImageWriters, and performing simple encoding and decoding.
jlabel - class - part of the swing package : A display area for a short text string or an image, or both
borderlayout - class - part of the awt package : A border layout lays out a container, arranging and resizing its components
imageicon - class - part of the swing package : Creates an image icon from an image object
bodyhandlers - class - part of the net package : Implements various useful handlers
 */

/*
    VARIABLES
avatarStream - object/reference of input stream
e - object/reference of IOException
styles - Object (array of strings)
style - object (string)
seed - primitive (int, distinctly not Integer)
uri - object/reference of URI
request - object/reference of httprequest
client - object/reference of httpclient
response - object/reference of httpresponse with <Input stream> as an argument
frame - object/reference of jframe
image - object/reference of Image
imagelabel - object/reference of jlabel
 */

/*
    SHOWAVATAR ARGUMENTS
avatarstream - reference

On the other hand, if you meant every argument that gets passed to functions WITHIN THE SCOPE of the showAvatar function, here:
"PNG Viewer" - Object
Exitonclose - int/primitive
false - boolean/primitive
200, 200 - int/primitive
color.PINK - object/reference
imagestream - object/reference
image - object/reference
borderlayout.center - object/reference
e - object/reference
frame- object/reference
"" - String/Object
error message - int/primitive
true - boolean/primitive
 */

void main() {

    try {
        var avatarStream = getRandomAvatarStream();
        //i have to imagine that this retrieves an image from the user to act as an avatar
        showAvatar(avatarStream);
        //and that this displays the aforementioned retrieved image
        //calls a constructor method to establish a consistent format of the user's avatar
    }
    catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);//loads a dialog box to display messages
    }//class method^
    // instance method
    // class variable
// ^constructor method to create text dialog with specific formatting
}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    var style = styles[(int)(Math.random() * styles.length)];
    // the random method randomly selects and returns a number between 0.0 and 1.0
//class method and instance variable
    // Generate a random seed

    var seed = (int)(Math.random() * 10000);
    // the random method randomly selects and returns a number between 0.0 and 1.0
//      class method

    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
    //      class method
    //      instance method
    //      'create' is a constructor method that creates a proper uri based on the provided string

    var request = HttpRequest.newBuilder(uri).build();
//     class method.instance method
    //newBuilder takes the returned and formatted uri and formats it again into a new builder
    // Send the request

    try (var client = HttpClient.newHttpClient()) {
        //returns a new http client with default settings-IS ALSO A CONSTRUCTOR METHOD I THINK
       //       class method

        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        //          instance method
        //          class variable.class method

        return response.body();
        //returns the body?? idfk that's all i got
    }// instance method
}

void showAvatar(InputStream imageStream) {
    JFrame frame = new JFrame("PNG Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //decides what happens to the application when the user closes it, returns nothing
  // instance method
    // class method

    frame.setResizable(false);
    //determines whether or not the window of the application is resizable, returns nothing
    //      instance method

    frame.setSize(200, 200);
    //determines the window size of the application
    //      instance method

    frame.getContentPane().setBackground(Color.PINK);
    //first method simply gets the content pane of the application and returns that, second method is applying a specific colour the background of the application and returns nothing
//      instance method.instance method     class variable

    try {
        Image image = ImageIO.read(imageStream);
        //decides a supplied image and returns a 'BufferedImage'
        //class method
        // Create a JLabel to display the image

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        //(creates a new jlabel with the specified image(creates an image icon from an image object, using subtext as description if available))

        frame.add(imageLabel, BorderLayout.CENTER);
        //adds the specified component (label and layout) to the end of this container (frame)
        //instance method
        // class variable

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }//     class method

    frame.setVisible(true);
    //determines the visibility of the frame of the window
}//     instance method
