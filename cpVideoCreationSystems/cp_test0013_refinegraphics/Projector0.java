package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0013_refinegraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeGenerator.StripeProjector;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe_Sweeper;

/*
 * an orderly 4way system
 */
public class Projector0 implements StripeProjector{

  /*
   * ################################
   * STRIPE SYSTEM
   * ################################
   */
  
  StripeSystem stripesystem;
  
  public void setStripeSystem(StripeSystem stripesystem){
    this.stripesystem=stripesystem;}
  
  /*
   * ################################
   * GENERATE
   * ################################
   */
  
  Random random=new Random();
 
  public List<Stripe> generate(){
    List<Stripe> a=new ArrayList<Stripe>(),b;
    b=conditionallyCreateSeamlessBox();
    if(b!=null)a.addAll(b);
    b=conditionallyCreatePeriodicSwarmBox();
    if(b!=null)a.addAll(b);
    return a;}
  
  /*
   * ################################
   * PERIODIC BOX SWARM
   * At randomly do a swarm of periodic boxes
   * we can have 0, 1 or 2 such swarms extant at any time
   * ################################
   */
  
  static final double NULLPROBABILITY=0.5;//for no swarm intervals
  
  PeriodicSwarm swarm0=null,swarm1=null;
  
  private List<Stripe> conditionallyCreatePeriodicSwarmBox(){
    //test swarms for create
    if(swarm0==null)swarm0=createSwarm();
    if(swarm1==null)swarm1=createSwarm();
    //test swarms for create box, conditionally create boxes
    List<Stripe> a=new ArrayList<Stripe>();
    if((stripesystem.time+swarm0.offset)%swarm0.period==0)
      a.addAll(createBox(swarm0.thickness,swarm0.strobe,swarm0.speed));
    if((stripesystem.time+swarm1.offset)%swarm1.period==0)
      a.addAll(createBox(swarm1.thickness,swarm1.strobe,swarm1.speed));
    //test swarms for kill
    if(stripesystem.time>swarm0.killtime)
      swarm0=null;
    if(stripesystem.time>swarm1.killtime)
      swarm1=null;
    //
    return a;}
  
  private PeriodicSwarm createSwarm(){
    if(random.nextDouble()<NULLPROBABILITY){
      return new PeriodicSwarm(getKilltimeForSwarm());
    }else{
      int
        period=getPeriodForSwarm(),
        offset=getOffsetForSwarm(period),
        thickness=getThicknessForSwarm(period),
        speed=getSpeedForSwarm(),
        killtime=getKilltimeForSwarm();
      int[] strobe=getRandomStrobe();
      
      return new PeriodicSwarm(
        period,
        offset,
        thickness,
        speed,
        strobe,
        killtime);}}
  
  private int getPeriodForSwarm(){
    return random.nextInt(180)+90;}
  
  private int getOffsetForSwarm(int period){
    return random.nextInt(period*2);}
  
  private int getThicknessForSwarm(int period){
    return random.nextInt((int)(period*0.5))+(int)(period*0.2);}
  
  int[] SWARMSPEED={1,2,2,3};
  
  private int getSpeedForSwarm(){
    return SWARMSPEED[random.nextInt(SWARMSPEED.length)];}
  
  private int getKilltimeForSwarm(){
    int lifespan=random.nextInt(360)+60;
    int k=lifespan+stripesystem.time;
    return k;}
  
  /*
   * ################################
   * SEAMLESS BOX
   * ################################
   */
  
  static final int[] SEAMLESSBOXTHICKNESS={100,150,200};
  
  int 
    priorseamlessboxbirthday=-1,
    priorseamlessboxthickness=-1;
  
  private List<Stripe> conditionallyCreateSeamlessBox(){
    if(priorseamlessboxbirthday==-1||stripesystem.time==(priorseamlessboxbirthday+priorseamlessboxthickness)){
      int thickness=SEAMLESSBOXTHICKNESS[random.nextInt(SEAMLESSBOXTHICKNESS.length)];
      int[] valuestrobe=getRandomStrobe();
      priorseamlessboxbirthday=stripesystem.time;
      priorseamlessboxthickness=thickness;
      return createBox(thickness,valuestrobe,1); }
    return null;}
  
  /*
   * ################################
   * BOX
   * 4 sweeping stripes
   * ################################
   */
  
  int[] SPEED={1,2,3};
  
  int[][] VALUESTROBE={
    {-1,-1,1,1},
    {1,1,2,2},
    {1,1,1,1,2,2,2,2},
    {-1,-1,-1,-1,1,1,1,1}};
  
  int[] getRandomStrobe(){
    return VALUESTROBE[random.nextInt(VALUESTROBE.length)];}
  
  private List<Stripe> createBox(int thickness,int[] valuestrobe,int speed){
    List<Stripe> a=new ArrayList<Stripe>();
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_HORIZONTAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_POSITIVE,
      speed));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_HORIZONTAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_NEGATIVE,
      speed));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_VERTICAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_POSITIVE,
      speed));
    a.add(new Stripe_Sweeper(
      stripesystem,
      Stripe.ORIENTATION_VERTICAL,
      thickness,
      valuestrobe,
      Stripe_Sweeper.HEADING_NEGATIVE,
      speed));
    //
    return a;}
    
  /*
   * ################################
   * DESTROY
   * ################################
   */
  
  public boolean destroy(){
    return false;}
  
}
