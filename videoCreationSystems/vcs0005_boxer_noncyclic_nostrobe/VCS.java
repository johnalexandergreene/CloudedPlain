package org.fleen.cloudedPlain.videoCreationSystems.vcs0005_boxer_noncyclic_nostrobe;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.ProgressListener;
import org.fleen.cloudedPlain.core.exportAudio.AudioExporter0;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer4_reduced_amplitude_to_cure_tearing;
import org.fleen.cloudedPlain.core.stripeGenerator.Generator5_soundtest2_squaretest2;
import org.fleen.cloudedPlain.videoCreationSystems.UI;
import org.fleen.cloudedPlain.videoCreationSystems.vcs0000_foo.VideoRenderer1;

public class VCS{
  
  private static final int 
    WIDTH=1280,
    HEIGHT=720,
    DURATION=25860;
  
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
      new StripeGenerator0(),
      WORKINGDIR,
      new VideoRenderer0(),new VideoExporter0(),
      null,null,
      listener);
    ui=new UI(cp);
    cp.run();
    System.out.println("test 0 end");}

}
