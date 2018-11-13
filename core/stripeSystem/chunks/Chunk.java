package org.fleen.cloudedPlain.core.stripeSystem.chunks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fleen.cloudedPlain.core.stripeSystem.SSRectangle;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.geom_2D.GD;

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
    return x;}

  public int getXMax(){
    return x+w-1;}

  public int getYMin(){
    return y;}

  public int getYMax(){
    return y+h-1;}
  
  public int getArea(){
    return getWidth()*getHeight();}
  
  public double[] getCenter(){
    double[] c={x+w/2,y+h/2};
    return c;}
  
  /*
   * get distance from point to edge of chunk
   * if the point is inside the chunk then return 0
   */
  public double getDistance(double px,double py){
    if(px>getXMin()&&px<getXMax()&&py>getYMin()&&py<getYMax())return 0;
    double 
      x0=x,y0=y,
      x1=x+w,y1=y,
      x2=x+w,y2=y+h,
      x3=x,y3=y+h;
    double 
      d0=GD.getDistance_PointLine(px,py,x0,y0,x1,y1),
      d1=GD.getDistance_PointLine(px,py,x1,y1,x2,y2),
      d2=GD.getDistance_PointLine(px,py,x2,y2,x3,y3),
      d3=GD.getDistance_PointLine(px,py,x3,y3,x0,y0);
    
//    System.out.println("--------------------------");
//    System.out.println("x="+x);
//    System.out.println("y="+y);
//    System.out.println("w="+w);
//    System.out.println("h="+h);
//    System.out.println("d0="+d0);
//    System.out.println("d1="+d1);
//    System.out.println("d2="+d2);
//    System.out.println("d3="+d3);
//    System.out.println("--------------------------");
    
    double d=d0;
    if(d1<d)d=d1;
    if(d2<d)d=d2;
    if(d3<d)d=d3;
    return d;}
  
  /*
   * ################################
   * COVERING STRIPES
   * the stripes that intersected to produces this chunk
   * ################################
   */
  
  public List<Stripe> stripes;
  
  void setStripes(Collection<Stripe> s){
    stripes=new ArrayList<Stripe>(s);}
  
  void setStripes(Collection<Stripe> s0,Collection<Stripe> s1){
    stripes=new ArrayList<Stripe>(s0.size()+s1.size());
    stripes.addAll(s0);
    stripes.addAll(s1);}
  
}
