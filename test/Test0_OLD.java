package org.fleen.cloudedPlain.test;

import java.awt.image.BufferedImage;
import java.io.File;

import org.fleen.cloudedPlain.core.ProgressListener;
import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer0;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

public class Test0_OLD{
  
  private static final int 
    WIDTH=400,
    HEIGHT=400,
    DURATION=100;
  
  private static UI ui;
  
  private static final File EXPORTDIR=new File("/home/john/Desktop/cpexport"); 
  
  private static ProgressListener listener=new ProgressListener(){
    public void notify(StripeSystem plain,BufferedImage frameimage,int[] framesound){
      if(plain.time%10==0)System.out.println("sliceindex="+plain.time);
      //TODO we should express the slice sound here too.
      ui.imagepanel.image=frameimage;
      ui.repaint();}};
  
  public static final void main(String[] a){
    System.out.println("test 0 start");
    StripeSystem p=new StripeSystem(WIDTH,HEIGHT,DURATION,new Generator1(),new VideoRenderer0(),new RendererSound0(),EXPORTDIR,listener);
    ui=new UI(p);
    p.render();
    System.out.println("test 0 end");}

}
