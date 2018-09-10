package org.fleen.cloudedPlain.core.rectangles;

import java.util.ArrayList;
import java.util.List;

public class Intersection extends CPRectangle{
  
  Intersection(int x,int y,int w,int h){
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;}
  
  int x,y,w,h;

  public int getCoorX(){
    return x;}
  
  public int getCoorY(){
    return y;}

  public int getWidth(){
    return w;}

  public int getHeight(){
    return h;}
  
  public List<Stripe> parents=new ArrayList<Stripe>();
  
  

}
