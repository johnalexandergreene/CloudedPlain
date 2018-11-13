package org.fleen.cloudedPlain.videoCreationSystems.vcs0008_boxer_cyclic_justsimpleuniformground_mathymagic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
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

  static int[][] VALUESTROBE={
      {1,1,1,1,3,3,3,3,5,5,5,5},
      {7,7,7,11,11,11,13,13,13,17,17,17},
      {19,19,19,19,19,19,23,23,23,23,23,23},
      
      };
  
//  static int[][] VALUESTROBE={
//    {1},
//    {2},
//    {3},
//    {4},
//    {5},
//    {6},
//    {7}};
  
//  static final int[][] VALUESTROBE=new int[][]{
//    {1,2},
//    {3,5},
//    {6,9},
//    {1,1,2,2},
//    {4,4,6,6},
//    {8,8,9,9},
//    {1,1,1,2,2,2},
//    {5,5,5,8,8,8},
//    {1,2,3},
//    {6,8,10},
//    {2,2,3,3,3},
//    {6,6,3,3,3,9,9,9},
//  };
      
  Random random=new Random();
  
  public List<Stripe> generate(){
    List<Stripe> 
      stripes=new ArrayList<Stripe>(),
      g=conditionallyCreateGround();
    stripes.addAll(g);
    return stripes;}
  
  /*
   * ################################
   * GROUND
   * ################################
   */
  
  static final int THICKNESS=100;
  
  int nextstripetime=-1;
  
  boolean DIDTEST=false;
  
  /*
   * the ground covers the stage completely
   * for each orientation and heading (h+, h-, v+, v-)
   *   when the prior ground stripe arrives at fully-within-stage (ex : for h+ that when location==0) create a new stripe
   *   
   * when the time hits boxstripe birthday+thickness then we create another stripe
   */
  List<Stripe> conditionallyCreateGround(){
    List<Stripe> a=new ArrayList<Stripe>();
//    if(DIDTEST)return a;
    if(nextstripetime==-1||nextstripetime==stripesystem.time){
//      DIDTEST=true;
      nextstripetime=stripesystem.time+THICKNESS;
      a.addAll(createBox(THICKNESS,1));}
    return a;}
  
  /*
   * ################################
   * CREATE BOX
   * ################################
   */
  
  static double MAGICFACTOR=1024;
  
  public List<Stripe> createBox(int thickness,int speed){
    List<Stripe> a=new ArrayList<Stripe>();
    //
    double magic=Math.abs((Math.sin((double)stripesystem.time/((double)VCS.DURATION))*MAGICFACTOR));
    //
    int[] valuestrobe=VALUESTROBE[((int)magic)%VALUESTROBE.length];
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
