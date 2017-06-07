package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.Cloud;
import org.fleen.cloudedPlain.core.CloudCreator;
import org.fleen.cloudedPlain.core.Plain;

public class CloudCreator0 implements CloudCreator{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public List<Cloud> create(){
    List<Cloud> clouds=new ArrayList<Cloud>();
    if(plain.sliceindex==0){
      Cloud a=new Cloud0();
      a.setPlain(plain);
      clouds.add(a);
    }else{
      Random r=new Random();
      if(r.nextInt(10)==0){
        Cloud a=new Cloud0();
        a.setPlain(plain);
        clouds.add(a);}}
    return clouds;}}
