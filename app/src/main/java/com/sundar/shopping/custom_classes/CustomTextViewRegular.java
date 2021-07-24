package com.sundar.shopping.custom_classes;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.sundar.shopping.classes.Constants;


public class CustomTextViewRegular extends AppCompatTextView {

    public CustomTextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (this.getWidth() == ViewGroup.LayoutParams.WRAP_CONTENT) {
            this.setGravity(Gravity.FILL);
        }
        this.setPadding(this.getPaddingLeft() + 1, this.getPaddingTop() + 1, this.getPaddingRight() + 1, this.getPaddingBottom() + 1);

        this.setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.appfontRegular));
    }
}
