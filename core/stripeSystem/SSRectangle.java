package org.fleen.cloudedPlain.core.stripeSystem;

public interface SSRectangle{
  
  /*
   * northwest corner point x
   */
  int getCoorX();
  
  /*
   * northwest corner point y
   */
  int getCoorY();
  
  int getWidth();
  
  int getHeight();
  
  /*
   * for our convenience
   */
  
  int getXMin();
  
  int getXMax();
  
  int getYMin();
  
  int getYMax();
  
}
