package org.fleen.cloudedPlain.core.geom;

import org.fleen.cloudedPlain.core.CloudedPlain;

public abstract class Stripe implements CPRectangle{
  
  public Stripe(CloudedPlain plain){
    this.plain=plain;
    initBirthday();}
  
  /*
   * ################################
   * PLAIN
   * The Plain that this stripe expresses itself upon
   * ################################
   */
  
  public CloudedPlain plain;
  
  public void setPlain(CloudedPlain plain){
    this.plain=plain;}
  
  /*
   * ################################
   * BIRTHDAY
   * the frame index when this object was created
   * from this we derive age
   * with speed we derive location
   * ################################
   */
  
  public int birthday;
  
  void initBirthday(){
    birthday=plain.frameindex;}
  
  public int getAge(){
    return plain.frameindex-birthday;}
  
  /*
   * ################################
   * ORIENTATION
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
