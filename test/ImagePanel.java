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
   * SAMPLER
   * ################################
   */
  
  Plain plain;
  
  /*
   * ################################
   * PAINT
   * ################################
   */
  
  public BufferedImage image=null;
  
  public void paint(Graphics g){
    super.paint(g);
    if(image==null)return;
    Graphics2D g2=(Graphics2D)g;
    g2.drawImage(image,null,null);}

}
