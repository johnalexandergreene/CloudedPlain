package org.fleen.cloudedPlain.core.stripeGenerators;

import java.util.List;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.rectangles.Stripe;

/*
 * a generator generates stripes
 * it may employ other generators
 */
public interface StripeGenerator{
  
  void setPlain(Plain plain);
  
  List<Stripe> generate();
  
  boolean destroy();

}
