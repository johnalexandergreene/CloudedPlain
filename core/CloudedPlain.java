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
    this.audioexporter=audioexporter;
    this.progresslistener=progresslistener;
    audioframes.clear();}//init that list
  
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
      if(stripesystem.frameindex%10==0)System.out.println("frameindex="+stripesystem.frameindex);
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
      videoexporter.export(videoframe,stripesystem.frameindex,workingdirectory);}
  
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
      audioexporter.exportAudio(audioframes,workingdirectory);}}
  
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
