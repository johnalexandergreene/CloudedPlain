package org.fleen.cloudedPlain.test;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.ProgressListener;
import org.fleen.cloudedPlain.core.exportAudio.AudioExporter0;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer3;
import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer1;
import org.fleen.cloudedPlain.core.stripeGenerator.Generator5_soundtest2_squaretest2;

public class T0002_squaresweeper{
  
  private static final int 
    WIDTH=1280,
    HEIGHT=720,
    DURATION=3600;
  
  private static UI ui;
  
  private static final String WORKINGDIR="/home/john/Desktop/cpexport"; 
  
  private static ProgressListener listener=new ProgressListener(){
    public void notify(CloudedPlain cloudedplain){
      ui.repaint();}};
  
  /*
   * new VideoExporter0()
   * 
   * 
   */
  public static final void main(String[] a){
    System.out.println("test 0 start");
    CloudedPlain cp=new CloudedPlain(
      WIDTH,HEIGHT,DURATION,
      new Generator5_soundtest2_squaretest2(),
      WORKINGDIR,
      new VideoRenderer1(),new VideoExporter0(),
      new AudioRenderer3(),new AudioExporter0(),
      listener);
    ui=new UI(cp);
    cp.run();
    System.out.println("test 0 end");}

}
