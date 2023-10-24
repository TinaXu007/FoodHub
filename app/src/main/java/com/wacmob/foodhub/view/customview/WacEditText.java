package com.wacmob.foodhub.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.wacmob.foodhub.R;

/**
 * Created by KP on 5/10/2018.
 */

public class WacEditText extends androidx.appcompat.widget.AppCompatEditText {
    public WacEditText(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public WacEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.WacEditText);
        String fontThickness = values.getString(R.styleable.WacEditText_editFontThickness);
        if (fontThickness == null) fontThickness = "normal";
        values.recycle();
        switch(fontThickness){
            case "bold":
                setBold(context);
                break;

            case "thin":
                setThin(context);
                break;

            default:
                applyCustomFont(context);
        }
    }

    public WacEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.WacEditText);
        String fontThickness = values.getString(R.styleable.WacEditText_editFontThickness);
        if (fontThickness == null) fontThickness = "normal";
        values.recycle();
        switch(fontThickness){
            case "bold":
                setBold(context);
                break;

            case "thin":
                setThin(context);
                break;

            default:
                applyCustomFont(context);
        }
    }

    private void applyCustomFont(Context context) {
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/roboto-regular.ttf");
        this.setTypeface(face);
    }

    public void setBold(Context context) {
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/roboto-medium.ttf");
        this.setTypeface(face);
    }

    public void setThin(Context context) {
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/roboto-thin.ttf");
        this.setTypeface(face);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
