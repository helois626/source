package com.levadom.net.rktest;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;


public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

	// Initialize the Parse SDK.
//	Parse.initialize(this, "VQkvNzvIh49DyyGWoqwtbu6CKl2zCFoFe1aArvRR", "Nehkfjdgzhp7rAM75oXmFyNcOsHq9wvhsOtvz17m"); 
//	Parse.initialize(this, "n9WQLOSnu5AxhW8rFqoVXTFGf9nMUtY6L6ZHo1Vb", "4PXR9IH4AOL0suYUdC8na3uoClW1ucWxg1HvaTn5");

    Parse.initialize(this, "VQkvNzvIh49DyyGWoqwtbu6CKl2zCFoFe1aArvRR", "Nehkfjdgzhp7rAM75oXmFyNcOsHq9wvhsOtvz17m");
    ParseInstallation.getCurrentInstallation().saveInBackground();
	// Specify an Activity to handle all pushes by default.
	PushService.setDefaultPushCallback(this, MainActivity.class);
	
  }
  
}