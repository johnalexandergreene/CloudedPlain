package org.fleen.cloudedPlain.core.video;

import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public interface VideoRenderer{
  
  void setPlain(StripeSystem plain);
  
  BufferedImage render(int[][] slice);

}
