package org.fleen.cloudedPlain.core.renderVideo;

import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.CloudedPlain;

public interface RendererGraphics{
  
  void setPlain(CloudedPlain plain);
  
  BufferedImage render(int[][] slice);

}
