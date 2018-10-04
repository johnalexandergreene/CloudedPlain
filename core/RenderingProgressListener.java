package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;

public interface RenderingProgressListener{
  
  void notify(CloudedPlain plain,BufferedImage sliceimage,int[] slicesound);

}
