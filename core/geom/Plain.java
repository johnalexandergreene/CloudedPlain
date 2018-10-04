package org.fleen.cloudedPlain.core.geom;

public class Plain implements CPRectangle{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public Plain(int w,int h){
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

}
