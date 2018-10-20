package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper_Special;

/*
 * an orderly 4way system
 */
public class Generator5_soundtest2_squaretest implements StripeGenerator{

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
    List<Stripe> a=new ArrayList<Stripe>();
    int
      thickness=50,
      speed=1;
    if(stripesystem.time%100==0){
      int[] valuestrobe=new int[]{1,2,2,1};
      a.add(getSpecial(0,0,valuestrobe,thickness,speed));
      a.add(getSpecial(0,1,valuestrobe,thickness,speed));
      a.add(getSpecial(1,0,valuestrobe,thickness,speed));
      a.add(getSpecial(1,1,valuestrobe,thickness,speed));
    }else if(stripesystem.time%100==50){
      int[] valuestrobe=new int[]{2,1,1,2};
      a.add(getSpecial(0,0,valuestrobe,thickness,speed));
      a.add(getSpecial(0,1,valuestrobe,thickness,speed));
      a.add(getSpecial(1,0,valuestrobe,thickness,speed));
      a.add(getSpecial(1,1,valuestrobe,thickness,speed));
    }
  return a;}
  
  private Stripe_Sweeper_Special getSpecial(int ori,int hea,int[] valuestrobe,int thickness,int speed){
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
    Stripe_Sweeper_Special s=new Stripe_Sweeper_Special(stripesystem,ori,thickness,valuestrobe,hea,speed,initlocation);
    return s;
  }
  
  /*
   * StripeSystem stripesystem,int orientation,int[] valuestrobe,
    int thickness,int location,int lifespan(non-Javadoc)
   * @see org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator#destroy()
   */
  
  public boolean destroy(){
    return false;}
  
}
