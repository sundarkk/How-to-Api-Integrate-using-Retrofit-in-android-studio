package com.sundar.shopping.custom_classes;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.sundar.shopping.classes.Constants;


public class CustomButton extends AppCompatButton {
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.appfontRegular));
    }
}
