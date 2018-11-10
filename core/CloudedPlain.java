package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.exportAudio.AudioExporter;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer;
import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer;
import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

/*
 * creates a video (with audio) of a system of dancing stripes
 * manages a stripe system and all associated renderers and exporters
 * exports a list of frame image files and a audio file
 * 
 * TODO
 * 
 * this should create its own subdirectory within the working directory and export its stuff there
 * 
 */
public class CloudedPlain{

  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public CloudedPlain(
    int width,int height,int duration,
    StripeGenerator stripegenerator,
    String workingdirectory,
    VideoRenderer videorenderer,VideoExporter0 videoexporter,
    AudioRenderer audiorenderer,AudioExporter audioexporter,
    ProgressListener progresslistener){
    initStripeSystemAndGeometry(width,height,duration,stripegenerator);
    initWorkingDirectory(workingdirectory);
    this.videorenderer=videorenderer;
    this.videoexporter=videoexporter;
    this.audiorenderer=audiorenderer;
    if(audiorenderer!=null)audiorenderer.setCloudedPlain(this);
    this.audioexporter=audioexporter;
    if(audioexporter!=null)audioexporter.setCloudedPlain(this);
    this.progresslistener=progresslistener;
    audioframes.clear();}//init that list
  
  /*
   * ################################
   * GLOBAL METRICS
   * ################################
   */
  
  /*
   * 720=1*2*3*4*5*6
   * thus 720 has lots of factors, which is why we chose it
   * we do 60 frames per second
   * 60*720=43200
   * So 43200 is our per-second sample rate
   */
  static final int
    //video and audio frame rate
    FRAMERATE=60,
    //sound sample rate over a single 1/60th of a second frame. 
    AUDIOSAMPLERATEPERFRAME=720,
    //sound sample rate over a whole second
    AUDIOSAMPLERATEPERSECOND=FRAMERATE*AUDIOSAMPLERATEPERFRAME;//43200
  
  public int getFrameRate(){
    return FRAMERATE;}
  
  public int getAudioSampleRatePerFrame(){
    return AUDIOSAMPLERATEPERFRAME;}
  
  public int getAudioSampleRatePerSecond(){
    return AUDIOSAMPLERATEPERSECOND;}
  
  /*
   * ################################
   * MAIN PROCESS
   * increment stripesystem to depth
   * at each increment 
   *   render a frame
   *   render a soundpiece
   *   export frame
   * when finished export sound
   * ################################
   */
  
  public void run(){
    System.out.println("CloudedPlain.run start");
    for(int i=0;i<duration;i++){
      //
      try{Thread.sleep(10);}catch(Exception x){}
      //
      if(stripesystem.time%10==0)System.out.println("frameindex="+stripesystem.time);
      renderVideoFrame();
      exportVideoFrame();
      renderAudioFrame();
      notifyProgressListener();
      stripesystem.incrementFrame();}
    exportAudio();
    System.out.println("CloudedPlain.run end");}
  
  /*
   * ################################
   * STRIPE SYSTEM AND GEOMETRY
   * ################################
   */
  
  public StripeSystem stripesystem;
  public int duration;
  
  void initStripeSystemAndGeometry(int w,int h,int d,StripeGenerator g){
    stripesystem=new StripeSystem(w,h,g);
    duration=d;}
  
  /*
   * ################################
   * WORKING DIRECTORY
   * export everything to here
   * ################################
   */
  
  File workingdirectory;
  
  void initWorkingDirectory(String path){
    workingdirectory=new File(path);}
  
  /*
   * ################################
   * RENDER VIDEO
   * ################################
   */
  
  VideoRenderer videorenderer;
  public BufferedImage videoframe=null;
  
  void renderVideoFrame(){
    if(videorenderer!=null)
      videoframe=videorenderer.renderFrame(stripesystem);}
  
  /*
   * ################################
   * EXPORT VIDEO
   * ################################
   */
  
  VideoExporter videoexporter;
  
  void exportVideoFrame(){
    if(videoexporter!=null)
      videoexporter.export(videoframe,stripesystem.time,workingdirectory);}
  
  /*
   * ################################
   * RENDER AUDIO
   * ################################
   */
  
  AudioRenderer audiorenderer;
  List<int[]> audioframes=new ArrayList<int[]>();
  
  void renderAudioFrame(){
    if(audiorenderer!=null){
      int[] a=audiorenderer.renderFrame(stripesystem);
      audioframes.add(a);}}
  
  /*
   * ################################
   * EXPORT AUDIO
   * ################################
   */
  
  AudioExporter audioexporter;
  
  void exportAudio(){
    if(audioexporter!=null){
      System.out.println("export audio");
      //assemble the sound array
      int framelength=getAudioSampleRatePerFrame();
      int[] audio=new int[audioframes.size()*framelength];
      for(int i=0;i<audioframes.size();i++)
        System.arraycopy(audioframes.get(i),0,audio,i*framelength,framelength);
      //export it
      audioexporter.exportAudio(audio,workingdirectory);}}
  
  /*
   * ################################
   * PROGRESS LISTENER
   * ################################
   */
  
  ProgressListener progresslistener;
  
  void notifyProgressListener(){
    if(progresslistener!=null)
      progresslistener.notify(this);}

}
