package org.fleen.cloudedPlain.core.stripeSystem;

/*
 * A stripe that sweeps the surface of the plain once, from an edge to the opposite edge
 * it is created, sweeps the plain, then destroyed
 * 
 * at init the new stripe is created off stage
 * its location changes according to time, traversing age*speed
 * when the stripe is off stage it stops moving and signals for destruction 
 */

public class Stripe_Sweeper extends Stripe{
  
  public Stripe_Sweeper(
    StripeSystem stripesystem,int orientation,
    int thickness,int[] valuestrobe,
    int heading,int speed){
    super(stripesystem,orientation,thickness,valuestrobe);
    this.heading=heading;
    this.speed=speed;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  /*
   * a horizontal stripe 
   *   with positive heading starts at the south edge and moves north
   *   with negative heading starts at the north edge and moves south
   * a vertical stripe
   *   with positive heading starts at the west edge and moves east
   *   with negative heading starts at the east edge and moves west
   */
  public static final int
    HEADING_POSITIVE=0,
    HEADING_NEGATIVE=1;
  
  public int speed,heading;
  
  public int getLocation(){
    int location;
    if(orientation==ORIENTATION_HORIZONTAL){
      if(heading==HEADING_POSITIVE){//northerly
        location=getAge()*speed-thickness;
      }else{//HEADING_NEGATIVE//southerly
        location=stripesystem.stage.getHeight()-getAge()*speed;}
    }else{//ORIENTATION_VERTICAL
      if(heading==HEADING_POSITIVE){//easterly
        location=getAge()*speed-thickness;
      }else{//HEADING_NEGATIVE//westerly
        location=stripesystem.stage.getWidth()-getAge()*speed;}}
    return location;}
  
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  /*
   * when the stripe has moved entirely off the stage
   */
  public boolean destroyMe(){
    boolean destroyme=false;
    if(orientation==ORIENTATION_HORIZONTAL){
      if(heading==HEADING_POSITIVE){//northerly
        destroyme=getLocation()>stripesystem.stage.getHeight()+thickness;
      }else{//HEADING_NEGATIVE//southerly
        destroyme=getLocation()<-thickness;}
    }else{//ORIENTATION_VERTICAL
      if(heading==HEADING_POSITIVE){//easterly
        destroyme=getLocation()>stripesystem.stage.getWidth();
      }else{//HEADING_NEGATIVE//westerly
        destroyme=getLocation()<-thickness;}}
    return destroyme;}

}
