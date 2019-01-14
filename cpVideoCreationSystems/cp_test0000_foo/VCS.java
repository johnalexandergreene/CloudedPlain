package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0000_foo;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.CloudedPlainObserver;
import org.fleen.cloudedPlain.core.exportAudio.AudioExporter0;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.stripeGenerator.Generator5_soundtest;
import org.fleen.cloudedPlain.cpVideoCreationSystems.UI;

public class VCS{
  
  private static final int 
    WIDTH=512,
    HEIGHT=512,
    DURATION=300;
  
  private static UI ui;
  
  private static final String WORKINGDIR="/home/john/Desktop/cpexport"; 
  
  private static CloudedPlainObserver listener=new CloudedPlainObserver(){
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
      new Generator5_soundtest(),
      WORKINGDIR,
      new VideoRenderer1(),new VideoExporter0(),
      new AudioRenderer2(),new AudioExporter0(),
      listener);
    ui=new UI(cp);
    cp.run();
    System.out.println("test 0 end");}

}
