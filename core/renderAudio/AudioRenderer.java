package org.fleen.cloudedPlain.core.renderAudio;

import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public interface AudioRenderer{
  
  /*
   * 720=1*2*3*4*5*6
   * 720 has lots of factors, that's why I chose it
   * we do 60 frames per second
   * 60*720=43200
   * So 43200 is our per-second sample rate
   */
  public static final int
    //slices per second. aka video frame rate
    SLICERATE=60,
    //sound sample rate over a single slice. 
    FRAMESOUNDSAMPLERATE=720,
    //
    SOUNDSAMPLERATE=SLICERATE*FRAMESOUNDSAMPLERATE,
    SOUNDTICKMAXVAL=65535;
  
  /*
   * render the stripesystem to a 1/60th second segment of the sound array and return it.
   */
  int[] renderFrame(StripeSystem ss);

}
