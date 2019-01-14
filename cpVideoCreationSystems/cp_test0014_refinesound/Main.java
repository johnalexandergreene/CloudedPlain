package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0014_refinesound;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.CloudedPlainObserver;
import org.fleen.cloudedPlain.core.exportAudio.AudioExporter0;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.cpVideoCreationSystems.UI;

/*
 * DOING IT SQUARE because square is better
 * big upgrade and refinement
 * improving sound too
 */
public class Main{
  
  public static final int 
    SPAN=720,
    DURATION=3600;
  
  private static UI ui;
  
  private static final String WORKINGDIR="/home/john/Desktop/cpexport"; 
  
  private static CloudedPlainObserver observer=new CloudedPlainObserver(){
    public void notify(CloudedPlain cloudedplain){
      ui.repaint();}};
  
  public static final void main(String[] a){
    CloudedPlain cp=new CloudedPlain(
      SPAN,DURATION,
      new Projector0(),
      WORKINGDIR,
      new VideoRenderer0(),new VideoExporter0(),
      new AudioRenderer1(),new AudioExporter0(),
      observer);
    ui=new UI(cp);
    cp.run();}

}
