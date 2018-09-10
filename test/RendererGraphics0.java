package org.fleen.cloudedPlain.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.renderVideo.RendererGraphics;

public class RendererGraphics0 implements RendererGraphics{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public BufferedImage render(int[][] slice){
    
    
  //TODO
    //test shape getter 
//    List<PlainShape> ps=plain.getShapes();
//    System.out.println("shape count = "+ps.size());
    
    
    
    BufferedImage image=new BufferedImage(plain.width,plain.height,BufferedImage.TYPE_INT_RGB);
//    WritableRaster raster = image.getRaster();
    
    Graphics2D g=image.createGraphics();
    g.setPaint(Color.green);
    g.fillRect(0,0,plain.width,plain.height);
    for(int x=0;x<plain.width;x++)
      for(int y=0;y<plain.height;y++)
        image.setRGB(x,y,getRGB(slice[x][y]));
    
    
    
    
    
    return image;}
  
  public Image getImageFromArray(int[] pixels, int width, int height) {
    MemoryImageSource mis = new MemoryImageSource(width, height, pixels, 0, width);
    Toolkit tk = Toolkit.getDefaultToolkit();
    return tk.createImage(mis);
}
  
  
  private static final Color[] COLORS={
      new Color(255,0,0),
      new Color(255,128,0),
      new Color(0,255,0),
      new Color(0,128,255),
      new Color(0,0,255)
  };
  public static final Color[] P_TOY_STORY_ADJUSTED2=new Color[]{
      new Color(168,67,39),
      new Color(251,206,89),
      new Color(88,184,121),
      new Color(154,94,154),
      new Color(234,61,65),
      new Color(248,237,23),
      new Color(249,139,90),
      new Color(0,146,232),
      new Color(254,178,213)};
    
    public static final Color[] P_PORCO_ROSSO=new Color[]{
      new Color(227,237,76),
      new Color(184,194,105),
      new Color(226,235,232),
      new Color(174,202,224),
      new Color(94,132,197),
      new Color(248,203,161),
      new Color(179,145,120),
      new Color(220,178,164),
      new Color(90,14,0),
      new Color(189,53,31)};
    
  private static Color[] PALETTE=P_TOY_STORY_ADJUSTED2;
  
  private int getRGB(int a){
    return PALETTE[a%PALETTE.length].getRGB();}

}
