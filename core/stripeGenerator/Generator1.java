package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Loiterer;

public class Generator1 implements StripeGenerator{

  /*
   * ################################
   * STRIPE SYSTEM
   * ################################
   */
  
  StripeSystem stripesystem;
  
  public void setStripeSystem(StripeSystem stripesystem){
    this.stripesystem=stripesystem;}
  
  static final double PROBABILITY=0.12;
  
  Random random=new Random();
  
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(random.nextDouble()<PROBABILITY){
      int orientation=random.nextInt(2);
      
      int[] valuestrobe;
      int z=random.nextInt(3);
      if(z==0)
        valuestrobe=new int[]{1,1,1,2};
      else if(z==1)
        valuestrobe=new int[]{1,2,3,4,5};
      else
        valuestrobe=new int[]{2,4};
      
      int thickness=(random.nextInt(8)*8)+16;
      int location;
      if(orientation==Stripe.ORIENTATION_HORIZONTAL){
        location=random.nextInt(stripesystem.stage.getHeight());
      }else{
        location=random.nextInt(stripesystem.stage.getWidth());}
      int lifespan=random.nextInt(8)*8+16;
      Stripe_Loiterer s=new Stripe_Loiterer(stripesystem,orientation,thickness,valuestrobe,location,lifespan);
      a.add(s);}
  return a;}
  
  /*
   * StripeSystem stripesystem,int orientation,int[] valuestrobe,
    int thickness,int location,int lifespan(non-Javadoc)
   * @see org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator#destroy()
   */
  
  public boolean destroy(){
    return false;}
  
}
