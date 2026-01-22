/* ********************************
import java.
Add your Lab 1 code to this file

 */

import java.io.FileWriter;
import java.io.IOException;

/**
 * Say a colour
 * @param colour
 * @return the input colour 5 times
 */
void say_Colour ( String colour) {
    int timer = 0;

    while (timer < 5) {
        System.out.println(colour);
        timer += 1;
    }

}

void main(){
    //The following code asks for a colour and responds saying that it is a cool colour.
    String response = IO.readln ("What is your favourite color? ");
    System.out.println( response + " is a cool colour!" );

    //The following code creates a file with "this is a file" written on it, if its sucsessful it returns to the terminal conformation.
    try(FileWriter f = new FileWriter("C:\\Users\\benst\\OneDrive\\Desktop\\test.txt")){
        f.write("This is a file.");
        System.out.println("File is printed");
    }
    //The following code returns that it cant locate the file if the file from the previous code is lost.
    catch(FileNotFoundException filenotfound) {
        System.out.println("Cannot locate file");
    }
    //The following code returns that it can't write the file if there is no file
    catch(IOException filenotwritten){
        System.out.println("Could not write file");
    }
    //The following code asks for 3 numbers and returns them in a list
    String responseOne = IO.readln("What is your first favourite number? ");
    String responseTwo = IO.readln("What is your second favourite number? ");
    String responseThree = IO.readln("What is your third favourite number? ");
    var numbers = List.of(responseOne, responseTwo, responseThree);
    System.out.println(numbers);

    }


