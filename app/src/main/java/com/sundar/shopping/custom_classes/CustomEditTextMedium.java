package com.sundar.shopping.custom_classes;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.sundar.shopping.classes.Constants;


public class CustomEditTextMedium extends AppCompatEditText {

    public CustomEditTextMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
       // this.setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.appfontMedium));
    }


}
