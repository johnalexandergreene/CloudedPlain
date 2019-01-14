package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0010_niceone;

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
    b=conditionallyCreateSeamlessBox();
    if(b!=null)a.addAll(b);
//    b=conditionallyCreatePeriodicBox();
//    if(b!=null)a.addAll(b);
    return a;}
  
  /*
   * ################################
   * PERIODIC BOX
   * ################################
   */
  
  static final double WANDERINGBOXPROBABILITY=0.02;
  
  private List<Stripe> conditionallyCreatePeriodicBox(){
    if(stripesystem.time%40==0)
      return createBox(8,new int[]{1,2},2);
    return null;}
  
  /*
   * ################################
   * SEAMLESS BOX
   * ################################
   */
  
  static final int[] SEAMLESSBOXTHICKNESS={64,96,128,160};
  
  int 
    priorseamlessboxbirthday=-1,
    priorseamlessboxthickness=-1;
  
  private List<Stripe> conditionallyCreateSeamlessBox(){
    if(priorseamlessboxbirthday==-1||stripesystem.time==(priorseamlessboxbirthday+priorseamlessboxthickness)){
      int thickness=SEAMLESSBOXTHICKNESS[random.nextInt(SEAMLESSBOXTHICKNESS.length)];
      int[] valuestrobe=VALUESTROBE[random.nextInt(VALUESTROBE.length)];
      priorseamlessboxbirthday=stripesystem.time;
      priorseamlessboxthickness=thickness;
      return createBox(thickness,valuestrobe,1); }
    return null;}
  
  /*
   * ################################
   * BOX
   * 4 sweeping stripes
   * ################################
   */
  
  int[] SPEED={1,2,3};
  
  int[][] VALUESTROBE={
    {1,1,2,2},
    {1,1,1,2,2,2},
    {1,1,1,1,2,2,3,3},
    {1,1,1,1,2,2},
    {1,1,2,2,3,3}};
  
  private List<Stripe> createBox(int thickness,int[] valuestrobe,int speed){
    List<Stripe> a=new ArrayList<Stripe>();
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_HORIZONTAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_POSITIVE,
      speed));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_HORIZONTAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_NEGATIVE,
      speed));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_VERTICAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_POSITIVE,
      speed));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_VERTICAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_NEGATIVE,
      speed));
    //
    return a;}
    
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  public boolean destroy(){
    return false;}
  
}
