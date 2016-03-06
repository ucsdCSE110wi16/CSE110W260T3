package cse110winter2015group3.mafia;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by aneeshnatarajan on 3/5/16.
 */
public class GameStory extends Activity {
    private static TextView story;
    static final int READ_BLOCK_SIZE = 100;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_story);
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
