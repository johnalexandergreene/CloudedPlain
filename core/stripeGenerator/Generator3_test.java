package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

public class Generator3_test implements StripeGenerator{

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

//  public List<Stripe> generate(){
//    List<Stripe> a=new ArrayList<Stripe>();
//    if(!created){
//      Stripe s=new Stripe_Loiterer(stripesystem,1,100,new int[]{1},100,100000);
//      a.add(s);
//      Stripe s0=new Stripe_Loiterer(stripesystem,1,100,new int[]{1},300,1000000);
//      a.add(s0);
//      created=true;}
//  return a;}
  
//  public List<Stripe> generate(){
//    List<Stripe> a=new ArrayList<Stripe>();
//    if(!created){
//      int[] valuestrobe=new int[]{1,7};
//      Stripe s=new Stripe_Sweeper(stripesystem,0,120,valuestrobe,0,1);
//      a.add(s);
//      Stripe s0=new Stripe_Sweeper(stripesystem,0,120,valuestrobe,1,1);
//      a.add(s0);
//      created=true;}
//  return a;}
  
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(!created){
      int[] valuestrobe=new int[]{1,2,3,2};
      Stripe s=new Stripe_Sweeper(stripesystem,0,120,valuestrobe,0,1);
      a.add(s);
      s=new Stripe_Sweeper(stripesystem,0,120,valuestrobe,1,1);
      a.add(s);
      s=new Stripe_Sweeper(stripesystem,1,120,valuestrobe,0,1);
      a.add(s);
      s=new Stripe_Sweeper(stripesystem,1,120,valuestrobe,1,1);
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
