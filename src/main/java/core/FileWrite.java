package core;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


// HOPEFULLY - takes user input of a file path and saves their score!
public class FileWrite {
    public static String main(String name, int count) {
        File txtFile = new File(name);

        try (FileWriter writePlace = new FileWriter(txtFile, true)) {
            writePlace.write("Your score: " + count + ". This was on: " + LocalDate.now());
            return ("Successfully saved.");
        } catch (IOException e) {
            return ("An error occurred with the file, or code.");
        }
    }
}

/*
Please be advised this method was not tested due to my superior, lazy mood. :)
 */
