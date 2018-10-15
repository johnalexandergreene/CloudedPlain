package org.fleen.cloudedPlain.test;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.ProgressListener;
import org.fleen.cloudedPlain.core.exportAudio.AudioExporter0;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer1;
import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer1;
import org.fleen.cloudedPlain.core.stripeGenerator.Generator4;

public class Test0{
  
  private static final int 
    WIDTH=512,
    HEIGHT=512,
    DURATION=2000;
  
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
      new Generator4(),
      WORKINGDIR,
      new VideoRenderer1(),new VideoExporter0(),
      new AudioRenderer1(),new AudioExporter0(),
      listener);
    ui=new UI(cp);
    cp.run();
    System.out.println("test 0 end");}

}
