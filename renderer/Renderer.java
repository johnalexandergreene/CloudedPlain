package org.fleen.cloudedPlain.renderer;

import org.fleen.cloudedPlain.core.Plain;

/*
 * given a plain
 * generate a directory full of frame images
 * generate a sound array
 * incrementally display progress with a frame image and/or whatever
 * 
 */
public interface Renderer{
  
  void render(Plain plain);
  
}
