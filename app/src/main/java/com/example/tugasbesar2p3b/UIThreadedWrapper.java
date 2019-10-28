package com.example.tugasbesar2p3b;

import android.os.Handler;
import android.os.Message;

public class UIThreadedWrapper extends Handler {
    protected final static int MSG_SET_TV_OUTPUT=0;
    protected final static int DRW_CIRCLE=1;
    protected MainActivity mainActivity;

    public UIThreadedWrapper(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg){
        if(msg.what==UIThreadedWrapper.MSG_SET_TV_OUTPUT){
            String parameter =(String)msg.obj;
            this.mainActivity.setTextViewOut(parameter);
        }
        else{
            this.mainActivity.drawCircle();
        }
    }
    public void setTextViewOut(String output){
        Message msg = new Message();
        msg.what = MSG_SET_TV_OUTPUT;
        msg.obj = output;
        this.sendMessage(msg);
    }

    public void drawCircle(){
        Message msg = new Message();
        msg.what =  DRW_CIRCLE;
        this.sendMessage(msg);
    }

}
