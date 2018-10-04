package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Loiterer;

public class Generator1 implements StripeGenerator{

  StripeSystem plain;
  
  public void setStage(StripeSystem plain){
    this.plain=plain;}
  
  boolean generated=false;
  
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>();
    if(!generated){
      a.add(new Stripe_Loiterer(plain));
      generated=true;
    }
  return a;}
  
  public boolean destroy(){
    return false;}
  
}
