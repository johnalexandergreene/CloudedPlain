package org.fleen.cloudedPlain.test;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.RendererSound;

public class RendererSound0 implements RendererSound{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public int[] render(int[][] slice){
    return new int[]{1,2,3};}

}
