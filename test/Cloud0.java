package org.fleen.cloudedPlain.test;

import java.util.Random;

import org.fleen.cloudedPlain.core.Cloud;
import org.fleen.cloudedPlain.core.Plain;

public class Cloud0 implements Cloud{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public boolean finished(){
    return false;}

  public void manifest(int[][] slice){
    Random r=new Random();
    int x,y,c=plain.width*plain.height/3;
    for(int i=0;i<c;i++){
      x=r.nextInt(slice.length);
      y=r.nextInt(slice[0].length);
      slice[x][y]+=1;}}

}
