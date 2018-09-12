package org.fleen.cloudedPlain.core;

import java.util.ArrayList;
import java.util.List;

public abstract class CPStripe implements CPRectangle{
  
  /*
   * ################################
   * TYPE
   * A stripe is either horizontal or vertical
   * ################################
   */
  
  public static final int 
    ORIENTATION_HORIZONTAL=0,
    ORIENTATION_VERTICAL=1;
  
  public abstract int getOrientation();
  
  public boolean isHorizontal(){
    return getOrientation()==ORIENTATION_HORIZONTAL;}
  
  public boolean isVertical(){
    return getOrientation()==ORIENTATION_VERTICAL;}
  
  
  /*
   * ################################
   * when this stripe is done doing its dance 
   * it lets us know that it is time to destroy it
   * ################################
   */
  
  public abstract boolean destroyMe();
  
//  /*
//   * ################################
//   * GET INTERSECTION BETWEEN THIS STRIPE AND A PLAIN
//   * 
//   * get the intersection of 2 rectangles
//   * that resulting intersection is [0..3] Intersection class rectangles
//   * 
//   * OUR RECTANGLES ARE SPECIAL
//   * vertical stripes are always as high as the plain
//   * horizontal stripes are always as wide as the plain
//   * stripes may be outside the plain
//   * chunks are always contained within the plain
//   * chunks are freely dimensioned
//   * 
//   * In the case of null intersection we return a plain-shaped chunk with no parents 
//   * 
//   */
//  public List<Chunk> getIntersection(Plain plain){
//    List<Chunk> intersection=new ArrayList<Chunk>(3);
//    int a=getOrientation();
//    if(a==ORIENTATION_HORIZONTAL){
//      
//      
//    }else{//ORIENTATION_VERTICAL
//      
//    }
//    
//    //todo
//    return intersection;
//    
//  }
  
  /*
   * ################################
   * GET INTERSECTION BETWEEN THIS STRIPE AND A CHUNK
   * 
   * get the intersection of 2 rectangles
   * that resulting intersection is [0..3] Intersection class rectangles
   * 
   * 
   * OUR RECTANGLES ARE SPECIAL
   * vertical stripes are always as high as the plain
   * horizontal stripes are always as wide as the plain
   * stripes may be outside the plain
   * chunks are always contained within the plain
   * chunks are freely dimensioned
   * 
   */
  public List<Chunk> getIntersection(Chunk chunk){
    List<Chunk> intersection=new ArrayList<Chunk>(3);
    
    //todo
    return intersection;
    
  }


}
