package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0014_refinesound_good;

public class PeriodicSwarm{
  
  public PeriodicSwarm(int period,int offset,int thickness,int speed,int[] strobe,int killtime){
    this.period=period;
    this.offset=offset;
    this.thickness=thickness;
    this.speed=speed;
    this.strobe=strobe;
    this.killtime=killtime;}
  
  //for null swarm, for no swarm
  public PeriodicSwarm(int killtime){
    this.killtime=killtime;}
  
  public int period=-1,offset,thickness,speed;
  public int killtime;
  public int[] strobe;
  
  public boolean isNull(){
    return period==-1;}
  
}
