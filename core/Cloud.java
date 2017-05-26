package org.fleen.cloudedPlain.core;

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
   * manifest the next 2D slice of this Cloud
   * 
   * params can be :
   *   plain clock
   *   cloud clock, the count of frames generated so far...
   *   stuff like that
   *   -- We do not collide or otherwise interact between clouds in other than graphical mixing. We are keeping this simple.
   *   -- Clouds do not spawn clouds. Again, we are keeping this simple
   *   
   * the thing this method does is this :
   *   add integer value to 0..n cells in the plain
   *     value is usually 1 (a monochrome effect) but that added value could be any integer. 
   *     thus generating 1 frame in our (60 fps) video 
   *   incrementally generate sound array
   *     set values over a 1/60 second long increment of the sound data array
   *   we could change this value over time. Do a strobe or flicker or something 
   */
  void manifest();
  
  
  
  

}
