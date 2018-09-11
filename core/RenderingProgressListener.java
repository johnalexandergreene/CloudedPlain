package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;

public interface RenderingProgressListener{
  
  void notify(Plain plain,BufferedImage sliceimage,int[] slicesound);

}
