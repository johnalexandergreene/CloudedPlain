package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0009_backtosquare_rotatingpalette_experiments;

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
    List<int[]> chunksounds=new ArrayList<int[]>(chunkcount);
    for(Chunk chunk:chunks)
      chunksounds.add(getSound(chunk,mra));
    //get all chunk inverse distances
    double maxdistance=cloudedplain.stripesystem.stage.getMaxDistanceFromCenter();
    List<Double> chunkinvdis=new ArrayList<Double>(chunkcount);
    double invdis;
    for(Chunk chunk:chunks){
      invdis=getChunkInverseDistanceFromCenter(chunk,maxdistance);
      chunkinvdis.add(invdis);}
    //sum and average, weighted by inverse distance
    int[] framesound=new int[getCloudedPlain().getAudioSampleRatePerFrame()];
    double ticksound,invdissum;
    for(int i=0;i<getCloudedPlain().getAudioSampleRatePerFrame();i++){
      ticksound=0;
      invdissum=0;
      for(int j=0;j<chunkcount;j++){
        ticksound+=chunksounds.get(j)[i]*chunkinvdis.get(j);
        invdissum+=chunkinvdis.get(j);}
      framesound[i]=(int)(ticksound/chunkcount);}
    return framesound;}
  
  /*
   * test 4 edges
   * return inverse distance of closest
   * if center is inside chunk then distance is 0
   */
  double getChunkInverseDistanceFromCenter(Chunk chunk,double maxdistance){
    double[] c=cloudedplain.stripesystem.stage.getCenter();
    double d=chunk.getDistance(c[0],c[1]);
    double id=(maxdistance-d)/maxdistance;
//    System.out.println("++++++++++++++++++++++++++++++++++");
//    System.out.println("distance="+d);
//    System.out.println("invdistance="+id);
//    System.out.println("++++++++++++++++++++++++++++++++++");
    
    return id;
//    return 1;
    }
  
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
  
  static double FREQFACTOR=1.0;
  
  static int[] BASEFREQS={16,32,48,64,80,96,112,128};
  
  /*
   * get summed values for stripes
   * use that to get base frequency
   * multiply basefrequency by factored normalized area
   */
  private double getFreq(Chunk c,double maxchunkarea){
    //get summed values for stripes
    int i=0;
    for(Stripe s:c.stripes)
      i+=s.getValue();
    //use that to get base frequency
    double basefreq=BASEFREQS[i%BASEFREQS.length];
    //get the normalized area and curve it too
    double invnormarea=(maxchunkarea-((double)c.getArea()))/maxchunkarea;
    //
    basefreq=basefreq*invnormarea*FREQFACTOR;
    
    return (int)basefreq;}
  
  private int getMaxRectangleArea(){
    return cloudedplain.stripesystem.stage.getArea();}
 
}
