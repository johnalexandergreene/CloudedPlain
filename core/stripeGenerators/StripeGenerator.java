package org.fleen.cloudedPlain.core.stripeGenerators;

import java.util.List;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.geom.Stripe;

/*
 * a generator generates stripes
 * it may employ other generators
 */
public interface StripeGenerator{
  
  void setPlain(CloudedPlain plain);
  
  List<Stripe> generate();
  
  boolean destroy();

}
