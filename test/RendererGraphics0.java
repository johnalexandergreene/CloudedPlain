package org.fleen.cloudedPlain.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.PlainShape;
import org.fleen.cloudedPlain.core.RendererGraphics;

public class RendererGraphics0 implements RendererGraphics{

  Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}

  public BufferedImage render(int[][] slice){
    
    
  //TODO
    //test shape getter 
    List<PlainShape> ps=plain.getShapes();
    System.out.println("shape count = "+ps.size());
    
    
    
    BufferedImage image=new BufferedImage(plain.width,plain.height,BufferedImage.TYPE_INT_RGB);
    Graphics2D g=image.createGraphics();
    g.setPaint(Color.green);
    g.fillRect(0,0,plain.width,plain.height);
    for(int x=0;x<plain.width;x++)
      for(int y=0;y<plain.height;y++)
        image.setRGB(x,y,getRGB(slice[x][y]));
    return image;}
  
  private static final Color[] COLORS={
      new Color(255,0,0),
      new Color(255,128,0),
      new Color(0,255,0),
      new Color(0,128,255),
      new Color(0,0,255)
  };
  
  private int getRGB(int a){
    return COLORS[a%COLORS.length].getRGB();}

}
