package com.patelheggere.hamsa.executive.services;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.patelheggere.hamsa.executive.HamsaApplication;
import com.patelheggere.hamsa.executive.utils.SharedPrefsHelper;

import static com.patelheggere.hamsa.executive.utils.AppUtils.Constants.EMAIL;
import static com.patelheggere.hamsa.executive.utils.AppUtils.Constants.FCM_TOKEN;
import static com.patelheggere.hamsa.executive.utils.AppUtils.Constants.NAME;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseInstanceIDSer";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        /*// Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);*/
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        SharedPrefsHelper.getInstance().save(FCM_TOKEN, token);
        if(SharedPrefsHelper.getInstance().get(NAME, null)!=null)
        {
           /* DatabaseReference databaseReference;
            databaseReference = HamsaApplication.getFireBaseRef();
            UserDetails details = new UserDetails();
            details.setName(SharedPrefsHelper.getInstance().get(NAME, "null"));
            details.setEmail(SharedPrefsHelper.getInstance().get(EMAIL, "null"));
            details.setMobile(SharedPrefsHelper.getInstance().get(MOBILE, "null"));
            details.setvillage(SharedPrefsHelper.getInstance().get(VILLAGE, "null"));
            details.setToken(token);
            databaseReference.child(SharedPrefsHelper.getInstance().get(MOBILE, "null")).setValue(details);*/
        }
    }
}
