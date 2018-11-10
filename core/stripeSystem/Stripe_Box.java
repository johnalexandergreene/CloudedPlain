package org.fleen.cloudedPlain.core.stripeSystem;

/*
 * This sweeper is special in that we can specify the init location
 * (the default init value for location puts the stripe just offstage, respective to its orientation and heading.)
 * 
 * It's useful for squaresweeper wher we gotta make sure that the 4 stripes are mirrored, despite the dimensions of the stage. 
 */

public class Stripe_Box extends Stripe_Sweeper{
  
  public Stripe_Box(
    StripeSystem stripesystem,int orientation,
    int thickness,int[] valuestrobe,
    int heading,int speed,int initlocation){
    super(stripesystem,orientation,thickness,valuestrobe,heading,speed);
    this.initlocation=initlocation;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  int initlocation;
  
  public int getLocation(){
    int a;
    if(heading==HEADING_POSITIVE){
      a=initlocation+getAge()*speed;
    }else{
      a=initlocation-getAge()*speed;}
    return a;}
  
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  /*
   * when the stripe has crossed the logical deathline
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
