package org.fleen.cloudedPlain.videoCreationSystems.vcs0004_wanderer;

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
  
  static final int[][] VALUESTROBE=new int[][]{
    {1,2},
    {3,5},
    {6,9},
    {1,1,2,2},
    {4,4,6,6},
    {8,8,9,9},
    {1,1,1,2,2,2},
    {5,5,5,8,8,8},
    {1,2,3},
    {6,8,10},
    {2,2,3,3,3},
    {6,6,3,3,3,9,9,9},
  };
  
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>();
    a.addAll(conditionallyCreateGround());
    a.addAll(conditionallyCreateWanderer());
    return a;}
  
  /*
   * ################################
   * CREATE GROUNDERS
   * ################################
   */
  
  static final int[] GROUNDER_THICKNESS={
    512,
    768,
    1024};
  
  //the most recently created background stripe
  Stripe_Sweeper hp=null,hn=null,vp=null,vn=null;
  
  /*
   * the ground covers the stage completely
   * for each orientation and heading (h+, h-, v+, v-)
   *   when the prior ground stripe arrives at fully-within-stage (ex : for h+ that when location==0) create a new stripe
   */
  public List<Stripe> conditionallyCreateGround(){
    List<Stripe> a=new ArrayList<Stripe>();
    //h+
    if(hp==null||hp.isAtVisibleInit()){
      hp=createPartiallyConstrainedSweeper(Stripe.ORIENTATION_HORIZONTAL,Stripe_Sweeper.HEADING_POSITIVE);
      a.add(hp);}
    //h-
    if(hn==null||hn.isAtVisibleInit()){
      hn=createPartiallyConstrainedSweeper(Stripe.ORIENTATION_HORIZONTAL,Stripe_Sweeper.HEADING_NEGATIVE);
      a.add(hn);}
    //v+
    if(vp==null||vp.isAtVisibleInit()){
      vp=createPartiallyConstrainedSweeper(Stripe.ORIENTATION_VERTICAL,Stripe_Sweeper.HEADING_POSITIVE);
      a.add(vp);}
    //v-
    if(vn==null||vn.isAtVisibleInit()){
      vn=createPartiallyConstrainedSweeper(Stripe.ORIENTATION_VERTICAL,Stripe_Sweeper.HEADING_NEGATIVE);
      a.add(vn);}
    //
    return a;}
  
  private Stripe_Sweeper createPartiallyConstrainedSweeper(int orientation,int heading){
    int thickness=GROUNDER_THICKNESS[random.nextInt(GROUNDER_THICKNESS.length)];
    int[] valuestrobe=VALUESTROBE[random.nextInt(VALUESTROBE.length)];
    Stripe_Sweeper s=new Stripe_Sweeper(stripesystem,orientation,thickness,valuestrobe,heading,1);
    return s;}
  
  /*
   * ################################
   * CREATE WANDERERS
   * ################################
   */
  
  static double PROBABILITY=0.005;
  
  static final int[] WANDER_THICKNESS={
    16,
    32,
    64,
    96,
    128,
    160,
    192,
    224,
    256};
  
  static final int[] WANDERER_SPEED={
    2,
    3,
    4};
  
  public List<Stripe> conditionallyCreateWanderer(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(random.nextDouble()<PROBABILITY){
      a.add(createRandomSweeper());}
  return a;}
  
  private Stripe createRandomSweeper(){
    int 
      orientation=random.nextInt(2),
      heading=random.nextInt(2),
      thickness=WANDER_THICKNESS[random.nextInt(WANDER_THICKNESS.length)],
      speed=WANDERER_SPEED[random.nextInt(WANDERER_SPEED.length)];
    int[] valuestrobe=VALUESTROBE[random.nextInt(VALUESTROBE.length)];
    Stripe s=new Stripe_Sweeper(stripesystem,orientation,thickness,valuestrobe,heading,speed);
    return s;}
  
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  public boolean destroy(){
    return false;}
  
}
