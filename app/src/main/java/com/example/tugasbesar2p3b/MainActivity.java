package com.example.tugasbesar2p3b;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    protected GameView mGameView;
    private float mXTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Membuat tampilan menjadi full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Membuat tampilan selalu menyala jika activity aktif
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Mendapatkan ukuran layar
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);


        mGameView = new GameView(this, point.x, point.y);
        setContentView(mGameView);



        //Sensor Accelerometer digunakan untuk menggerakan player ke kanan dan ke kiri
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameView.pause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mXTemp = event.values[0];
        if (event.values[0] > 1){
            mGameView.steerLeft(event.values[0]);
        }
        else if (event.values[0] < -1){
            mGameView.steerRight(event.values[0]);
        }else{
            mGameView.stay();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();


        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                if(x <= 500){
                    mGameView.steerLeft(5);

//                    Log.d("kiri", "kiri");
                }
                else{
                    mGameView.steerRight(5);
//                    Log.d("kanan", "kanan");
                }
                break;
        }
        return true;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
