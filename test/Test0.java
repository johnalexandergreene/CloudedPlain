package org.fleen.cloudedPlain.test;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.ProgressListener;
import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer0;
import org.fleen.cloudedPlain.core.stripeGenerator.Generator3_sweepertest;

public class Test0{
  
  private static final int 
    WIDTH=400,
    HEIGHT=400,
    DURATION=1000;
  
  private static UI ui;
  
  private static final String WORKINGDIR="/home/john/Desktop/cpexport"; 
  
  private static ProgressListener listener=new ProgressListener(){
    public void notify(CloudedPlain cloudedplain){
      ui.repaint();}};
  
  public static final void main(String[] a){
    System.out.println("test 0 start");
    CloudedPlain cp=new CloudedPlain(
      WIDTH,HEIGHT,DURATION,
      new Generator3_sweepertest(),
      WORKINGDIR,
      new VideoRenderer0(),new VideoExporter0(),
      null,null,
      listener);
    ui=new UI(cp);
    cp.run();
    System.out.println("test 0 end");}

}
