package cse110winter2015group3.mafia;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Stan on 2/14/2016.
 */
public class ParseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "f8frQdQmzFtvuyBqRd6H9OixCbu5QdD8ZOJtLV5e",
                "ru3eERc3549M1TQdmb6XgSbRVBeLBGxVMTiSWJyA");

        ParseUser.enableAutomaticUser();
        ParseACL defauAcl = new ParseACL();

        defauAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defauAcl, true);
    }
}
