package org.fleen.cloudedPlain.core.rectangles;

import java.util.ArrayList;
import java.util.List;

public abstract class CPRectangle{
  
  /*
   * northwest corner point
   */
  public abstract int getCoorX();
  
  public abstract int getCoorY();
  
  public abstract int getWidth();
  
  public abstract int getHeight();
  
  /*
   * get the intersection of the 2 rectangles
   * the intersection is [0..3] rectangles
   * 
   * OUR RECTANGLES ARE SPECIAL
   * vertical stripes are always as high as the plain
   * horizontal stripes are always as wide as the plain
   * stripes may be outside the plain
   * 
   * intersections are always contained within the plain
   * intersections may be any dimension
   * 
   * 
   * 
   * 
   * 
   */
  public static List<Intersection> getIntersection(CPRectangle r0,CPRectangle r1){
    List<Intersection> intersections=new ArrayList<Intersection>(3);
  }

}
