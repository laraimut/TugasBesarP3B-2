package com.example.tugasbesar2p3b;

public class Pesawat {
    private double x,y,r,distx,disty,batas1,batas2;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }


    public Pesawat(int x, int y,int r, double distx,double disty,int batas1,int batas2){
        this.x=x;
        this.y=y;
        this.r=r;
        this.distx = distx;
        this.disty = disty;
        this.batas1 = batas1;
        this.batas2 = batas2;
    }

    public void updatePosisi(){
        this.x+=distx;
        this.y+=disty;
    }

    public boolean outOfArea(){
        return x-r<=0||y-r<=0||x+r>=batas1||y+r>=batas2;
    }
}