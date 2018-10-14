package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

/*
 * an orderly 4way system
 */
public class Generator4 implements StripeGenerator{

  /*
   * ################################
   * STRIPE SYSTEM
   * ################################
   */
  
  StripeSystem stripesystem;
  
  public void setStripeSystem(StripeSystem stripesystem){
    this.stripesystem=stripesystem;}
  
  public List<Stripe> generate(){
    Stripe s;
    List<Stripe> a=new ArrayList<Stripe>();
    if(stripesystem.time%64==0){
      for(int ori=0;ori<2;ori++){
        for(int hea=0;hea<2;hea++){
          s=new Stripe_Sweeper(stripesystem,ori,32,new int[]{2,2,1},hea,1);
          a.add(s);}}}
    if((stripesystem.time+32)%64==0){
      for(int ori=0;ori<2;ori++){
        for(int hea=0;hea<2;hea++){
          s=new Stripe_Sweeper(stripesystem,ori,32,new int[]{1,2,3},hea,1);
          a.add(s);}}}
    if(stripesystem.time%128==0){
      for(int ori=0;ori<2;ori++){
        for(int hea=0;hea<2;hea++){
          s=new Stripe_Sweeper(stripesystem,ori,64,new int[]{3,3,4,4},hea,1);
          a.add(s);}}}
    if(stripesystem.time%64==0){
      for(int ori=0;ori<2;ori++){
        for(int hea=0;hea<2;hea++){
          s=new Stripe_Sweeper(stripesystem,ori,128,new int[]{1,3,3},hea,1);
          a.add(s);}}}
  return a;}
  
  /*
   * StripeSystem stripesystem,int orientation,int[] valuestrobe,
    int thickness,int location,int lifespan(non-Javadoc)
   * @see org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator#destroy()
   */
  
  public boolean destroy(){
    return false;}
  
}
