package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.Stripe_Loiterer;
import org.fleen.cloudedPlain.core.geom.Stripe;
import org.fleen.cloudedPlain.core.stripeGenerators.StripeGenerator;

public class Generator1 implements StripeGenerator{

  CloudedPlain plain;
  
  public void setPlain(CloudedPlain plain){
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
