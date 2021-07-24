package com.sundar.shopping.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.sundar.shopping.R
import com.sundar.shopping.classes.AppBaseActivity
import com.sundar.shopping.classes.Constants
import com.sundar.shopping.classes.HelperClass.getDeviceId
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppBaseActivity() {

    private val TAG = "SSTag"
    private val activity = this@SplashActivity
    private var animationPlayed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (savedInstanceState != null) {
            animationPlayed = savedInstanceState.getBoolean(Constants.ANIMATION_PLAYED)
        }

        init()
    }

    private fun init() {
        if (!animationPlayed) {
            val animation = AnimationUtils.loadAnimation(activity, R.anim.zoom_in)
            app_logo.startAnimation(animation)
            goToNextScreen(2000)
        }

        /*  Log.d(TAG, "sm_model: " + getPhoneModel())
          Log.d(TAG, "sm_model_id : " + getDeviceId())*/


    }

    private fun goToNextScreen(delay: Long) {
        val intent = Intent(activity, HomeScreenActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, delay)


    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean(Constants.ANIMATION_PLAYED, true)
        super.onSaveInstanceState(outState)
        //  super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}