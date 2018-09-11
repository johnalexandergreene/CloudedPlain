package org.fleen.cloudedPlain.core.stripeGenerators;

import java.util.List;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.Stripe_Sweeper;

/*
 * a generator generates stripes
 * it may employ other generators
 */
public interface StripeGenerator{
  
  void setPlain(Plain plain);
  
  List<Stripe_Sweeper> generate();
  
  boolean destroy();

}
