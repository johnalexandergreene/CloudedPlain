package org.fleen.cloudedPlain.core.renderAudio;

import org.fleen.cloudedPlain.core.CloudedPlain;
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
  
  public int[] renderFrame(StripeSystem ss){
    int audiosamplerateperframe=getCloudedPlain().getAudioSampleRatePerFrame();
    int[] sound=new int[audiosamplerateperframe];
    int cellsum=getCellSum(ss);
    int a,b;
    b=cellsum*cellsum;
    if(b>1000000000)b=1000000000;
    for(int i=0;i<audiosamplerateperframe;i++){
      a=i%1000;
      if(a<500)
        sound[i]=b;
      else
        sound[i]=0;
    }
    return sound;}
  
  private int getCellSum(StripeSystem ss){
    int s=0;
    for(Chunk c:ss.getChunks()){
      s+=getSoundVal(c);}
    return s;}
  
  private int getSoundVal(Chunk c){
    int i=0;
    for(Stripe s:c.stripes)
      i+=s.getValue();
    i*=c.getArea();
    return i;}
  
//  
//  public int[] render(int[][] slice){
//    int[] sound=new int[Plain.SLICESOUNDSAMPLERATE];
//    for(PlainShape shape:plain.getShapes())
//      doSound(sound,shape);
//    return sound;}
//  
//  
//  private static final int[] COLORTONEWAVELENGTH={
//    20,40,60,80,100,120,140    
//  };
//  
//  private void doSound(int[] sound,PlainShape shape){
//    int interval=COLORTONEWAVELENGTH[shape.color]+(shape.size()/20);
//    for(int i=0;i<Plain.SLICESOUNDSAMPLERATE;i++)
//      if(i%interval<30)
//        incrementSound(sound,i);}
//  
//  
//  private void incrementSound(int[] sound,int i){
//    int a=sound[i]+800;
//    if(a>Plain.SOUNDTICKMAXVAL)
//      a=Plain.SOUNDTICKMAXVAL;
//    sound[i]=a;}
    
    
    
 

}
