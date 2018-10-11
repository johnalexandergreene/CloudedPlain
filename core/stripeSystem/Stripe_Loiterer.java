package org.fleen.cloudedPlain.core.stripeSystem;

/*
 * stays where its put till the timer runs out
 * TEST
 */
public class Stripe_Loiterer extends Stripe{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public Stripe_Loiterer(
    StripeSystem stripesystem,int orientation,int thickness,int[] valuestrobe,int location,int lifespan){
    super(stripesystem,orientation,thickness,valuestrobe);
    this.location=location;
    this.lifespan=lifespan;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  int location;
  
  public int getLocation(){
    return location;}
  
  /*
   * ################################
   * LIFESPAN
   * ################################
   */
  
  public int lifespan;

  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  public boolean destroyMe(){
    return getAge()>lifespan;}

}
