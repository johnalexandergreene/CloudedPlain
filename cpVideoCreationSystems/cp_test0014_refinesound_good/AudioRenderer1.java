package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0014_refinesound_good;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;

public class AudioRenderer1 implements AudioRenderer{

  /*
   * ################################
   * CLOUDED PLAIN
   * ################################
   */
  
  CloudedPlain cloudedplain;
  
  public void setCloudedPlain(CloudedPlain cp){
    cloudedplain=cp;}
  
  public CloudedPlain getCloudedPlain(){
    return cloudedplain;}
  
  /*
   * ################################
   * RENDER
   * Each frame is the average of all rectangles in the stripe system
   * frequency is color*fatness
   * volume is closeness to center
   * ################################
   */
  public int[] renderFrame(StripeSystem ss){
    //get metrics
    int soundpiecelength=cloudedplain.getAudioSampleRatePerFrame();
    //get all chunk sounds
    double[] stagecenter=cloudedplain.stripesystem.stage.getCenter();
    double maxdistance=cloudedplain.stripesystem.stage.getMaxDistanceFromCenter();
    double maxfatness=cloudedplain.stripesystem.stage.getFatness();
    List<Chunk> chunks=getCloudedPlain().stripesystem.getChunks();
    List<int[]> chunksounds=new ArrayList<int[]>();
    for(Chunk chunk:chunks)
      chunksounds.add(getChunkSound(chunk,soundpiecelength,maxfatness,maxdistance,stagecenter));
    //get averaged chunk sounds
    int[] totalsound=new int[soundpiecelength];
    int a;
    for(int i=0;i<soundpiecelength;i++){
      a=0;
      for(int[] cellsound:chunksounds)
        a+=cellsound[i];
      //
      a/=chunksounds.size();
      totalsound[i]=a;}
    //
    return totalsound;}
  
  //it works
  static final double 
    MAXAMPLITUDE=28000,
    FREQFACTOR=6.8;
  
  int[] getChunkSound(Chunk chunk,int soundpiecelength,double maxfatness,double maxdistance,double[] stagecenter){
    double value=chunk.getSummedValueOfStripes();
    double inversefatness=(maxfatness-chunk.getFatness())/maxfatness;
    double freq=value*inversefatness;
    freq=Math.pow(freq,0.73);//makes it sound nicer, clumps the freqs a bit, trims off the extreme stuff
    freq*=FREQFACTOR;
    //
    double amplitude=((maxdistance-chunk.getDistance(stagecenter[0],stagecenter[1]))/maxdistance)*MAXAMPLITUDE;
    //
    int[] sound=new int[soundpiecelength];
    double a;
    for(int i=0;i<soundpiecelength;i++){
      a=((double)i)/((double)soundpiecelength);
      a=a*Math.PI*2*freq;
      sound[i]=(int)(Math.sin(a)*amplitude);
      if(sound[i]>MAXAMPLITUDE)sound[i]=(int)MAXAMPLITUDE;
      if(sound[i]<-MAXAMPLITUDE)sound[i]=(int)(-MAXAMPLITUDE);}
    //
    return sound;}
 
}
