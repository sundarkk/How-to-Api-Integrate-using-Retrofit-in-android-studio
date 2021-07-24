package com.sundar.shopping.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.appcompat.app.AppCompatActivity;


import com.sundar.shopping.activities.LoginActivity;

import org.aviran.cookiebar2.CookieBar;

public class AppBaseActivity extends AppCompatActivity {
    Context context = getBaseContext();
    private Activity activity;


    public void disableOrientation(){
        setRequestedOrientation(getResources().getConfiguration().orientation);
    }
    public void enableOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
    }
    @Override
    protected void attachBaseContext(Context ctx) {
        this.context = ctx;
        super.attachBaseContext(context);

    }

    @Override
    protected void onPause() {
        super.onPause();
        CookieBar.dismiss(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = com.sundar.shopping.classes.AppBaseActivity.this;

        if (HelperClass.getSessionExpired(activity).equals(Constants.SESSION_INACTIVE)) {
            startActivity(new Intent(activity, LoginActivity.class));
        }
    }
}
