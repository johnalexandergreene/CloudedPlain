package org.fleen.cloudedPlain.videoCreationSystems.vcs0005_boxer_noncyclic_nostrobe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Box;

/*
 * random box stripes
 * 
 * we have 2 systems of box stripes
 *   ground
 *   wanderer
 * The ground stripes fill up the whole ground
 * The wanderers wander over the ground
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
  
  static int[][] VALUESTROBE={
    {1},
    {2},
    {3},
    {4},
    {5},
    {6},
    {7}};
      
  Random random=new Random();
  
  /*
   * ################################
   * GENERATE
   * ################################
   */
  
  public List<Stripe> generate(){
    
//    List<Stripe> g=conditionallyCreateGround();
    List<Stripe> w=conditionallyCreateWanderer();
    List<Stripe> stripes=new ArrayList<Stripe>();
//    stripes.addAll(g);
    stripes.addAll(w);
    return stripes;
  }
  
  
  /*
   * ################################
   * GROUNDERS
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
  List<Stripe> conditionallyCreateGround(){
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
   * WANDERER
   * ################################
   */
  
  /*
   * if the stripe count is low, medium or high
   */
  static double 
    PROBABILITY_AT_LOW_COUNT=0.05,
    PROBABILITY_AT_MED_COUNT=0.08,
    PROBABILITY_AT_HIGH_COUNT=0.08;
  
  List<Stripe> conditionallyCreateWanderer(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(random.nextDouble()<0.01)
      a.addAll(getBox(64,1));
    
    
    return a;
  }
  
  /*
   * ################################
   * CREATE BOX
   * ################################
   */
  
  public List<Stripe> getBox(int thickness,int speed){
    List<Stripe> a=new ArrayList<Stripe>();
    int[] valuestrobe=VALUESTROBE[random.nextInt(VALUESTROBE.length)];
    a.add(getBox(0,0,valuestrobe,thickness,speed));
    a.add(getBox(0,1,valuestrobe,thickness,speed));
    a.add(getBox(1,0,valuestrobe,thickness,speed));
    a.add(getBox(1,1,valuestrobe,thickness,speed));
  return a;}
  
  private Stripe_Box getBox(int ori,int hea,int[] valuestrobe,int thickness,int speed){
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
