package org.fleen.cloudedPlain.core.stripe;

import java.util.List;

import org.fleen.cloudedPlain.core.Plain;

/*
 * a generator generates clouds
 * it may employ more generators
 */
public interface Generator{
  
  void setPlain(Plain plain);
  
  List<Stripe> generate();

}
