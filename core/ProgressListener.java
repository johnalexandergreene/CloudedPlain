package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public interface ProgressListener{
  
  void notify(StripeSystem plain,BufferedImage sliceimage,int[] slicesound);

}
