package org.fleen.cloudedPlain.test;

import java.util.Random;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.RendererSound;

public class RendererSound0 implements RendererSound{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public int[] render(int[][] slice){
    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
    Random r=new Random();
    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
      sound[i]=r.nextInt(Plain.SOUNDTICKMAXVAL);
    return sound;}

}
