package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.Cloud;
import org.fleen.cloudedPlain.core.Generator;
import org.fleen.cloudedPlain.core.Plain;

public class Generator0 implements Generator{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public List<Cloud> generateClouds(){
    List<Cloud> clouds=new ArrayList<Cloud>();
    if(plain.sliceindex==0){
      Cloud a=new Cloud0();
      a.setPlain(plain);
      clouds.add(a);
    }else{
      Random r=new Random();
      if(r.nextInt(100)==0){
        Cloud a=new Cloud0();
        a.setPlain(plain);
        clouds.add(a);
        }
    }
    return clouds;}}
