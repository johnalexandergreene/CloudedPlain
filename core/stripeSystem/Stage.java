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
  
  public Stage(int span){
    this.span=span;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  int span;
  
  public int getCoorX(){
    return 0;}
  
  public int getCoorY(){
    return 0;}

  public int getWidth(){
    return span;}

  public int getHeight(){
    return span;}

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
  
  public double[] getCenter(){
    double[] c={(getXMax()-getXMin())/2,(getYMax()-getYMin())/2};
    return c;}
  
  /*
   * ie, the distance from the center to a corner
   */
  private double halfdiagonal=-1;
  
  public double getMaxDistanceFromCenter(){
    if(halfdiagonal==-1){
      double 
        w=getWidth(),
        h=getHeight();
      halfdiagonal=Math.sqrt((w*w)+(h*h))/2;}
    return halfdiagonal;}
  
  //
  
  private double fatness=-1;
  
  public double getFatness(){
    if(fatness==-1)
      fatness=Math.max(getWidth(),getHeight())*getArea();
    return fatness;}
  
  

}
