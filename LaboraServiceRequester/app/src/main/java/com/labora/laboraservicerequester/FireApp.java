import com.firebase.client.Firebase;
import android.app.Application;
package com.labora.laboraservicerequester;


public class FireApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
