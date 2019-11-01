package com.example.tugasbesar2p3b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    protected Canvas mCanvas;
    protected ImageView ivCanvas;
    private GestureDetector mDetector;

    protected Paint paint;
    private boolean canvasInitiated;
    private Bitmap mBitmap;
    private int hitam,bg;
    protected MyCustomGestureListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ivCanvas= (ImageView) findViewById(R.id.iv_canvas);

        this.ivCanvas.setOnTouchListener(this);

        this.listener= new MyCustomGestureListener(this);
        this.mDetector=new GestureDetector(this,this.listener);

        this.hitam=ResourcesCompat.getColor(getResources(),R.color.colorHitam,null);



        this.canvasInitiated=false;

    }

    public void initiateCanvas()
    {
        this.mBitmap=Bitmap.createBitmap(this.ivCanvas.getWidth(),this.ivCanvas.getHeight(),Bitmap.Config.ARGB_8888);
        this.ivCanvas.setImageBitmap(mBitmap);
        this.mCanvas=new Canvas(mBitmap);

        this.paint = new Paint();
        this.paint.setStrokeWidth(3);
        this.changeStrokeColor(this.hitam);
        this.paint.setStyle(Paint.Style.STROKE);
        this.resetCanvas();

    }
    public void drawTriangle(Canvas canvas, Paint paint, float x, float y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }
    public void resetCanvas()
    {
        this.mCanvas.drawColor(this.bg);
        this.ivCanvas.invalidate();
        this.changeStrokeColor(hitam);
        this.paint.setStrokeWidth(3);

    }

    private void changeStrokeColor(int color) {
        //change stroke color using parameter (color resource id)
        this.paint.setColor(color);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.canvasInitiated = true;
        this.initiateCanvas();
        this.resetCanvas();
        return this.mDetector.onTouchEvent(motionEvent);

    }
}
