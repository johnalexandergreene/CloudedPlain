package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0001_wanderer_justground_lushfatty;

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
public class StripeGenerator0 implements StripeProjector{

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
//    a.addAll(conditionallyCreateGround());
    a.addAll(conditionallyCreateWanderingBox());
//    a.addAll(conditionallyCreateWanderer());
    return a;}
  
  /*
   * ################################
   * CREATE GROUNDERS
   * ################################
   */
  
  static final int[] GROUNDER_THICKNESS={
    256,
    384,
    512};
  
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
  
  static final int[] WANDER_THICKNESS={
    16,
    32,
    64,64,
    96,96,96,
    128,128,128,
    160,160,
    192,
    224,
    256};
  
  static final int[] WANDERER_SPEED={
    1,
    2,
    3,
    4};
  
  public List<Stripe> conditionallyCreateWanderer(){
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
   * SEAMLESS BOX
   * ################################
   */
  
  static final double WANDERINGBOXPROBABILITY=0.001;
  
  private List<Stripe> conditionallyCreateWanderingBox(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(random.nextDouble()<WANDERINGBOXPROBABILITY)
      a.addAll(createWanderingBox());
    return a;}
  
  private List<Stripe> createWanderingBox(){
    List<Stripe> a=new ArrayList<Stripe>();
    a.add(
      new Stripe_Sweeper(
        stripesystem,
        Stripe.ORIENTATION_HORIZONTAL,
        122,
        VALUESTROBE[0],
        Stripe_Sweeper.HEADING_POSITIVE,
        1));
    a.add(
      new Stripe_Sweeper(
        stripesystem,
        Stripe.ORIENTATION_HORIZONTAL,
        122,
        VALUESTROBE[0],
        Stripe_Sweeper.HEADING_NEGATIVE,
        1));
    a.add(
      new Stripe_Sweeper(
        stripesystem,
        Stripe.ORIENTATION_VERTICAL,
        122,
        VALUESTROBE[0],
        Stripe_Sweeper.HEADING_POSITIVE,
        1));
    a.add(
      new Stripe_Sweeper(
        stripesystem,
        Stripe.ORIENTATION_VERTICAL,
        122,
        VALUESTROBE[0],
        Stripe_Sweeper.HEADING_NEGATIVE,
        1));
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
