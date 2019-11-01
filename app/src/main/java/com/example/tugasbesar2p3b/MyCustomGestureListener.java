package com.example.tugasbesar2p3b;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MyCustomGestureListener extends GestureDetector.SimpleOnGestureListener{

    protected MainActivity activity;
    protected float x1, y1, x2, y2;

    public MyCustomGestureListener(MainActivity a){
        this.activity = a;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //new start point with position if null, else set start position
        Log.d("debug", "onDown: ");
        this.x1=e.getX();
        this.y1=e.getY();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //set path
        float x = this.x1;
        float y = this.y1;
        this.x2=this.x1-distanceX;
        this.y2=this.y1-distanceY;
        //change start point for next path
        this.x1=this.x2;
        this.y1=this.y2;

        this.activity.drawTriangle(this.activity.mCanvas,this.activity.paint,x,
                y,100);


        //redraw
        this.activity.ivCanvas.invalidate();

        return true;
    }



}
