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
 * STRIPE VALUE
 * 
 * the plain has a palette. An array. color=index
 * 
 * a cell is covered by 1..n stripes
 * 
 * each stripe has a value pattern. A strobe of values. We can translate these into color and sound
 * specified by a list of integers. ex : 1,2,2,1, or 1,2 or 4,1,2,2,1,4
 * or even just 1 value, if the value is constant
 * 
 * at each tick of the plain's clock we specify the value for a stripe
 * that value is gotten from the stripe's valuepattern array
 * the value = valuepattern[tick%valuepatternlength] 
 * 
 * when we render a cell we get all of the stripes that cover the cell and sum their values at that tick
 * thus we get the summed value at that cell
 * then we can use that value to get a color (from the plain palette) 
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
