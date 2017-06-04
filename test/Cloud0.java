package org.fleen.cloudedPlain.test;

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

  public void manifest(int[][] slice){
    // TODO Auto-generated method stub
    
  }

}
