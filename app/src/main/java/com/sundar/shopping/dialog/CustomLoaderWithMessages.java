package com.sundar.shopping.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sundar.shopping.R;
import com.tuyenmonkey.mkloader.MKLoader;

public class CustomLoaderWithMessages extends Dialog {

    //    Animation aniRotate;
    private CustomLoaderWithMessages d;
    //    private ImageView loader;
    private MKLoader progress_bar;

    private TextView message;

    public CustomLoaderWithMessages(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_loader_with_messages);
        message = findViewById(R.id.message);

        d = CustomLoaderWithMessages.this;

        d.setCancelable(false);
    }

    public void setMessage(String m) {
        message.setText(m);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}

