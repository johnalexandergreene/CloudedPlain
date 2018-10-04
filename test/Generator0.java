package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

public class Generator0 implements StripeGenerator{

  StripeSystem plain;
  
  public void setStage(StripeSystem plain){
    this.plain=plain;}
  
  public List<Stripe> generate(){
//  Random r=new Random();
  List<Stripe> stripes=new ArrayList<Stripe>();
  Stripe_Sweeper a;
  int[] valuepattern=new int[]{1,2,2,1};
  if(plain.frameindex%32==0){
    a=new Stripe_Sweeper(plain,Stripe_Sweeper.HEADING_NORTH,120,1,valuepattern);
    a.setPlain(plain);
    stripes.add(a);
    a=new Stripe_Sweeper(plain,Stripe_Sweeper.HEADING_EAST,120,1,valuepattern);
    a.setPlain(plain);
    stripes.add(a);
    a=new Stripe_Sweeper(plain,Stripe_Sweeper.HEADING_SOUTH,120,1,valuepattern);
    a.setPlain(plain);
    stripes.add(a);
    a=new Stripe_Sweeper(plain,Stripe_Sweeper.HEADING_WEST,120,1,valuepattern);
    a.setPlain(plain);
    stripes.add(a);}
  return stripes;}
  
  public boolean destroy(){
    return false;}
  
}
