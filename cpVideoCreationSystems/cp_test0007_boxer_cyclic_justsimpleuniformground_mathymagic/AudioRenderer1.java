package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0007_boxer_cyclic_justsimpleuniformground_mathymagic;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;

/*
 * CLEAN THIS UP WITH LOWPASS FILTER AFTERWARDS
 */
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
   * ################################
   */
  
  /*
   * get sound for each chunk, weighted by distance from center or something
   */
  public int[] renderFrame(StripeSystem ss){
    List<Chunk> chunks=getCloudedPlain().stripesystem.getChunks();
    int chunkcount=chunks.size();
    //get all chunk sounds
    int mra=getMaxRectangleArea();
    List<int[]> chunksounds=new ArrayList<int[]>(chunks.size());
    for(Chunk chunk:chunks)
      chunksounds.add(getSound(chunk,mra));
    //average them
    int[] average=new int[getCloudedPlain().getAudioSampleRatePerFrame()];
    int b;
    for(int i=0;i<getCloudedPlain().getAudioSampleRatePerFrame();i++){
      b=0;
      for(int[] c:chunksounds)
        b+=c[i];
      average[i]=b/chunkcount;}
    return average;}
  
  static final int MAXAMPLITUDE=32768;
  
  public int[] getSound(Chunk chunk,int mra){
    int audiosamplerateperframe=getCloudedPlain().getAudioSampleRatePerFrame();
    int[] sound=new int[audiosamplerateperframe];
    double a,b;
    double f=getFreq(chunk,mra);
    if(f==0)return sound;
    int wavelength=(int)(audiosamplerateperframe/f);
    for(int i=0;i<audiosamplerateperframe;i++){
      a=Math.sin(((double)i)/((double)wavelength));
      b=a*MAXAMPLITUDE;
      sound[i]=(int)b;}
    return sound;}
  
  static double FREQFACTOR=12.0;
  
  static int[] BASEFREQS={12,24,36,64};
  
  /*
   * get summed values for stripes
   * use that to get base frequency
   * multiply basefrequency by factored normalized area
   */
  private double getFreq(Chunk c,int mra){
    //get summed values for stripes
    int i=0;
    for(Stripe s:c.stripes)
      i+=s.getValue();
    //use that to get base frequency
    double basefreq=BASEFREQS[i%BASEFREQS.length];
    //get the normalized area and curve it too
    double 
      normalizedarea=((double)c.getArea())/((double)mra);
    normalizedarea=Math.pow(normalizedarea,0.33);
    //
    basefreq=basefreq*normalizedarea*FREQFACTOR;
    
    return (int)basefreq;}
  
  private int getMaxRectangleArea(){
    return cloudedplain.stripesystem.stage.getArea();}
 
}
