package org.fleen.cloudedPlain.core.renderAudio;

import org.fleen.cloudedPlain.core.Plain;

public interface RendererSound{
  
  void setPlain(Plain plain);
  
  /*
   * a 1 slice piece of the plain sound
   * (1/60th of a second piece of the sound array given our present generation params)
   */
  int[] render(int[][] slice);

}
