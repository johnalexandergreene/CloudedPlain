package org.fleen.cloudedPlain.core.stripeSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunks;

/*
 * a system of 
 *   1 stage rectangle
 *   0..n stripe rectangles
 *   0..n stripe generators
 *   a time parameter (an integer. A frame index.)
 * 
 * We increment the time (t) from 0 to whatever
 * 
 * t is a parameter for the stripes' definition, governing their location, value, etc
 * stripes generally hang out for a while, maybe sweeping across the stage, maybe just standing there or whatever, then get destroyed.
 * 
 * t also governs the behavior of our stripe generators. Telling them to create a stripe or another generator or kill themselves or something
 * 
 * we also provide various analysis methods for the system, for example getting the intersection squares
 * 
 */
public class StripeSystem{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public StripeSystem(int w,int h,StripeGenerator g){
    initStage(w,h);
    generator=g;
    g.setStripeSystem(this);}
 
  /*
   * ################################
   * STAGE
   * ################################
   */
  
  public Stage stage;
  
  public void initStage(int w,int h){
    stage=new Stage(w,h);}
  
  /*
   * ################################
   * STRIPES
   * ################################
   */
  
  /*
   * All the Stripes generated by the generator, or the generator's generators, etc
   * They are generated, do their thing, then signal to be destroyed
   */
  public List<Stripe> stripes=new ArrayList<Stripe>();  
  
  /*
   * ################################
   * STRIPE GENERATOR
   * ################################
   */
  
  /*
   * this is the plain's root stripe generator. The final authority on stripe generation.
   * the plain contains one generator, which may refer to other generators, and so on, treewise
   */
  public StripeGenerator generator=null;

  
  public void setGenerator(StripeGenerator g){
    generator=g;
    g.setStripeSystem(this);}
  
  /*
   * ################################
   * TIME
   * aka frame index
   * 60 frames per second
   * each frame encompasses 1/60th of a second
   * ################################
   */
  
  public int time;
  
  public void incrementFrame(){
    generateStripes();
    destroyStripes();
    time++;}
  
  /*
   * for each stripe generator, conditionally generate 0..n stripes
   */
  void generateStripes(){
    List<Stripe> newstripes=generator.generate();
    stripes.addAll(newstripes);}
  
  /*
   * get the stripes that are ready to be destroyed
   * remove them from the stripes list
   */
  void destroyStripes(){
    Iterator<Stripe> i=stripes.iterator();
    Stripe c;
    int d=0;
    while(i.hasNext()){
      c=i.next();
      if(c.destroyMe()){
        i.remove();
        d++;}}
    if(d>0)System.out.println("destroyed : "+d);}
  
  /*
   * ################################
   * CHUNKS
   * ################################
   */
  
  public Chunks getChunks(){
    return new Chunks(this);}
  
}
