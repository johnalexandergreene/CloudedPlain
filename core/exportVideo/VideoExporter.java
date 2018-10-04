package org.fleen.cloudedPlain.core.exportVideo;

import java.awt.image.BufferedImage;
import java.io.File;

public interface VideoExporter{
  
  void export(BufferedImage frame,int index,File path);

}
