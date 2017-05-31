package org.fleen.cloudedPlain.test;

import java.util.Random;

import org.fleen.cloudedPlain.core.Cloud;
import org.fleen.cloudedPlain.core.Plain;

public class Cloud0 implements Cloud{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;
    
  }

  public boolean finished(){
    return false;
  }

  public void manifest(){
    Random r=new Random();
    int w=plain.getWidth(),h=plain.getHeight();
    
  }

}
