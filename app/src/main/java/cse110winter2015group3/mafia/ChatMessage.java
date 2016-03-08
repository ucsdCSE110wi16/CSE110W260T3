package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/27/2016.
 */
public class ChatMessage {

    private String name;
    private String text;

    public ChatMessage() {
        // needed for Firebase's deserializer
    }

    public ChatMessage (String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
