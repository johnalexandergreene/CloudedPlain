package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0005_boxer_noncyclic_nostrobe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeProjector;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Box;

/*
 * random box stripes
 * 
 * we have 2 systems of box stripes
 *   ground
 *   wanderer
 * The ground stripes fill up the whole ground
 * The wanderers wander over the ground
 * 
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
  
  static int[][] VALUESTROBE={
    {1},
    {2},
    {3},
    {4},
    {5},
    {6},
    {7}};
      
  Random random=new Random();
  
  public List<Stripe> generate(){
    List<Stripe> 
      stripes=new ArrayList<Stripe>(),
      g=conditionallyCreateGround(),
      w=conditionallyCreateWanderer();
    stripes.addAll(g);
    stripes.addAll(w);
    return stripes;}
  
  /*
   * ################################
   * GROUND
   * ################################
   */
  
  static final int 
    THICKNESS_GROUND_MIN=128,
    THICKNESS_GROUND_MAX=512;
  
  int nextstripetime=-1;
  
  /*
   * the ground covers the stage completely
   * for each orientation and heading (h+, h-, v+, v-)
   *   when the prior ground stripe arrives at fully-within-stage (ex : for h+ that when location==0) create a new stripe
   *   
   * when the time hits boxstripe birthday+thickness then we create another stripe
   */
  List<Stripe> conditionallyCreateGround(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(nextstripetime==-1||nextstripetime==stripesystem.time){
      int thickness=(int)(random.nextDouble()*(THICKNESS_GROUND_MAX-THICKNESS_GROUND_MIN)+THICKNESS_GROUND_MIN);
      nextstripetime=stripesystem.time+thickness;
      a.addAll(createBox(thickness,1));}
    return a;}
  
  /*
   * ################################
   * WANDERER
   * ################################
   */
  
  int priorwanderertime;
  
  static int 
    STRIPECOUNT_LOW=8,
    STRIPECOUNT_HIGH=18;
  
  static double 
    PROBABILITY_AT_LOW_COUNT=0.8,
    PROBABILITY_AT_MED_COUNT=0.008,
    PROBABILITY_AT_HIGH_COUNT=0.0003;
  
  static final double 
    THICKNESS_WANDERER_MAX=100,
    THICKNESS_WANDERER_MIN=30;
  
  int[] SPEED={
    2,
    3,
    4
  };
  
  List<Stripe> conditionallyCreateWanderer(){
    List<Stripe> a=new ArrayList<Stripe>();
    double p,c=stripesystem.stripes.size();
    if(c<=STRIPECOUNT_LOW){
      p=PROBABILITY_AT_LOW_COUNT;
    }else if(c>STRIPECOUNT_LOW&&c<=STRIPECOUNT_HIGH){
      p=PROBABILITY_AT_MED_COUNT;
    }else{
      p=PROBABILITY_AT_HIGH_COUNT;}
    //
    if(random.nextDouble()<p&&(stripesystem.time-priorwanderertime)>THICKNESS_WANDERER_MAX*2){
      priorwanderertime=stripesystem.time;
      int
        thickness=(int)(random.nextDouble()*(THICKNESS_WANDERER_MAX-THICKNESS_WANDERER_MIN)+THICKNESS_WANDERER_MIN),
        speed=SPEED[random.nextInt(SPEED.length)];
      a.addAll(createBox(thickness,speed));}
    //
    return a;}
  
  /*
   * ################################
   * CREATE BOX
   * ################################
   */
  
  public List<Stripe> createBox(int thickness,int speed){
    List<Stripe> a=new ArrayList<Stripe>();
    int[] valuestrobe=VALUESTROBE[random.nextInt(VALUESTROBE.length)];
    a.add(createBoxStripe(0,0,valuestrobe,thickness,speed));
    a.add(createBoxStripe(0,1,valuestrobe,thickness,speed));
    a.add(createBoxStripe(1,0,valuestrobe,thickness,speed));
    a.add(createBoxStripe(1,1,valuestrobe,thickness,speed));
  return a;}
  
  private Stripe_Box createBoxStripe(int ori,int hea,int[] valuestrobe,int thickness,int speed){
    //get init location
    int 
      initlocation,
      stagewidth=stripesystem.stage.getWidth(),
      stageheight=stripesystem.stage.getHeight();
    if(stagewidth>stageheight){
      if(ori==0){//horizontal
        if(hea==0){//northerly
          initlocation=((stageheight-stagewidth)/2)-thickness;
        }else{//southerly
          initlocation=stageheight+((stagewidth-stageheight)/2);}
      }else{//vertical
        if(hea==0){//easterly
          initlocation=-thickness;
        }else{//westerly
          initlocation=stagewidth;}}
    }else{//stagewidth<=stageheight
      int 
        squarexmin=(stagewidth-stageheight)/2,
        squarexmax=stagewidth+((stageheight-stagewidth)/2);
      if(ori==0){//horizontal
        if(hea==0){//northerly
          initlocation=0;
        }else{//southerly
          initlocation=stageheight+thickness;}
      }else{//vertical
        if(hea==0){//easterly
          initlocation=squarexmin-thickness;
        }else{//westerly
          initlocation=squarexmax;}}}
  //
    Stripe_Box s=new Stripe_Box(stripesystem,ori,thickness,valuestrobe,hea,speed,initlocation);
    return s;
  }
  
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  public boolean destroy(){
    return false;}
  
}
