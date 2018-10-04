package org.fleen.cloudedPlain.core.audio;

import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public interface AudioRenderer{
  
  void setStripeSystem(StripeSystem ss);
  
  /*
   * render a 1/60th second segment of the sound array and return it.
   */
  int[] renderFrame();

}
