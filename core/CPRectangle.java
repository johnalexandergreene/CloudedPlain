package org.fleen.cloudedPlain.core;

import java.util.ArrayList;
import java.util.List;

public abstract class CPRectangle{
  
  /*
   * northwest corner point x
   */
  public abstract int getCoorX();
  
  /*
   * northwest corner point y
   */
  public abstract int getCoorY();
  
  public abstract int getWidth();
  
  public abstract int getHeight();
  
  /*
   * 
   * 
   * get the intersection of 2 rectangles
   * that resulting intersection is [0..3] Intersection class rectangles
   * 
   * OUR RECTANGLES ARE SPECIAL
   * vertical stripes are always as high as the plain
   * horizontal stripes are always as wide as the plain
   * stripes may be outside the plain
   * 
   * intersections are always contained within the plain
   * intersections may be any dimension
   * 
   */
  public static List<Intersection> getIntersection(Stripe stripe,Plain plain){
    List<Intersection> intersections=new ArrayList<Intersection>(3);
    
  }
  
  public static List<Intersection> getIntersection(Stripe stripe,Intersection intersection){
    List<Intersection> intersections=new ArrayList<Intersection>(3);
    
  }

}
