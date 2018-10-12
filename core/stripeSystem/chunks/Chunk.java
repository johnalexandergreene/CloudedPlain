package org.fleen.cloudedPlain.core.stripeSystem.chunks;

import java.util.List;

import org.fleen.cloudedPlain.core.stripeSystem.SSRectangle;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;

/*
 * The product of an intersection between 2..n stripes
 */
public class Chunk implements SSRectangle{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  Chunk(int x,int y,int w,int h){
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;}
  
  /*
   * ################################
   * GEOM
   * ################################
   */
  
  int x,y,w,h;

  public int getCoorX(){
    return x;}
  
  public int getCoorY(){
    return y;}

  public int getWidth(){
    return w;}

  public int getHeight(){
    return h;}

  public int getXMin(){
    return getCoorX();}

  public int getXMax(){
    return getCoorX()+getWidth()-1;}

  public int getYMin(){
    return getCoorY();}

  public int getYMax(){
    return getCoorY()+getHeight()-1;}
  
  /*
   * ################################
   * COVERING STRIPES
   * the stripes that intersected to produces this chunk
   * ################################
   */
  
  public List<Stripe> stripes;
  
}
