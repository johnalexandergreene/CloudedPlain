package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;

public interface RendererGraphics{
  
  void setPlain(Plain plain);
  
  BufferedImage render(int[][] slice);

}
