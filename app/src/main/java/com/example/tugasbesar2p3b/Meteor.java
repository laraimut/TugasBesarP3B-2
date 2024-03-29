package com.example.tugasbesar2p3b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

import java.util.Random;




public class Meteor {

    private Bitmap mBitmap;
    private int mX;
    private int mY;
    private int mMaxX;
    private int mMinX;
    private int mMaxY;
    private int mMinY;

    private int mSpeed;
    private Rect mCollision;
    private int mScreenSizeX;
    private int mScreenSizeY;
    private int mHP;
    private SoundPlayer mSoundPlayer;
    private int SCORE=10;
    private int METEOR_DESTROYED=10;

    public Meteor(Context context, int screenSizeX, int screenSizeY, SoundPlayer soundPlayer){
        mScreenSizeX = screenSizeX;
        mScreenSizeY = screenSizeY;
        mSoundPlayer = soundPlayer;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * 3/5, mBitmap.getHeight() * 3/5, false);

        mMaxX = screenSizeX - mBitmap.getWidth();
        mMaxY = screenSizeY - mBitmap.getHeight();
        mMinX = 0;
        mMinY = 0;
        mHP = 1;

        Random random = new Random();
        mSpeed = random.nextInt(3) + 1;

        mX = random.nextInt(mMaxX);
        mY = 0 - mBitmap.getHeight();

        mCollision = new Rect(mX, mY, mX + mBitmap.getWidth(), mY + mBitmap.getHeight());
    }

    public void update(){
        mY += 7 * mSpeed;

        mCollision.left = mX;
        mCollision.top = mY;
        mCollision.right = mX + mBitmap.getWidth();
        mCollision.bottom = mY + mBitmap.getHeight();
    }

    public Rect getCollision() {
        return mCollision;
    }

    public void hit(){
        if (--mHP ==0){
            SCORE += 20;
            this.METEOR_DESTROYED++;
            destroy();
        }else{
            mSoundPlayer.playExplode();
        }
    }

    public void destroy(){
        mY = mScreenSizeY + 1;
        mSoundPlayer.playCrash();
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }
}
