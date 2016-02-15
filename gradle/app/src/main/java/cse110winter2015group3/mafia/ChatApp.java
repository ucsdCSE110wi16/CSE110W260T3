package cse110winter2015group3.mafia;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Menu;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by luc on 2/4/2016.
 */
public class ChatApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models here
        ParseObject.registerSubclass(Message.class);
        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KfR8elGOPN2VxjBRM3ZVU6j1gEogT8ha2pxnGwvX")
                .clientKey("aiRG73gP64LSNyamzab9MGGtaezpIZBCKCdkmvAM")
                .server("https://myappname.herokuapp.com")
                .build());

    }
}
