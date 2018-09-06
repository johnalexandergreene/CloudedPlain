package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.cloud.Cloud_Stripe;
import org.fleen.cloudedPlain.core.cloud.Cloud;
import org.fleen.cloudedPlain.core.generator.Generator;

public class Generator0 implements Generator{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

//  public List<Cloud> generate(){
//    Random r=new Random();
//    List<Cloud> clouds=new ArrayList<Cloud>();
//    if(plain.sliceindex==0){
//      Cloud a=new C_Stripe(plain,r.nextInt(4),50,1,0,0,false,1);
//      a.setPlain(plain);
//      clouds.add(a);
//    }else{
//      if(r.nextInt(50)==0){
//        Cloud a=new C_Stripe(plain,r.nextInt(4),50,1,0,0,false,1);
//        a.setPlain(plain);
//        clouds.add(a);
//        }
//    }
//    return clouds;}
  
  public List<Cloud> generate(){
    Random r=new Random();
    List<Cloud> clouds=new ArrayList<Cloud>();
    Cloud a;
    if(plain.sliceindex%12==0){
      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_SOUTH,50,1,0,1,false,2);
      a.setPlain(plain);
      clouds.add(a);}
    if(plain.sliceindex%36==0){
      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_EAST,10,1,0,2,false,1);
      a.setPlain(plain);
      clouds.add(a);  
    if(plain.sliceindex%64==0){
      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_NORTH,60,1,0,4,false,3);
      a.setPlain(plain);
      clouds.add(a);}
    if(plain.sliceindex%24==0){
      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_WEST,120,4,0,8,false,3);
      a.setPlain(plain);
      clouds.add(a);}
    
    }
    
    
    return clouds;}
  
}
