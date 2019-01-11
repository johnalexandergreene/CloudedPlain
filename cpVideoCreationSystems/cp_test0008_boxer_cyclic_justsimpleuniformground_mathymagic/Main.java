package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0008_boxer_cyclic_justsimpleuniformground_mathymagic;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.ProgressListener;
import org.fleen.cloudedPlain.core.exportAudio.AudioExporter0;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.cpVideoCreationSystems.UI;

public class Main{
  
  public static final int 
    WIDTH=1280,
    HEIGHT=720,
    DURATION=6000;
  
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
    CloudedPlain cp=new CloudedPlain(
      WIDTH,HEIGHT,DURATION,
      new StripeGenerator0(),
      WORKINGDIR,
      new VideoRenderer0(),new VideoExporter0(),
      new AudioRenderer1(),new AudioExporter0(),
      listener);
    ui=new UI(cp);
    cp.run();}

}
