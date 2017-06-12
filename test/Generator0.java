package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.cloud.C_Stripe;
import org.fleen.cloudedPlain.core.cloud.Cloud;
import org.fleen.cloudedPlain.core.generator.Generator;

public class Generator0 implements Generator{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public List<Cloud> generate(){
    Random r=new Random();
    List<Cloud> clouds=new ArrayList<Cloud>();
    if(plain.sliceindex==0){
      Cloud a=new C_Stripe(plain,r.nextInt(4),50,1,0,0,false,1);
      a.setPlain(plain);
      clouds.add(a);
    }else{
      if(r.nextInt(50)==0){
        Cloud a=new C_Stripe(plain,r.nextInt(4),50,1,0,0,false,1);
        a.setPlain(plain);
        clouds.add(a);
        }
    }
    return clouds;}}
