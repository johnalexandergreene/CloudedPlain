package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0009_backtosquare_rotatingpalette_experiments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeProjector;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

/*
 * an orderly 4way system
 */
public class Projector0 implements StripeProjector{

  /*
   * ################################
   * STRIPE SYSTEM
   * ################################
   */
  
  StripeSystem stripesystem;
  
  public void setStripeSystem(StripeSystem stripesystem){
    this.stripesystem=stripesystem;}
  
  /*
   * ################################
   * GENERATE
   * ################################
   */
  
  Random random=new Random();
 
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>(),b;
    b=conditionallyCreateWanderingBox();
    if(b!=null)a.addAll(b);
    return a;}
  
  /*
   * ################################
   * WANDERING BOX
   * ################################
   */
  
  static final double WANDERINGBOXPROBABILITY=0.02;
  
  private List<Stripe> conditionallyCreateWanderingBox(){
    if(random.nextDouble()<WANDERINGBOXPROBABILITY)
      return createWanderingBox();
    return null;}
  
  private List<Stripe> createWanderingBox(){
    int[] vs={stripesystem.time%8,stripesystem.time%16,stripesystem.time%24};
    int t=stripesystem.time%256;
    int s=stripesystem.time%3;
    List<Stripe> a=new ArrayList<Stripe>();
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_HORIZONTAL,
      t,
      vs,
      Stripe_Sweeper.HEADING_POSITIVE,
      s));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_HORIZONTAL,
      t,
      vs,
      Stripe_Sweeper.HEADING_NEGATIVE,
      s));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_VERTICAL,
      t,
      vs,
      Stripe_Sweeper.HEADING_POSITIVE,
      s));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_VERTICAL,
      t,
      vs,
      Stripe_Sweeper.HEADING_NEGATIVE,
      s));
    
    
    return a;
  }
  
  
  
  
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  public boolean destroy(){
    return false;}
  
}
