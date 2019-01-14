package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0011_3colorpalette;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunks;

public class VideoRenderer0 implements VideoRenderer{

  public static final Color[] P_FOO=new Color[]{
      new Color(255,105,198),
      new Color(255,0,0),
      new Color(255,165,0),
      new Color(255,227,37),
      new Color(255,255,152),
    };
  
  public static final Color[] P_FOO2=new Color[]{
      new Color(255,0,0),
//      new Color(255,128,0),
      new Color(255,255,0),
      new Color(255,255,255)
    };
    
  static final Color[] COLORS=P_FOO2;
  
  public BufferedImage renderFrame(StripeSystem stripesystem){
    
    int w=stripesystem.stage.getWidth(),h=stripesystem.stage.getHeight();
    BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    Graphics2D g=image.createGraphics();
    
//flip it?
//    AffineTransform t=AffineTransform.getScaleInstance(1,-1);
//    t.translate(0,h);
//    g.setTransform(t);
    
    g.setPaint(Color.gray);
    g.fillRect(0,0,w,h);
    Chunks chunks=stripesystem.getChunks();
    for(Chunk chunk:chunks){
      g.setPaint(getColor(chunk,stripesystem));
      g.fillRect(chunk.getCoorX(),chunk.getCoorY(),chunk.getWidth(),chunk.getHeight());}
    return image;}
  
  Color getColor(Chunk chunk,StripeSystem stripesystem){
    int a=0;
    for(Stripe stripe:chunk.stripes)
      a+=stripe.getValue();
    int i=a%COLORS.length;
    if(i<0)i+=COLORS.length;
    Color c=COLORS[i];
    return c;}
  


}
