package com.sundar.shopping.custom_classes;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

import com.sundar.shopping.classes.Constants;

public class CustomTextViewBold extends AppCompatTextView {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public CustomTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setGravity(Gravity.FILL);
        if (this.getPaddingStart() == 0) {
            this.setPadding(this.getPaddingLeft() + 2, this.getPaddingTop() + 2, this.getPaddingRight() + 2, this.getPaddingBottom() + 2);
        } else {
            this.setPadding(this.getPaddingLeft() + 1, this.getPaddingTop() + 1, this.getPaddingRight() + 1, this.getPaddingBottom() + 1);
        }
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.appfontBold));
    }
}
