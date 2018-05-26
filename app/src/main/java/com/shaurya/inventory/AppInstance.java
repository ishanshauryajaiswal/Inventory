package com.shaurya.inventory;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by ishan.jaiswal on 5/24/2018.
 */

public class AppInstance extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
