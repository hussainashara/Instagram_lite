package com.example.sastu_insta.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class squareImageview extends AppCompatImageView {


    public squareImageview(Context context) {
        super(context);
    }

    public squareImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public squareImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
