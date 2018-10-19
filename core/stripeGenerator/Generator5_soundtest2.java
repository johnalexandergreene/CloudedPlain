package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

/*
 * an orderly 4way system
 */
public class Generator5_soundtest2 implements StripeGenerator{

  /*
   * ################################
   * STRIPE SYSTEM
   * ################################
   */
  
  StripeSystem stripesystem;
  
  public void setStripeSystem(StripeSystem stripesystem){
    this.stripesystem=stripesystem;}
  
  Random random=new Random();
  
  public List<Stripe> generate(){
    Stripe s;
    List<Stripe> a=new ArrayList<Stripe>();
    double probability=0.05;
    if(stripesystem.stripes.size()<2){
      probability=0.5;
    }else if(stripesystem.stripes.size()>5)
      probability=0.003;
    
    if(random.nextDouble()<probability){
      int 
        ori=random.nextInt(2),
        hea=random.nextInt(2),
        thickness=random.nextInt(600)+30,
        speed=random.nextInt(2)+1;
      int[] vs;
      int b=random.nextInt(3);
      if(b==0){
        vs=new int[]{1,2};
      }else if(b==1){
        vs=new int[]{2,2,1};
      }else{
        vs=new int[]{1,1,3,3};}
      s=new Stripe_Sweeper(stripesystem,ori,thickness,vs,hea,speed);
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
