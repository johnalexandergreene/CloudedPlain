package org.fleen.cloudedPlain.core.cloud;

import org.fleen.cloudedPlain.core.Plain;

public interface Cloud{
  
  /*
   * The Plain that this cloud expresses itself upon
   */
  void setPlain(Plain plain);
  
  /*
   * When this Cloud is finished drifting over the plain, signal to the system that it should be removed.
   */
  boolean finished();
  
  /*
   * manifest this cloud upon the plain
   * refer to the plain for params like tslice
   * 
   * the thing this method does is this :
   *   add integer value to 0..n cells in the plain
   *     value is usually 1 (a monochrome effect) but that added value could be any integer. 
   *     thus generating 1 frame in our (60 fps) video 
   *   incrementally generate sound array
   *     set values over a 1/60 second long increment of the sound data array
   *   we could change this value over time. Do a strobe or flicker or something 
   */
  void manifest(int[][] slice);
  
}
