package part1.logging;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class InDenialLogger implements Logger{
    String Happy = "We got an error! Oh that's perfect, you know, you never learn without making mistakes! We love this!";
    String Angry = "We got an error. Oh, don't worry, I took it out back. It shouldn't bother us anymore";
    String Flirty = "An error! Wow, you really know how to set the mood ;) What do you say we take this error and...fix the syntax? ;)";
    String Scared = "So, about that run time..there was an error. But it's ok!! I remember dealing with this growing up and if we just don't look at it then it can't see us..";
    String Therapist = "An error popped up. Now, I don't want you to dive straight into fixing this. Let's talk about it. How does that error make you feel? Angry? Sad? Worthless?";
    String Deny = "An error? What? No. I think you're blind actually. And stupid.";
    ArrayList<String> Responses = new ArrayList<String>(Arrays.asList(Happy, Angry, Flirty, Scared, Therapist, Deny));

    public void log(String message, LogLevel level){
        System.out.println(Instant.now().toString() + " [" + level + "] " + RandomResponse(Responses));
    }

    private String RandomResponse(ArrayList<String> Responses) {
        Collections.shuffle(Responses);
        return Responses.getFirst();
    }
    public String FormatMessage(String message, LogLevel level){
        String responseMsg = RandomResponse(Responses);
        return (Instant.now().toString() + "[" + level + "]" + responseMsg);
    }
    // this isn't entirely an in-denial logger but I hope the creative liberty I took with it is fine
}
