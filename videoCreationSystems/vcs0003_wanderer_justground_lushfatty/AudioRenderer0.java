package org.fleen.cloudedPlain.videoCreationSystems.vcs0003_wanderer_justground_lushfatty;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;

public class AudioRenderer0 implements AudioRenderer{

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
   * ################################
   */
  
  /*
   * get sound for each chunk, weighted by distance from center or something
   */
  public int[] renderFrame(StripeSystem ss){
    List<Chunk> chunks=getCloudedPlain().stripesystem.getChunks();
    int chunkcount=chunks.size();
    List<int[]> chunksounds=new ArrayList<int[]>(chunks.size());
    for(Chunk chunk:chunks)
      chunksounds.add(getSound(chunk));
    int[] average=new int[getCloudedPlain().getAudioSampleRatePerFrame()];
    int b;
    for(int i=0;i<getCloudedPlain().getAudioSampleRatePerFrame();i++){
      b=0;
      for(int[] c:chunksounds)
        b+=c[i];
      average[i]=b/chunkcount;}
    return average;}
  
  public int[] getSound(Chunk chunk){
    int audiosamplerateperframe=getCloudedPlain().getAudioSampleRatePerFrame();
    int[] sound=new int[audiosamplerateperframe];
    double a,b;
    double f=getFreq(chunk);
//    System.out.println("freq="+f);
    if(f==0)return sound;
    int wavelength=(int)(audiosamplerateperframe/f);
    for(int i=0;i<audiosamplerateperframe;i++){
      a=Math.sin(((double)i)/((double)wavelength));
      b=a*65000;
      sound[i]=(int)b;}
    return sound;}
  
  static double FREQFACTOR=0.03;
  
  private double getFreq(Chunk c){
    double i=0;
    for(Stripe s:c.stripes)
      i+=s.getValue();
    i*=c.getArea();
    return i*FREQFACTOR;}
  
  
  
 
}
