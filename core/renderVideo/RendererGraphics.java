package org.fleen.cloudedPlain.core.renderVideo;

import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.Plain;

public interface RendererGraphics{
  
  void setPlain(Plain plain);
  
  BufferedImage render(int[][] slice);

}
