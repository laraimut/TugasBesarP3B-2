package com.example.tugasbesar2p3b;

import android.util.Log;

public class ThreadPesawat implements Runnable{
    protected Thread thread;
    protected UIThreadedWrapper uiWrapper;

    public ThreadPesawat(UIThreadedWrapper uiWrapper){
        this.uiWrapper=uiWrapper;
        this.thread = new Thread(this);
    }

    public void start(){
        this.thread.start();
    }
    @Override
    public void run() {

        while(uiWrapper.mainActivity.start==true){

            uiWrapper.setTextViewOut("test");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            uiWrapper.drawCircle();

        }
    }
}