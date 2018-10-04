package org.fleen.cloudedPlain.test;

import java.awt.image.BufferedImage;
import java.io.File;

import org.fleen.cloudedPlain.core.CloudedPlain;
import org.fleen.cloudedPlain.core.RenderingProgressListener;

public class Test0{
  
  private static final int 
    WIDTH=400,
    HEIGHT=400,
    DURATION=100;
  
  private static UI ui;
  
  private static final File EXPORTDIR=new File("/home/john/Desktop/cpexport"); 
  
  private static RenderingProgressListener listener=new RenderingProgressListener(){
    public void notify(CloudedPlain plain,BufferedImage frameimage,int[] framesound){
      if(plain.frameindex%10==0)System.out.println("sliceindex="+plain.frameindex);
      //TODO we should express the slice sound here too.
      ui.imagepanel.image=frameimage;
      ui.repaint();}};
  
  public static final void main(String[] a){
    System.out.println("test 0 start");
    CloudedPlain p=new CloudedPlain(WIDTH,HEIGHT,DURATION,new Generator1(),new RendererGraphics0(),new RendererSound0(),EXPORTDIR,listener);
    ui=new UI(p);
    p.render();
    System.out.println("test 0 end");}

}
