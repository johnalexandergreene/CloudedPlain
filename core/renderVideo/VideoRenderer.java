package org.fleen.cloudedPlain.core.renderVideo;

import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public interface VideoRenderer{
  
  BufferedImage renderFrame(StripeSystem stripesystem);

}
