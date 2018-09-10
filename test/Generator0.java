package org.fleen.cloudedPlain.test;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.Stripe;
import org.fleen.cloudedPlain.core.stripeGenerators.StripeGenerator;

public class Generator0 implements StripeGenerator{

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
  
//  public List<Cloud> generate(){
////    Random r=new Random();
//    List<Cloud> clouds=new ArrayList<Cloud>();
//    Cloud a;
//    if(plain.sliceindex%12==0){
//      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_SOUTH,50,1,0,1,false,2);
//      a.setPlain(plain);
//      clouds.add(a);}
//    if(plain.sliceindex%36==0){
//      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_EAST,10,1,0,2,false,1);
//      a.setPlain(plain);
//      clouds.add(a);  
//    if(plain.sliceindex%64==0){
//      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_NORTH,60,1,0,4,false,3);
//      a.setPlain(plain);
//      clouds.add(a);}
//    if(plain.sliceindex%24==0){
//      a=new Cloud_Stripe(plain,Cloud_Stripe.HEADING_WEST,120,4,0,8,false,3);
//      a.setPlain(plain);
//      clouds.add(a);}
//    
//    }
//    
//    
//    return clouds;}
  
  public List<Stripe> generate(){
//  Random r=new Random();
  List<Stripe> clouds=new ArrayList<Stripe>();
  Stripe a;
  int[] valuepattern=new int[]{1,2,2,1};
  if(plain.frameindex%32==0){
    a=new Stripe(plain,Stripe.HEADING_NORTH,120,1,valuepattern);
    a.setPlain(plain);
    clouds.add(a);
    a=new Stripe(plain,Stripe.HEADING_EAST,120,1,valuepattern);
    a.setPlain(plain);
    clouds.add(a);
    a=new Stripe(plain,Stripe.HEADING_SOUTH,120,1,valuepattern);
    a.setPlain(plain);
    clouds.add(a);
    a=new Stripe(plain,Stripe.HEADING_WEST,120,1,valuepattern);
    a.setPlain(plain);
    clouds.add(a);}
  return clouds;}
  
  public boolean destroy(){
    return false;}
  
}
