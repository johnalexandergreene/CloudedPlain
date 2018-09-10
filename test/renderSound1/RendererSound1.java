package org.fleen.cloudedPlain.test.renderSound1;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.renderSound.RendererSound;

/*
 * THE WAY WE WANT TO DO SOUND
 * 
 * first, get rid of the generalized pixel-pattern thing. we are just doing intersecting rectangles
 * 
 * for sound
 * at a slice
 * get all rectangles in the composition
 * each rectangle has height, width, area, color, location
 * these variables can all corrospond to sound-generation parameters
 * we might add a little random too, to get a nicer chorus
 * 
 * GETTING RECTANGLES
 * starting at the topleft pixel, scan all pixels, left to right, top to bottom
 * a bunch of adjacent pixels of the same color makes a line
 * a bunch of adjacent lines of the same color makes a rectangle
 * ugh. this might be weird.
 * 
 * OR
 * Given a bunch of lines (for all stripes, the lines that define the 2 moving edges of the stripe-rectangle)
 * ugh
 * 
 * GETTING SOUND
 * a rectangle's sound is derived from its location, height, width, gangliness, area, color... stuff like that
 * constant rectangles make constant sounds
 * changing rectangles make changing sounds
 * etc.
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class RendererSound1 implements RendererSound{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

//  public int[] render(int[][] slice){
//    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
//    Random r=new Random();
//    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
//      sound[i]=r.nextInt(Plain.SOUNDTICKMAXVAL);
//    return sound;}
  
  public int[] render(int[][] frame){
    int[] sound=new int[Plain.FRAMESOUNDSAMPLERATE];
    List<CellRectangle> cr=getCellRectangles(frame);
    for(int i=0;i<Plain.FRAMESOUNDSAMPLERATE;i++)
      sound[i]=getSound(cr,i);
    return sound;}
  
  
  
  /*
   * #########################################
   * get rectangles
   * 
   * scan the cells starting at 0,0 
   * 
   * scan the first line
   * get the first cell. 
   * create our first cellrectangle
   * that cell is the topleft cell of the first cellrectangle
   *   that gives us our topleft coordinate
   *   at this moment height=1 and width=1
   *   value=the value of that cell 
   * scan to the right
   * if the next cell is the same value then width++
   * otherwise create a new cellrectangle
   * keep doing that until we hit the end of the line
   * now go to the next line (x=0,y=1)
   * test the present cell.
   * if it's the same value as the cell at y-1 then add 1 to the height of that cell's cellrectangle
   * otherwise create a new cellrectangle
   * 
   *  or something like that
   *  
   *  ################################
   *  
   *  a better idea
   *  
   *  given the plain rectangle and the stripe rectangles
   *  intersect them
   *  
   *  given the base rectangle and an arbitrary stripe rectangle : r0
   *  this gives us a number of new rectangles : r1
   *  mark the new rectangles appropriately with their "parent" rectangles
   *  remove the 2 original rectangles.
   *  get another stripe rectangle
   *  intersect it with the rectangles in r1
   *  keep products of intersections. remove all rectangles involved in the intersection.
   *  
   *  keep doing that until we run out of stripe rectangles.
   *  now we have our intersection rectangles
   *  each with the list of all rectangles that were intersected to create it.
   *  (and then we can sum the values the parent rectangles to get the value of that intersection rectangle) 
   *  
   * 
   * 
   * 
   * 
   * 
   * ################################
   */
  
  List<CellRectangle> getIntersectionRectangles(){
    
  }
  
  
  List<CellRectangle> getCellRectangles(int[][] frame){
    List<CellRectangle> cr=new ArrayList<CellRectangle>();
    for(int x=0;x<frame.length;x++)
      for(int y=0;y<frame[0].length;y++)
        doNextCell(frame,x,y,cr);
    return cr;}
  
  void doNextCell(int[][] frame,int x,int y,List<CellRectangle> cr){
    
  }
  
    
    
 

}
