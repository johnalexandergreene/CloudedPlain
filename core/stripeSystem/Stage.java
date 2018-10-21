package org.fleen.cloudedPlain.core.stripeSystem;

/*
 * the rectangle viewport where stripes dance
 */
public class Stage implements SSRectangle{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public Stage(int w,int h){
    width=w;
    height=h;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  int width,height;
  
  public int getCoorX(){
    return 0;}
  
  public int getCoorY(){
    return 0;}

  public int getWidth(){
    return width;}

  public int getHeight(){
    return height;}

  public int getXMin(){
    return 0;}

  public int getXMax(){
    return getWidth()-1;}

  public int getYMin(){
    return 0;}

  public int getYMax(){
    return getHeight()-1;}
  
  public int getArea(){
    return getHeight()*getWidth();}

}
