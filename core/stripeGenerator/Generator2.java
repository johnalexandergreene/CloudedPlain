package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

public class Generator2 implements StripeGenerator{

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
  boolean created=false;
  
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(random.nextDouble()<0.3){
      int orientation=random.nextInt(2);
      int[] valuestrobe=new int[]{1};
      int thickness=60;
      int heading=random.nextInt(2);
      int speed=1;
      Stripe s=new Stripe_Sweeper(stripesystem,orientation,thickness,valuestrobe,heading,speed);
      a.add(s);
      created=true;}
  return a;}
  
  /*
   * StripeSystem stripesystem,int orientation,int[] valuestrobe,
    int thickness,int location,int lifespan(non-Javadoc)
   * @see org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator#destroy()
   */
  
  public boolean destroy(){
    return false;}
  
}
