package org.fleen.cloudedPlain.core.stripeSystem;

public abstract class Stripe implements SSRectangle{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public Stripe(StripeSystem stripesystem){
    this.stripesystem=stripesystem;
    initBirthday();}
  
  /*
   * ################################
   * STRIPE SYSTEM
   * The system of which this stripe is a part
   * ################################
   */
  
  public StripeSystem stripesystem;
  
  public void setPlain(StripeSystem s){
    stripesystem=s;}
  
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
    birthday=stripesystem.frameindex;}
  
  public int getAge(){
    return stripesystem.frameindex-birthday;}
  
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
