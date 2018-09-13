package org.fleen.cloudedPlain.core;

import java.util.ArrayList;
import java.util.List;

public abstract class CPStripe implements CPRectangle{
  
  /*
   * ################################
   * TYPE
   * A stripe is either horizontal or vertical
   * ################################
   */
  
  public static final int 
    ORIENTATION_HORIZONTAL=0,
    ORIENTATION_VERTICAL=1;
  
  public abstract int getOrientation();
  
  public boolean isHorizontal(){
    return getOrientation()==ORIENTATION_HORIZONTAL;}
  
  public boolean isVertical(){
    return getOrientation()==ORIENTATION_VERTICAL;}
  
  /*
   * ################################
   * SOME GEOMETRY
   * ################################
   */
  
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
   * when this stripe is done doing its dance 
   * it lets us know that it is time to destroy it
   * ################################
   */
  
  public abstract boolean destroyMe();

}
