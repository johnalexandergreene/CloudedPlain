package org.fleen.cloudedPlain.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.fleen.cloudedPlain.core.Plain;

public class ImagePanel extends JPanel{

  private static final long serialVersionUID=581500866418502553L;
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  ImagePanel(Plain plain){
    this.plain=plain;}
  
  /*
   * ################################
   * SAMPLER
   * ################################
   */
  
  Plain plain;
  
  /*
   * ################################
   * PAINT
   * ################################
   */
  
  private static final int PADDINGSPAN=10;
  
  public BufferedImage image=null;
  
  public void paint(Graphics g){
    AffineTransform pad=new AffineTransform();
    pad.translate(PADDINGSPAN,PADDINGSPAN);
    //
    super.paint(g);
    if(plain==null||plain.uiimage==null)return;
    Graphics2D g2=(Graphics2D)g;
    g2.drawImage(image,pad,null);}

}
