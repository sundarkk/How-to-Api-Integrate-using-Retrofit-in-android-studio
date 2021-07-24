package com.sundar.shopping.custom_classes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;

import com.sundar.shopping.R;
import com.sundar.shopping.classes.Constants;

public class MyTv extends AppCompatTextView {
    private String tag = "MTTag";
//Todo new custome textview added
    public MyTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttr(context, attrs);
        if (this.getWidth() == ViewGroup.LayoutParams.WRAP_CONTENT) {
            this.setGravity(Gravity.FILL);
        }
//        this.setPadding(this.getPaddingLeft() + 1, this.getPaddingTop() + 1, this.getPaddingRight() + 1, this.getPaddingBottom() + 1);
//        this.setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.appfontRegular));
    }

    private void readAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        // Read the title and set it if any
        String fontName = a.getString(R.styleable.MyTextView_fontName);
        if (fontName != null && fontName.endsWith(".ttf")) {
            // We have a attribute value and set it to proper value as you want
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));
            Log.d(tag, fontName);
        } else {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.appfontRegular));
        }
        a.recycle();
    }
}
