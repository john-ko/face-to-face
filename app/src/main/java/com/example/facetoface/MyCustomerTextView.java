package com.example.facetoface;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Hu on 11/17/2016.
 */

public class MyCustomerTextView extends TextView {
    public MyCustomerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Kaushan.otf"));
    }
}