package org.fleen.cloudedPlain.core.stripeGenerator;

import java.util.List;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

/*
 * a stripe generator generates stripes for a clouded plain
 * it may employ other generators
 */
public interface StripeGenerator{
  
  void setStripeSystem(StripeSystem cloudedplain);
  
  List<Stripe> generate();
  
  /*
   * we're done using this generator so get rid of it
   * not generally used for the root stripegenerator but it might be used for a branch, 
   * like if that branch's purpose is to generate a transitory pattern of stripes
   */
  boolean destroy();

}
