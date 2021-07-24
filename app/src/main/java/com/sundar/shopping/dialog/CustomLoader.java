package com.sundar.shopping.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;


import com.sundar.shopping.R;
import com.tuyenmonkey.mkloader.MKLoader;

public class CustomLoader extends Dialog {

//    Animation aniRotate;
    private CustomLoader d;
//    private ImageView loader;
    private MKLoader progress_bar;

    public CustomLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_loader);
//        loader = findViewById(R.id.loader);
//        progress_bar = findViewById(R.id.progress_bar);
//        aniRotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
//        loader.startAnimation(aniRotate);

        d = CustomLoader.this;

        d.setCancelable(false);
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
//        loader.clearAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        loader.clearAnimation();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
