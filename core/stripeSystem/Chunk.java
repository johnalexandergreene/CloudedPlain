package org.fleen.cloudedPlain.core.stripeSystem;

import java.util.List;

/*
 * The product of an intersection between 2 rectangles
 * an intersection between 2 rectangles can produce 0,1, 2 or 3 rectangles
 * thse intersection-born rectangles are called chunks
 * we perform 2 kinds of intersections 
 * stripe-plain and stripe-chunk.
 */
public class Chunk implements SSRectangle{
  
  Chunk(int x,int y,int w,int h){
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
  
  public List<Stripe> coveringstripes;

  public int getXMin(){
    return getCoorX();}

  public int getXMax(){
    return getCoorX()+getWidth()-1;}

  public int getYMin(){
    return getCoorY();}

  public int getYMax(){
    return getCoorY()+getHeight()-1;}
  
  
  

}
