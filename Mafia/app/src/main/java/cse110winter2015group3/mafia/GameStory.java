package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GameStory extends AppCompatActivity {

    private static TextView story;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_story);
        try {
            story = (TextView) findViewById(R.id.gameStory);
            String gameStory = readStoryFile();
            story.setText(gameStory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readStoryFile() throws IOException {
        InputStream inputStream;
        inputStream = getAssets().open("gameStory.txt");

        byte [] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(buffer);
        inputStream.close();
        outputStream.close();

        return outputStream.toString();

    }
}
