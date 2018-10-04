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
    StripeSystem stripesystem,int orientation,int[] valuestrobe,
    int thickness,int location,int lifespan){
    super(stripesystem,orientation,thickness,location,valuestrobe);
    this.lifespan=lifespan;}
  
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
