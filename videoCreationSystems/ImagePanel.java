package org.fleen.cloudedPlain.videoCreationSystems;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import org.fleen.cloudedPlain.core.CloudedPlain;

public class ImagePanel extends JPanel{

  private static final long serialVersionUID=581500866418502553L;
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  ImagePanel(CloudedPlain cloudedplain){
    this.cloudedplain=cloudedplain;}
  
  /*
   * ################################
   * CLOUDED PLAIN
   * ################################
   */
  
  CloudedPlain cloudedplain;
  
  /*
   * ################################
   * PAINT
   * ################################
   */
  
  static final AffineTransform OFFSETALITTLE=AffineTransform.getTranslateInstance(50,50);
  
  public void paint(Graphics g){
    super.paint(g);
    if(cloudedplain.videoframe==null)return;
    Graphics2D g2=(Graphics2D)g;
    g2.drawImage(cloudedplain.videoframe,OFFSETALITTLE,null);}

}
