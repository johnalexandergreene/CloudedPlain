package org.fleen.cloudedPlain.core.renderAudio;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public interface AudioRenderer{
  
  void setCloudedPlain(CloudedPlain cp);
  
  CloudedPlain getCloudedPlain();
  
  /*
   * render the stripesystem to a 1/60th second frame of the sound array and return it.
   * those values are 2 byte. we're gonna convert them into a byte array at export.
   * that's max=65536, signed.
   * so  -65536 to 65536 ?
   */
  int[] renderFrame(StripeSystem ss);

}
