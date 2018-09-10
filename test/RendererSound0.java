package org.fleen.cloudedPlain.test;

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
  
  public int[] render(int[][] slice){
    int[] sound=new int[Plain.FRAMESOUNDSAMPLERATE];
    int cellsum=getCellSum(slice);
    int a,b;
    b=cellsum*cellsum;
    if(b>1000000000)b=1000000000;
    for(int i=0;i<Plain.FRAMESOUNDSAMPLERATE;i++){
      a=i%1000;
      if(a<500)
        sound[i]=b;
      else
        sound[i]=0;
//      sound[i]=(int)(Integer.MAX_VALUE*Math.sin(i/Plain.SLICESOUNDSAMPLERATE));
    }
    return sound;}
  
  private int getCellSum(int[][] slice){
    int s=0;
    for(int x=0;x<plain.frame.length;x++){
      for(int y=0;y<plain.frame[0].length;y++){
        s+=plain.frame[x][y];}}
    return s;}
  
//  
//  public int[] render(int[][] slice){
//    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
//    for(PlainShape shape:plain.getShapes())
//      doSound(sound,shape);
//    return sound;}
//  
//  
//  private static final int[] COLORTONEWAVELENGTH={
//    20,40,60,80,100,120,140    
//  };
//  
//  private void doSound(int[] sound,PlainShape shape){
//    int interval=COLORTONEWAVELENGTH[shape.color]+(shape.size()/20);
//    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
//      if(i%interval<30)
//        incrementSound(sound,i);}
//  
//  
//  private void incrementSound(int[] sound,int i){
//    int a=sound[i]+800;
//    if(a>Plain.SOUNDTICKMAXVAL)
//      a=Plain.SOUNDTICKMAXVAL;
//    sound[i]=a;}
    
    
    
 

}
