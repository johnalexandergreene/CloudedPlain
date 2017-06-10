package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;

public interface PlainProgressListener{
  
  void notify(Plain plain,BufferedImage sliceimage,int[] slicesound);

}
