package org.fleen.cloudedPlain.test;

import java.awt.image.BufferedImage;
import java.io.File;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.PlainProgressListener;

public class Test0{
  
  private static final int 
    WIDTH=600,
    HEIGHT=600,
    DURATION=1200;
  
  private static UI ui;
  
  private static final File EXPORTDIR=new File("/home/john/Desktop/cpexport"); 
  
  private static PlainProgressListener rendererlistener=new PlainProgressListener(){
    public void notify(Plain plain,BufferedImage sliceimage,int[] slicesound){
      if(plain.sliceindex%100==0)System.out.println("sliceindex="+plain.sliceindex);
      //TODO we should express the slice sound here too.
      ui.imagepanel.image=sliceimage;
      ui.repaint();}};
  
  public static final void main(String[] a){
    System.out.println("test 0 start");
    Plain p=new Plain(WIDTH,HEIGHT,DURATION,new Generator0(),new RendererGraphics0(),new RendererSound0(),EXPORTDIR,rendererlistener);
    ui=new UI(p);
    p.render();
    System.out.println("test 0 end");}

}
