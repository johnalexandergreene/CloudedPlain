package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;

public interface RendererListener{
  
  void notify(BufferedImage sliceimage,int[] slicesound);

}
