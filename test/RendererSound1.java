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
  
  
  public int[] render(int[][] slice){
    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
    for(PlainShape shape:plain.getShapes())
      doSound(sound,shape);
    return sound;}
  
  
  private static final int[] COLORTONEWAVELENGTH={
    20,40,60,80,100,120,140    
  };
  
  private void doSound(int[] sound,PlainShape shape){
    int interval=COLORTONEWAVELENGTH[shape.color]+(shape.size()/20);
    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
      if(i%interval<30)
        incrementSound(sound,i);}
  
  
  private void incrementSound(int[] sound,int i){
    int a=sound[i]+800;
    if(a>Plain.SOUNDTICKMAXVAL)
      a=Plain.SOUNDTICKMAXVAL;
    sound[i]=a;}
    
    
    
 

}
