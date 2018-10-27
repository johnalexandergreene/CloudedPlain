package org.fleen.cloudedPlain.videoCreationSystems.vcs0001_wandering_stripes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

/*
 * an orderly 4way system
 */
public class StripeGenerator0 implements StripeGenerator{

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
  
  static final int 
    STRIPECOUNT_LOW=4,
    STRIPECOUNT_HIGH=12;
  
  static final double 
    PROBABILITY_LOW=0.3,
    PROBABILITY_MID=0.05,
    PROBABILITY_HIGH=0.004;
  
  static final int[][] VALUESTROBE=new int[][]{
    {1,2},
    {1,1,2,2},
    {1,1,1,2,2,2},
    {1,2,3},
    {2,2,3,3,3}
  };
  
  static final int[] THICKNESS={
    30,
    60,60,
    90,90,
    120,120,
    150,150,
    180,
    300
  };
  
  static final int[] SPEED={
    1,1,
    2,2,
    3,
    4,
  };
  
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>();
    a.addAll(conditionallyCreateGround());
    a.addAll(conditionallyCreateWanderer());
    return a;}
  
  static final int 
    GROUND_H_THICKNESS=240,
    GROUND_V_THICKNESS=360;
  
  public List<Stripe> conditionallyCreateGround(){
    List<Stripe> a=new ArrayList<Stripe>();
    Stripe s;
    //h
    if(stripesystem.time%(GROUND_H_THICKNESS*2)==0){
      s=new Stripe_Sweeper(stripesystem,0,GROUND_H_THICKNESS,new int[]{1,1,2,2},0,1);
      a.add(s);
    }else if((stripesystem.time+GROUND_H_THICKNESS)%(GROUND_H_THICKNESS*2)==0){
      s=new Stripe_Sweeper(stripesystem,0,GROUND_H_THICKNESS,new int[]{2,2,1,1},0,1);
      a.add(s);}
    //v
    if(stripesystem.time%(GROUND_V_THICKNESS*2)==0){
      s=new Stripe_Sweeper(stripesystem,1,GROUND_V_THICKNESS,new int[]{3,3,4,4},0,1);
      a.add(s);
    }else if((stripesystem.time+GROUND_V_THICKNESS)%(GROUND_V_THICKNESS*2)==0){
      s=new Stripe_Sweeper(stripesystem,1,GROUND_V_THICKNESS,new int[]{4,4,3,3},0,1);
      a.add(s);}
    //
    return a;}
  
  public List<Stripe> conditionallyCreateWanderer(){
    Stripe s;
    List<Stripe> a=new ArrayList<Stripe>();
    int stripecount=stripesystem.stripes.size();
    double probability;
    if(stripecount<STRIPECOUNT_LOW){
      probability=PROBABILITY_LOW;
    }else if(stripecount>STRIPECOUNT_HIGH){
      probability=PROBABILITY_HIGH;
    }else{//mid
      probability=PROBABILITY_MID;}
    if(random.nextDouble()<probability){
      int 
        orientation=random.nextInt(2),
        heading=random.nextInt(2),
        thickness=THICKNESS[random.nextInt(THICKNESS.length)],
        speed=SPEED[random.nextInt(SPEED.length)];
      int[] valuestrobe=VALUESTROBE[random.nextInt(VALUESTROBE.length)];
      s=new Stripe_Sweeper(stripesystem,orientation,thickness,valuestrobe,heading,speed);
      a.add(s);}
  return a;}
  
  public boolean destroy(){
    return false;}
  
}
