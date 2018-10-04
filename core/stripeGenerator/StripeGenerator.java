package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.List;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

/*
 * a generator generates stripes
 * it may employ other generators
 */
public interface StripeGenerator{
  
  void setStripeSystem(StripeSystem cloudedplain);
  
  List<Stripe> generate();
  
  boolean destroy();

}
