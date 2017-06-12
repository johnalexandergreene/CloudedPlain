package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.PlainShape;
import org.fleen.cloudedPlain.core.PlainShapeCell;
import org.fleen.cloudedPlain.core.RendererSound;

public class RendererSound0 implements RendererSound{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

//  public int[] render(int[][] slice){
//    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
//    Random r=new Random();
//    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
//      sound[i]=r.nextInt(Plain.SOUNDTICKMAXVAL);
//    return sound;}
  
  /*
   * set values at uniform intervals
   * 
   * get all of the contiguous blobs, ie : shapes, in the plain
   *   ALG FOR GETTING A SHAPE
   *     get arbitrary cell
   *     gather adjacent same-colored cells for as long as such are available. add them to a list
   *     thus the shape
   *     
   *   count the cells. that's the area
   *   count the edge cells. that's the perimeter
   *   etc 
   * 
   * 
   * analyze them
   * this might be heavy
   * get all contiguous blobs of cells
   * for each blob, get
   *   area
   *   color
   *   distance from center of plain
   *   distance from nearest edge of plain
   *   perimeter
   *   gangliness (perimeter/area)
   *   
   * FOR TEST
   * for each shape get the color and area
   * color means pitch. area means volume
   *   
   */
  public int[] render(int[][] slice){
    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
    List<PlainShape> shapes=getPlainShapes();
    for(PlainShape shape:shapes)
      addToSound(shape,sound);
    
    
    
//    Random r=new Random();
//    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
//      sound[i]=r.nextInt(Plain.SOUNDTICKMAXVAL);
    return sound;}
  
  private List<PlainShape> getPlainShapes(){
    Set<PlainShapeCell> rawcells=plain.getRawPlainShapeCells();
    List<PlainShape> shapes=new ArrayList<PlainShape>();
    PlainShape shape;
    PlainShapeCell first;
    Iterator<PlainShapeCell> i;
    while(!rawcells.isEmpty()){
      //get a first cell for the new shape
      i=rawcells.iterator();
      first=i.next();
      i.remove();
      //make the shape
      shape=new PlainShape(plain,first);
      shape.aggolomerate(rawcells);}
    return shapes;}
  
  

}
