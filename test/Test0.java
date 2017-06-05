package org.fleen.cloudedPlain.test;

import java.awt.image.BufferedImage;
import java.io.File;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.RendererListener;

public class Test0{
  
  private static final int 
    WIDTH=100,
    HEIGHT=100,
    DURATION=300;
  
  private static UI ui;
  
  private static final File EXPORTDIR=new File("/home/john/Desktop/cpexport"); 
  
  private static RendererListener rendererlistener=new RendererListener(){
    public void notify(BufferedImage sliceimage,int[] slicesound){
      //TODO we could express the slice sound here too.
      ui.imagepanel.image=sliceimage;
      ui.repaint();}};
  
  public static final void main(String[] a){
    Plain p=new Plain(WIDTH,HEIGHT,DURATION,new CloudCreator0(),new RendererGraphics0(),new RendererSound0());
    ui=new UI(p);
    p.render(EXPORTDIR,rendererlistener);}

}
