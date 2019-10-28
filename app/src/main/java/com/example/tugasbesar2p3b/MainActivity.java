package com.example.tugasbesar2p3b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected ImageView ivCanvas;
    protected Bitmap mBitmap;
    protected Button btnKanan;
    protected Canvas mCanvas;
    protected TextView tv;
    protected boolean start;
    protected Paint paint;
    protected ThreadPesawat thread;
    protected UIThreadedWrapper uiWrapper;
    protected Pesawat pesawat;
    private double x1,y1,x2,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.x1=0.0;
        this.y1=0.0;
        setContentView(R.layout.activity_main);
        this.ivCanvas=findViewById(R.id.ivCanvas);
        this.btnKanan=findViewById(R.id.btn_kanan);

        this.tv=findViewById(R.id.tv_out);
        this.btnKanan.setOnClickListener(this);

        this.uiWrapper = new UIThreadedWrapper(this);
        ivCanvas = this.findViewById(R.id.ivCanvas);
        paint = new Paint();
        int mColorTest= ResourcesCompat.getColor(getResources(),R.color.colorAccent,null);
        paint.setColor(mColorTest);
        start= false;
    }



    public void setTextViewOut(String parameter){
        this.tv.setText(parameter);
    }
    @Override
    public void onClick(View view) {

        if(view.getId()==this.btnKanan.getId())
        {
            int x =  ivCanvas.getWidth()/4;
            int y = ivCanvas.getHeight()/2;
            if(!start){
                start = true;
                mBitmap = Bitmap.createBitmap(ivCanvas.getWidth(),ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
                this.ivCanvas.setImageBitmap(mBitmap);
                this.mCanvas = new Canvas(mBitmap);
                this.mCanvas.drawColor(Color.WHITE);
                Random r = new Random();
                 x1 +=1;
                y1+=0;


                Paint paint = new Paint();
                paint.setColor(Color.RED);
                mCanvas.drawCircle(x,y,80,paint);
                pesawat = new Pesawat(x,y,80,x1,y1,ivCanvas.getWidth(),ivCanvas.getHeight());
                thread = new ThreadPesawat(uiWrapper);
                thread.start();
                if(pesawat.outOfArea()){
                    start= false;
                }
            }

        }

    }

    public void drawCircle(){
        Paint paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        mCanvas.drawCircle((float)pesawat.getX(), (float)pesawat.getY(), 80, paint1);
        pesawat.updatePosisi();
//        Random r = new Random();
//        int x = r.nextInt(10);

        int mColorTest=ResourcesCompat.getColor(getResources(),R.color.colorAccent,null);
        paint1.setColor(mColorTest);

        mCanvas.drawCircle((float)pesawat.getX(), (float)pesawat.getY(), 80, paint1);
        this.ivCanvas.invalidate();
        if(pesawat.outOfArea()){
            start= false;
        }
    }

}

